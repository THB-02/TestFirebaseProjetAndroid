package com.openclassrooms.firebaseoc.repository;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.openclassrooms.firebaseoc.manager.UserManager;

public class GroupRepository {

    private static final String SALONS_COLLECTION = "salons";
    private static final String MEMBRES_COLLECTION = "membres";

    private static volatile GroupRepository instance;
    private UserManager userManager;

    private GroupRepository() {  }


    public static GroupRepository getInstance() {
        GroupRepository result = instance;
        if (result != null) {
            return result;
        }
        synchronized(ChatRepository.class) {
            if (instance == null) {
                instance = new GroupRepository();
            }
            return instance;
        }
    }

    public CollectionReference getChatCollection(){
        return FirebaseFirestore.getInstance().collection(SALONS_COLLECTION);
    }

    public Query getAllRooms(){
        return  FirebaseFirestore.getInstance().collection("salons");
    }



}
