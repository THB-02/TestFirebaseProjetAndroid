package com.openclassrooms.firebaseoc.models;


import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Salon {
    private String nom;
    private String scrum;
    private String id;


    public Salon() { }

    public Salon(String name) {
        this.nom = name;
        getDocumentId();
    }

    public Salon(String name, String scrum) {

        this.nom= name;
        this.scrum = scrum;
    }

    // --- GETTERS ---
    public String getNom() { return nom; }
    public String getId() { return id; }
    public String getScrum(){
        return scrum;
    }

    // --- SETTERS ---
    public void setNom(String nom) {
        this.nom = nom;
        getDocumentId();
    }
    public void setId(String id) { this.id = id; }
    public void setScrum(String scrum){
        this.scrum = scrum;
    }

    public void getDocumentId() {
        FirebaseFirestore.getInstance().collection("salons")
                .whereEqualTo("nom", getNom())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            setId(document.getId());
                        }
                    }
                });
    }
}
