package com.openclassrooms.firebaseoc.manager;

import com.google.firebase.firestore.Query;
import com.openclassrooms.firebaseoc.repository.GroupRepository;

public class GroupManager {

    private static volatile GroupManager instance;
    private GroupRepository groupRepository;

    private GroupManager() {
        groupRepository = GroupRepository.getInstance();
    }

    public static GroupManager getInstance() {
        GroupManager result = instance;
        if (result != null) {
            return result;
        }
        synchronized(GroupManager.class) {
            if (instance == null) {
                instance = new GroupManager();
            }
            return instance;
        }
    }

    public Query getAllRooms(){
        return groupRepository.getAllRooms();
    }
}
