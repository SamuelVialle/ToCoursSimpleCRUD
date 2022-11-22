package com.samuelvialle.tocourssimplecrud.commons;

import com.google.firebase.firestore.FirebaseFirestore;

public interface Constants {

    // Constantes pour la collection FireStore
    String COLLECTION_TODO = "TodoList";

    // Constantes pour les champs des documents de cette collection
    String KEY_ID = "id";
    String KEY_TITLE = "title";
    String KEY_CONTENT = "content";
    String KEY_PRIORITY = "priority";

}
