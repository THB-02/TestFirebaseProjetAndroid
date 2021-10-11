package com.openclassrooms.firebaseoc.manager;

import com.google.firebase.firestore.Query;
import com.openclassrooms.firebaseoc.models.US;
import com.openclassrooms.firebaseoc.repository.ChatRepository;
import com.openclassrooms.firebaseoc.repository.PlanningPokerRepository;

public class PlanningPokerManager {

    private static volatile PlanningPokerManager instance;
    private PlanningPokerRepository planningPokerRepository;

    private PlanningPokerManager() {
        planningPokerRepository = PlanningPokerRepository.getInstance();
    }

    public static PlanningPokerManager getInstance() {
        PlanningPokerManager result = instance;
        if (result != null) {
            return result;
        }
        synchronized(PlanningPokerManager.class) {
            if (instance == null) {
                instance = new PlanningPokerManager();
            }
            return instance;
        }
    }

    public void createUS(String enonce, String salon){
        planningPokerRepository.createUS(enonce, salon);
    }

    public Query getLastUS(String salon){
        return planningPokerRepository.getLastUS(salon);
    }

}
