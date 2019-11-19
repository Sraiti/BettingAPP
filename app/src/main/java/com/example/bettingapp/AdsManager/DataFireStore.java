package com.example.bettingapp.AdsManager;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class DataFireStore {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public module_firebase ObjectFirebase;
    public boolean isObjLoaded = false;

    public static DataFireStore Instence;

    public static DataFireStore getInstance() {
        if (Instence == null)
            Instence = new DataFireStore();
        return Instence;
    }


    public void loadObject() {
        DocumentReference ref = db.collection("ads").document("object");
        ref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                isObjLoaded = true;
                ObjectFirebase = documentSnapshot.toObject(module_firebase.class);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                isObjLoaded = true;
            }
        });
    }

}
