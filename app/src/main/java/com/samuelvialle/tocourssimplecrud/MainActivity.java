package com.samuelvialle.tocourssimplecrud;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;
import com.samuelvialle.tocourssimplecrud.commons.FirebaseInstances;

public class MainActivity extends AppCompatActivity {

    /**** GLOBAL ****/
    //1 ajout des Vars globales
    private RecyclerView rvTodoList;
    private FloatingActionButton fabAddTodo;



    /*** MÉTHODES PERSO ****/
    //2 Initialisation des Vars Globales
    private void initUI(){
        fabAddTodo = findViewById(R.id.fabAddTodo);
        // L recycler
        rvTodoList = findViewById(R.id.rvTodoList);
        // initialisation du linear layout qui va contenir le recycler view
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false);
        // Initialisation du recyclerView avec le linearLayout
        rvTodoList.setLayoutManager(linearLayoutManager);
    }

    //5 Méthode pour afficher l'alertDialog
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

            }
        });

        // Gestion du bouton negativeButton, celui qui annule ma création du todo
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        // Création de l'alertDialog en fonction des options ci-dessus
        builder.setTitle("CREATE TODO"); // Ajout du titre
        builder.setView(subView); // Association de la vue
        builder.create(); // Création
        builder.show(); // Affichage
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
        createTodoClic();
        // 4 Ajout de l'appel des la méthode de connexion aux outils Firebase
//        FirebaseInstances.getInstances();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

    }
}