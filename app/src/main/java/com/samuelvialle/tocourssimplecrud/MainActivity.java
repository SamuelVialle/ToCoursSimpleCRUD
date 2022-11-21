package com.samuelvialle.tocourssimplecrud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /**** GLOBAL ****/
    // Ajout des Vars globales
    private RecyclerView rvTodoList;
    private FloatingActionButton fabAddTodo;

    // Var Firebase
    private FirebaseFirestore db;
    private CollectionReference todoListCollection; // La référence vers la collection

    // Var de l'adapter
    private AdapterTodo adapter;

    /*** MÉTHODES PERSO ****/
    // Méthode initUI pour la liaison des widgets dans le code
    private void initUI(){
        fabAddTodo = findViewById(R.id.fabAddTodo);
        // Le recycler
        rvTodoList = findViewById(R.id.rvTodoList);
        // initialisation du linear layout qui va contenir le recycler view
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false);
        rvTodoList.setHasFixedSize(true); // Pour accéler l'affichage
        // Initialisation du recyclerView avec le linearLayout
        rvTodoList.setLayoutManager(linearLayoutManager);
    }

    // Méthode d'initialisation de la db Firestore
    private void initFirebase() {
        db = FirebaseFirestore.getInstance();
        todoListCollection = db.collection("TodoList");
    }

    // Méthode pour afficher l'alertDialog
    private void showAlertDialogToAddTodo(){
        LayoutInflater inflater = LayoutInflater.from(this); // Inflater pour injecter le layout de l'alertDialog
        View subView = inflater.inflate(R.layout.alert_dialog_create_todo, null); // Création de la sous vue qui contient l'alertDialog

        // Le lien design code des élements de l'alertDialog
        final TextInputEditText etTodoTitle = subView.findViewById(R.id.etTodoTitle);
        final TextInputEditText etTodoContent = subView.findViewById(R.id.etTodoContent);

        AlertDialog.Builder builder = new AlertDialog.Builder(this); // Le builder de l'alertDialog

        // Gestion du bouton positiveButton, celui qui validera la création de la note en base
        builder.setPositiveButton("CREATE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Ici on récupérera les données du formulaire et on les passera à la méthode qui gère la création dans Firestore
//                String randomId = UUID.randomUUID().toString(); // Random ID
                final Long time = System.currentTimeMillis()/1000; // Timestamp
                final String id = time.toString();
                final String title = etTodoTitle.getText().toString();
                final String content = etTodoContent.getText().toString();

                createData(id, title, content);
            }
        });

        // Gestion du bouton negativeButton, celui qui annule ma création du todo
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Ajout d'un toast pour la gestion de l'UX
                Toast.makeText(MainActivity.this, "Canceled add todo", Toast.LENGTH_SHORT).show();
            }
        });

        // Création de l'alertDialog en fonction des options ci-dessus
        builder.setTitle("CREATE TODO"); // Ajout du titre
        builder.setView(subView); // Association de la vue
        builder.create(); // Création
        builder.show(); // Affichage
    }

    /** CREATE **/
    // Méthode pour l'ajout de données dans Firestore
    private void createData(String id, String title, String content) {
        if (!title.isEmpty() && !content.isEmpty()){
            // Création du tableau qui contiendra les données à envoyer
//            HashMap<String, Object> data = new HashMap<>();
//            data.put("id", id);
//            data.put("title", title);
//            data.put("desc", content);

            // Création du tableau à partir du model
            ModelTodo data = new ModelTodo(id, title, content);

            // Envoi des data dans la base
            todoListCollection.document(id).set(data)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(MainActivity.this, "Note saved !", Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this, "Failed" + e, Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(this, "Empty fields is not allow", Toast.LENGTH_SHORT).show();
        }
    }

    /** READ **/
    private void readData(){
        Query query = todoListCollection.orderBy("id");

        FirestoreRecyclerOptions<ModelTodo> todos =
                new FirestoreRecyclerOptions.Builder<ModelTodo>()
                        .setQuery(query, ModelTodo.class)
                        .build();
        Log.i("TAG", "readData:");
        // Gestion de l'adapter
        adapter = new AdapterTodo(todos);
        rvTodoList.setAdapter(adapter);
    }



    /*** Gestion du clic sur le FAB **/
    private void createTodoClic(){
        fabAddTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialogToAddTodo();
            }
        });
    }

    /**** CYCLES DE VIES ****/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Les méthodes appelées
        initUI();
        initFirebase();
        createTodoClic();
        readData();

    }
}