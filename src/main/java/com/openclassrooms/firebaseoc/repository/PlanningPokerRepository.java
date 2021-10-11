package com.openclassrooms.firebaseoc.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.openclassrooms.firebaseoc.manager.UserManager;
import com.openclassrooms.firebaseoc.models.Message;
import com.openclassrooms.firebaseoc.models.Salon;
import com.openclassrooms.firebaseoc.models.US;

public class PlanningPokerRepository {

    private static final String CHAT_COLLECTION = "salons";
    private static final String MESSAGE_COLLECTION = "messages";
    private static volatile PlanningPokerRepository instance;
    private UserManager userManager;

    private PlanningPokerRepository() { this.userManager = UserManager.getInstance(); }


    public static PlanningPokerRepository getInstance() {
        PlanningPokerRepository result = instance;
        if (result != null) {
            return result;
        }
        synchronized(PlanningPokerRepository.class) {
            if (instance == null) {
                instance = new PlanningPokerRepository();
            }
            return instance;
        }
    }

    public void createUS(String enonce, String salon){

        // Create the Message object
        US us = new US(enonce);

        // Store US to Firestore
        FirebaseFirestore.getInstance().collection("salons")
        .document(salon)
        .collection("ListeUS")
        .add(us);
    }

    public Query getLastUS(String salon){
        return FirebaseFirestore.getInstance().collection("salons")
                .document(salon)
                .collection("ListeUS")
                .orderBy("dateCreated", Query.Direction.DESCENDING)
                .limit(1);
    }


}
