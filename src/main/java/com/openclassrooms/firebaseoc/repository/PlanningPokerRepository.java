package com.openclassrooms.firebaseoc.repository;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.SetOptions;
import com.openclassrooms.firebaseoc.manager.UserManager;
import com.openclassrooms.firebaseoc.models.US;

import java.util.HashMap;
import java.util.Map;

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


    public void addNote(String username, String salon, String idUS, String note){
        Map<String, String> uneNote = new HashMap<>();
        uneNote.put(username, note);
        Map<String, Map<String, String>> data = new HashMap<>();
        data.put("notes",uneNote);

        FirebaseFirestore.getInstance().collection("salons")
                .document(salon)
                .collection("ListeUS")
                .document(idUS)
                .set(data, SetOptions.merge());
    }

    public void finishUS(String salon, String idUS, boolean finished){
        Map<String, Boolean> data = new HashMap<>();
        data.put("finished",finished);

        FirebaseFirestore.getInstance().collection("salons")
                .document(salon)
                .collection("ListeUS")
                .document(idUS)
                .set(data,SetOptions.merge());
   }


}
