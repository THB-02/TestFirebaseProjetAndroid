package com.openclassrooms.firebaseoc.repository;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.openclassrooms.firebaseoc.manager.UserManager;
import com.openclassrooms.firebaseoc.models.Message;
import com.openclassrooms.firebaseoc.models.Salon;
import com.openclassrooms.firebaseoc.models.User;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GroupRepository {

private static final String SALONS_COLLECTION = "salons";

private static volatile GroupRepository instance;
private UserManager userManager;
private Salon salon;

private GroupRepository() { this.userManager = UserManager.getInstance(); }


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

public CollectionReference getGroupCollection(){
    return FirebaseFirestore.getInstance().collection(SALONS_COLLECTION);
}

public Query getAllRooms(){
    return  FirebaseFirestore.getInstance().collection("salons").whereEqualTo("members."+userManager.getCurrentUser().getUid().toString(), userManager.getCurrentUser().getUid()).orderBy("dateCreated", Query.Direction.DESCENDING);
}


// Create salon in Firestore
public void createGroup(String nom) {
    userManager.getUserData().addOnSuccessListener(user -> {
        // Create the Message object
        Map<String, String> members = new HashMap<>();
        members.put('"'+user.getUid().toString()+'"', user.getUid().toString());
        Salon salon = new Salon(nom,user.getUid());
        salon.setMembers(members);

        this.getGroupCollection().add(salon);

    });

    }

    public Query getLastSalon(){
        return FirebaseFirestore.getInstance().collection("salons")
                .orderBy("dateCreated", Query.Direction.DESCENDING)
                .limit(1);
    }

    public void addScrum (String uidSalon, String uidUSer){
        userManager.getUserData().addOnSuccessListener(user -> {

            FirebaseFirestore.getInstance().collection("salons")
                    .document(uidSalon).collection("members").document(user.getUid()).set(user);


        });
    }




}
