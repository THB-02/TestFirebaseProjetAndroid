package com.openclassrooms.firebaseoc.models;



import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Salon {
    private String nom;
    private String scrum;
    private String uid;
    private Date dateCreated;
    private Map<String, String> members = new HashMap<>();

    public Salon() { }

    public Salon(String name) {
        this.nom = name;
        getDocumentId();
    }
    public Salon(String name, String scrum) {
        this.nom= name;
        this.scrum = scrum;
    }
    public Salon(String uid,String name, String scrum) {
        this.uid = uid;
        this.nom= name;
        this.scrum = scrum;
    }


    // --- GETTERS ---
    public String getNom() { return nom; }
    public String getUid() { return uid; }
    public String getScrum(){
        return scrum;
    }

    @ServerTimestamp
    public Date getDateCreated() { return dateCreated; }

    public Map<String, String> getMembers() {
        return members;
    }

    public void setMembers(Map<String, String> members) {
        this.members = members;
    }

    // --- SETTERS ---
    public void setNom(String nom) {
        this.nom = nom;
        getDocumentId();
    }
    
    public void setUid(String uid) { this.uid = uid; }
    public void setScrum(String scrum){
        this.scrum = scrum;
    }
    public void setDateCreated(Date dateCreated) { this.dateCreated = dateCreated; }

    public void getDocumentId() {
        FirebaseFirestore.getInstance().collection("salons")
                .whereEqualTo("nom", getNom())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            setUid(document.getId());
                        }
                    }
                });
    }
    public void addMembers(User user, String salon){
        Map<String, String> member = new HashMap<>();
        member.put("uid", user.getUid().toString());
        Map<String, Map<String, String>> data = new HashMap<>();
        data.put("members",member);

        FirebaseFirestore.getInstance().collection("salons")
                .document(salon)
                .set(data, SetOptions.merge());
    }


}
