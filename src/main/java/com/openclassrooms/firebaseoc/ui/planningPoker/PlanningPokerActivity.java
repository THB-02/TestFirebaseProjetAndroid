package com.openclassrooms.firebaseoc.ui.planningPoker;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.openclassrooms.firebaseoc.databinding.ActivityPlanningPokerBinding;
import com.openclassrooms.firebaseoc.manager.GroupManager;
import com.openclassrooms.firebaseoc.manager.PlanningPokerManager;
import com.openclassrooms.firebaseoc.manager.UserManager;
import com.openclassrooms.firebaseoc.models.Message;
import com.openclassrooms.firebaseoc.models.US;
import com.openclassrooms.firebaseoc.models.User;
import com.openclassrooms.firebaseoc.ui.BaseActivity;


public class PlanningPokerActivity extends BaseActivity<ActivityPlanningPokerBinding> {

    private String salon;
    private String scrum;

    private UserManager userManager = UserManager.getInstance();
    private PlanningPokerManager planningPokerManager = PlanningPokerManager.getInstance();
    private GroupManager groupManager = GroupManager.getInstance();

    @Override
    protected ActivityPlanningPokerBinding getViewBinding() {
        return ActivityPlanningPokerBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                salon= null;
                scrum= null;
            } else {
                salon= extras.getString("salon");
                scrum= extras.getString("scrum");
            }
        } else {
            salon= (String) savedInstanceState.getSerializable("salon");
            scrum= (String) savedInstanceState.getSerializable("scrum");
        }
        setupListeners();
    }

    private void setupListeners(){

        String userId = userManager.getCurrentUser().getUid();
        String username = userManager.getCurrentUser().getDisplayName();

        planningPokerManager.getLastUS(salon).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                for(QueryDocumentSnapshot document : value){
                    if(document.toObject(US.class) != null){
                        US us = document.toObject(US.class);
                        String idUS = document.getId();
                        binding.textUs.setText(us.getEnonce());

                        if (us.getNotes().containsKey(username)){
                            binding.btnNotes.setVisibility(View.GONE);
                            binding.btnModifReponse.setVisibility(View.VISIBLE);
                            if(scrum.equals(userId)){
                                binding.btnUsSuivante.setVisibility(View.VISIBLE);
                            }
                        }
                        else{
                            binding.btnModifReponse.setVisibility(View.GONE);
                            binding.btnNotes.setVisibility(View.VISIBLE);
                        }

                        binding.btnModifReponse.setOnClickListener(view -> {
                            binding.btnModifReponse.setVisibility(View.GONE);
                            binding.btnNotes.setVisibility(View.VISIBLE);
                            binding.btnUsSuivante.setVisibility(View.GONE);
                        });

                        binding.button0.setOnClickListener(view -> {
                            binding.btnNotes.setVisibility(View.GONE);
                            binding.btnModifReponse.setVisibility(View.VISIBLE);
                            planningPokerManager.addNote(username, salon, idUS, "0");
                            if(scrum.equals(userId)){
                                binding.btnUsSuivante.setVisibility(View.VISIBLE);
                            }
                        });
                        binding.button05.setOnClickListener(view -> {
                            binding.btnNotes.setVisibility(View.GONE);
                            binding.btnModifReponse.setVisibility(View.VISIBLE);
                            planningPokerManager.addNote(username, salon, idUS, "0.5");
                            if(scrum.equals(userId)){
                                binding.btnUsSuivante.setVisibility(View.VISIBLE);
                            }
                        });
                        binding.button1.setOnClickListener(view -> {
                            binding.btnNotes.setVisibility(View.GONE);
                            binding.btnModifReponse.setVisibility(View.VISIBLE);
                            planningPokerManager.addNote(username, salon, idUS, "1");
                            if(scrum.equals(userId)){
                                binding.btnUsSuivante.setVisibility(View.VISIBLE);
                            }
                        });
                        binding.button2.setOnClickListener(view -> {
                            binding.btnNotes.setVisibility(View.GONE);
                            binding.btnModifReponse.setVisibility(View.VISIBLE);
                            planningPokerManager.addNote(username, salon, idUS, "2");
                            if(scrum.equals(userId)){
                                binding.btnUsSuivante.setVisibility(View.VISIBLE);
                            }
                        });
                        binding.button3.setOnClickListener(view -> {
                            binding.btnNotes.setVisibility(View.GONE);
                            binding.btnModifReponse.setVisibility(View.VISIBLE);
                            planningPokerManager.addNote(username, salon, idUS, "3");
                            if(scrum.equals(userId)){
                                binding.btnUsSuivante.setVisibility(View.VISIBLE);
                            }
                        });
                        binding.button5.setOnClickListener(view -> {
                            binding.btnNotes.setVisibility(View.GONE);
                            binding.btnModifReponse.setVisibility(View.VISIBLE);
                            planningPokerManager.addNote(username, salon, idUS, "5");
                            if(scrum.equals(userId)){
                                binding.btnUsSuivante.setVisibility(View.VISIBLE);
                            }
                        });
                        binding.button8.setOnClickListener(view -> {
                            binding.btnNotes.setVisibility(View.GONE);
                            binding.btnModifReponse.setVisibility(View.VISIBLE);
                            planningPokerManager.addNote(username, salon, idUS, "8");
                            if(scrum.equals(userId)){
                                binding.btnUsSuivante.setVisibility(View.VISIBLE);
                            }
                        });
                        binding.button13.setOnClickListener(view -> {
                            binding.btnNotes.setVisibility(View.GONE);
                            binding.btnModifReponse.setVisibility(View.VISIBLE);
                            planningPokerManager.addNote(username, salon, idUS, "13");
                            if(scrum.equals(userId)){
                                binding.btnUsSuivante.setVisibility(View.VISIBLE);
                            }
                        });
                        binding.button20.setOnClickListener(view -> {
                            binding.btnNotes.setVisibility(View.GONE);
                            binding.btnModifReponse.setVisibility(View.VISIBLE);
                            planningPokerManager.addNote(username, salon, idUS, "20");
                            if(scrum.equals(userId)){
                                binding.btnUsSuivante.setVisibility(View.VISIBLE);
                            }
                        });
                        binding.button40.setOnClickListener(view -> {
                            binding.btnNotes.setVisibility(View.GONE);
                            binding.btnModifReponse.setVisibility(View.VISIBLE);
                            planningPokerManager.addNote(username, salon, idUS, "40");
                            if(scrum.equals(userId)){
                                binding.btnUsSuivante.setVisibility(View.VISIBLE);
                            }
                        });
                        binding.button100.setOnClickListener(view -> {
                            binding.btnNotes.setVisibility(View.GONE);
                            binding.btnModifReponse.setVisibility(View.VISIBLE);
                            planningPokerManager.addNote(username, salon, idUS, "100");
                            if(scrum.equals(userId)){
                                binding.btnUsSuivante.setVisibility(View.VISIBLE);
                            }
                        });
                        binding.buttonInterrogation.setOnClickListener(view -> {
                            binding.btnNotes.setVisibility(View.GONE);
                            binding.btnModifReponse.setVisibility(View.VISIBLE);
                            planningPokerManager.addNote(username, salon, idUS, "?");
                            if(scrum.equals(userId)){
                                binding.btnUsSuivante.setVisibility(View.VISIBLE);
                            }
                        });

                        binding.btnUsSuivante.setOnClickListener(view -> {
                            planningPokerManager.finishUS(salon, idUS, true);
                            binding.messageContainer.setVisibility(View.VISIBLE);

                        });

                        if(us.isFinished()){
                            binding.btnNotes.setVisibility(View.GONE);
                            binding.btnModifReponse.setVisibility(View.GONE);
                            binding.btnUsSuivante.setVisibility(View.GONE);
                            binding.textUs.setText("Le scrum master a fermé les votes.\nEn attente d'une nouvelle US..");
                        }
                        else{
                            binding.messageContainer.setVisibility(View.GONE);
                        }
                    }
                }
            }
        });


        if(scrum.equals(userId)){
            binding.btnUsSuivante.setVisibility(View.VISIBLE);
            binding.messageContainer.setVisibility(View.VISIBLE);
        }
        else{
            binding.btnUsSuivante.setVisibility(View.GONE);
            binding.messageContainer.setVisibility(View.GONE);
        }

        if(binding.textUs.getText().equals("En attente d'US du scrum master..")){
            Log.e("string",String.valueOf(binding.textUs.getText().equals("En attente d'US du scrum master..")));
            binding.btnModifReponse.setVisibility(View.GONE);
            binding.btnNotes.setVisibility(View.GONE);
            binding.btnUsSuivante.setVisibility(View.GONE);
        }

        // Send button
        binding.sendButton.setOnClickListener(view -> {
            sendUS();
        });
    }

    private void sendUS(){
        // Check if user can send a message (Text not null + user logged)
        boolean canSendMessage = !TextUtils.isEmpty(binding.chatEditText.getText()) && userManager.isCurrentUserLogged();

        if (canSendMessage){
            // Create a new message for the chat
            planningPokerManager.createUS(binding.chatEditText.getText().toString(), this.salon);
            // Reset text field
            binding.chatEditText.setText("");
        }
    }


}
