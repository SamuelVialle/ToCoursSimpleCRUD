package com.samuelvialle.tocourssimplecrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.samuelvialle.tocourssimplecrud.commons.FirebaseInstances;

public class MainActivity extends AppCompatActivity {

    /**** GLOBAL ****/
    //1 ajout des Vars globales
    private RecyclerView rvTodoList;
    private FloatingActionButton fabAddTodo;



    /*** MÉTHODES PERSO ****/
    //2 Initialisation des Vars Globales
    private void initUI(){
        rvTodoList = findViewById(R.id.rvTodoList);
        fabAddTodo = findViewById(R.id.fabAddTodo);
    }

    /**** CYCLES DE VIES ****/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //3 Ajout de la méthode initUI
        initUI();
        // 4 Ajout de l'appel des la méthode de connexion aux outils Firebase
        FirebaseInstances.getInstances();
    }
}