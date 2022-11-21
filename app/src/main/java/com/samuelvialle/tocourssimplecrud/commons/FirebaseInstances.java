package com.samuelvialle.tocourssimplecrud.commons;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseInstances {

    // L'instance aux outils Firebase n'est créée qu'une seule puis elle est ré-utilisée encore et
    // encore dans les classes qui en ont besoin
    public static void getInstances() {
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Log.i("TAG", "getInstances: ");

//        @NonNull
//        public static FirebaseFirestore getInstance() {
//            FirebaseApp app = FirebaseApp.getInstance();
//            if (app == null) {
//                throw new IllegalStateException("You must call FirebaseApp.initializeApp first.");
//            } else {
//                return getInstance(app, "(default)");
//            }
//        }
//        @NonNull
//        public static FirebaseApp getInstance() {
//            synchronized(LOCK) {
//                FirebaseApp defaultApp = (FirebaseApp)INSTANCES.get("[DEFAULT]");
//                if (defaultApp == null) {
//                    throw new IllegalStateException("Default FirebaseApp is not initialized in this process " + ProcessUtils.getMyProcessName() + ". Make sure to call FirebaseApp.initializeApp(Context) first.");
//                } else {
//                    return defaultApp;
//                }
//            }
//        }
    }

}
