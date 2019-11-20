package com.example.bettingapp.util;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.bettingapp.Moduls.match;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FireStore {

    public boolean isloadData=false;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public static FireStore Instence;
    public static FireStore getInstence(){
        if (Instence==null)
            Instence=new FireStore();
        return Instence;
    }

    public List<Object> mRecyclerViewItemsToday;
    public List<Object> mRecyclerViewItemsyesterday;

    public List<Object> LoadDataToday(){
        mRecyclerViewItemsToday = new ArrayList<>();
        db.collection("match")
                .whereEqualTo("Day", "t")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("TAG", document.getId() + " => " + document.getData());
                                mRecyclerViewItemsToday.add(document.toObject(match.class));
                            }
                            isloadData=true;
                        } else {

                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });

        return  mRecyclerViewItemsToday;
    }

    public List<Object> LoadDatayesterday(){
        mRecyclerViewItemsyesterday = new ArrayList<>();
        db.collection("match")
                .whereEqualTo("Day", "y")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("TAG", document.getId() + " => " + document.getData());
                                mRecyclerViewItemsyesterday.add(document.toObject(match.class));
                            }
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });

        return  mRecyclerViewItemsyesterday;
    }
}
