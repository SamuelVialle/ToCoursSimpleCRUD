package com.samuelvialle.tocourssimplecrud.commons;

import android.util.Log;

import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseInstances {

    // L'instance aux outils Firebase n'est créée qu'une seule puis elle est ré-utilisée encore et
    // encore dans les classes qui en ont besoin
    public static void getInstances() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Log.i("TAG", "getInstances: ");
    }

}
