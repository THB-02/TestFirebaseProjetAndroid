package com.openclassrooms.firebaseoc.repository;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
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
    return  FirebaseFirestore.getInstance().collection("salons");
}

/*public Query getMyAllRooms(String uid){
    return  FirebaseFirestore
            .getInstance()
            .collection("salons")
            .whereArrayContains(String.valueOf(Arrays.asList("members","scrum")), uid);
    }*/

// Create User in Firestore
public void createGroup(String nom) {
    userManager.getUserData().addOnSuccessListener(user -> {
        // Create the Message object
        /*Map<String, String> member = new HashMap<>();
        member.put(user.getUsername(), user.getUid().toString());
        HashMap<String, Map<String, String>> data = new HashMap<>();
        data.put("members",member);
*/
        String uid = user.getUid();
        Salon salon = new Salon(nom, uid);

        this.getGroupCollection().add(salon);


    });

    }




}
