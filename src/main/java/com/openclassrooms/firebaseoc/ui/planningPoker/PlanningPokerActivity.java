package com.openclassrooms.firebaseoc.ui.planningPoker;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.openclassrooms.firebaseoc.R;
import com.openclassrooms.firebaseoc.databinding.ActivityPlanningPokerBinding;
import com.openclassrooms.firebaseoc.manager.GroupManager;
import com.openclassrooms.firebaseoc.manager.PlanningPokerManager;
import com.openclassrooms.firebaseoc.manager.UserManager;
import com.openclassrooms.firebaseoc.models.Note;
import com.openclassrooms.firebaseoc.models.Salon;
import com.openclassrooms.firebaseoc.models.US;
import com.openclassrooms.firebaseoc.models.User;
import com.openclassrooms.firebaseoc.ui.BaseActivity;
import com.openclassrooms.firebaseoc.ui.groups.GroupAdapter;


public class PlanningPokerActivity extends BaseActivity<ActivityPlanningPokerBinding> implements PlanningPokerAdapter.Listener{

    private String salon;
    private String scrum;
    private PlanningPokerAdapter planningPokerAdapter;
    private String currentChatName;

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
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_us,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){

        if(item.getItemId()==R.id.copy_menu){
                    ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("idRoom",salon);
                    clipboardManager.setPrimaryClip(clip);
                    Toast.makeText(PlanningPokerActivity.this, "Clé du salon copié !", Toast.LENGTH_SHORT).show();


            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupListeners(){

        String userId = userManager.getCurrentUser().getUid();
        String username = userManager.getCurrentUser().getDisplayName();
        Log.e("test","0");
        planningPokerManager.getLastUS(salon).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(value.size() > 0){
                    for(QueryDocumentSnapshot document : value) {
                        Log.e("test", "2");
                        Log.e("test", "3");
                        US us = document.toObject(US.class);
                        String idUS = document.getId();
                        configureRecyclerView(salon, idUS);
                        binding.textUs.setText(us.getEnonce());

                        if (us.getNotes().containsKey(username)) {
                            binding.btnNotes.setVisibility(View.GONE);
                            binding.btnModifReponse.setVisibility(View.VISIBLE);
                            binding.resultNotes.setVisibility(View.VISIBLE);
                            if (scrum.equals(userId)) {
                                binding.btnUsSuivante.setVisibility(View.VISIBLE);
                            }
                        } else {
                            binding.btnModifReponse.setVisibility(View.GONE);
                            binding.btnNotes.setVisibility(View.VISIBLE);
                            binding.resultNotes.setVisibility(View.GONE);
                        }

                        binding.btnModifReponse.setOnClickListener(view -> {
                            binding.btnModifReponse.setVisibility(View.GONE);
                            binding.btnNotes.setVisibility(View.VISIBLE);
                            binding.btnUsSuivante.setVisibility(View.GONE);
                            binding.resultNotes.setVisibility(View.GONE);
                        });

                        binding.button0.setOnClickListener(view -> {
                            binding.btnNotes.setVisibility(View.GONE);
                            binding.btnModifReponse.setVisibility(View.VISIBLE);
                            binding.resultNotes.setVisibility(View.VISIBLE);

                            planningPokerManager.addNote(username, salon, idUS, "0");
                            if (scrum.equals(userId)) {
                                binding.btnUsSuivante.setVisibility(View.VISIBLE);
                            }
                        });
                        binding.button05.setOnClickListener(view -> {
                            binding.btnNotes.setVisibility(View.GONE);
                            binding.btnModifReponse.setVisibility(View.VISIBLE);
                            binding.resultNotes.setVisibility(View.VISIBLE);
                            planningPokerManager.addNote(username, salon, idUS, "0.5");
                            if (scrum.equals(userId)) {
                                binding.btnUsSuivante.setVisibility(View.VISIBLE);
                            }
                        });
                        binding.button1.setOnClickListener(view -> {
                            binding.btnNotes.setVisibility(View.GONE);
                            binding.btnModifReponse.setVisibility(View.VISIBLE);
                            binding.resultNotes.setVisibility(View.VISIBLE);
                            planningPokerManager.addNote(username, salon, idUS, "1");
                            if (scrum.equals(userId)) {
                                binding.btnUsSuivante.setVisibility(View.VISIBLE);
                            }
                        });
                        binding.button2.setOnClickListener(view -> {
                            binding.btnNotes.setVisibility(View.GONE);
                            binding.btnModifReponse.setVisibility(View.VISIBLE);
                            binding.resultNotes.setVisibility(View.VISIBLE);
                            planningPokerManager.addNote(username, salon, idUS, "2");
                            if (scrum.equals(userId)) {
                                binding.btnUsSuivante.setVisibility(View.VISIBLE);
                            }
                        });
                        binding.button3.setOnClickListener(view -> {
                            binding.btnNotes.setVisibility(View.GONE);
                            binding.btnModifReponse.setVisibility(View.VISIBLE);
                            binding.resultNotes.setVisibility(View.VISIBLE);
                            planningPokerManager.addNote(username, salon, idUS, "3");
                            if (scrum.equals(userId)) {
                                binding.btnUsSuivante.setVisibility(View.VISIBLE);
                            }
                        });
                        binding.button5.setOnClickListener(view -> {
                            binding.btnNotes.setVisibility(View.GONE);
                            binding.btnModifReponse.setVisibility(View.VISIBLE);
                            binding.resultNotes.setVisibility(View.VISIBLE);
                            planningPokerManager.addNote(username, salon, idUS, "5");
                            if (scrum.equals(userId)) {
                                binding.btnUsSuivante.setVisibility(View.VISIBLE);
                            }
                        });
                        binding.button8.setOnClickListener(view -> {
                            binding.btnNotes.setVisibility(View.GONE);
                            binding.btnModifReponse.setVisibility(View.VISIBLE);
                            binding.resultNotes.setVisibility(View.VISIBLE);
                            planningPokerManager.addNote(username, salon, idUS, "8");
                            if (scrum.equals(userId)) {
                                binding.btnUsSuivante.setVisibility(View.VISIBLE);
                            }
                        });
                        binding.button13.setOnClickListener(view -> {
                            binding.btnNotes.setVisibility(View.GONE);
                            binding.btnModifReponse.setVisibility(View.VISIBLE);
                            binding.resultNotes.setVisibility(View.VISIBLE);
                            planningPokerManager.addNote(username, salon, idUS, "13");
                            if (scrum.equals(userId)) {
                                binding.btnUsSuivante.setVisibility(View.VISIBLE);
                            }
                        });
                        binding.button20.setOnClickListener(view -> {
                            binding.btnNotes.setVisibility(View.GONE);
                            binding.btnModifReponse.setVisibility(View.VISIBLE);
                            binding.resultNotes.setVisibility(View.VISIBLE);
                            planningPokerManager.addNote(username, salon, idUS, "20");
                            if (scrum.equals(userId)) {
                                binding.btnUsSuivante.setVisibility(View.VISIBLE);
                            }
                        });
                        binding.button40.setOnClickListener(view -> {
                            binding.btnNotes.setVisibility(View.GONE);
                            binding.btnModifReponse.setVisibility(View.VISIBLE);
                            binding.resultNotes.setVisibility(View.VISIBLE);
                            planningPokerManager.addNote(username, salon, idUS, "40");
                            if (scrum.equals(userId)) {
                                binding.btnUsSuivante.setVisibility(View.VISIBLE);
                            }
                        });
                        binding.button100.setOnClickListener(view -> {
                            binding.btnNotes.setVisibility(View.GONE);
                            binding.btnModifReponse.setVisibility(View.VISIBLE);
                            binding.resultNotes.setVisibility(View.VISIBLE);
                            planningPokerManager.addNote(username, salon, idUS, "100");
                            if (scrum.equals(userId)) {
                                binding.btnUsSuivante.setVisibility(View.VISIBLE);
                            }
                        });
                        binding.buttonInterrogation.setOnClickListener(view -> {
                            binding.btnNotes.setVisibility(View.GONE);
                            binding.btnModifReponse.setVisibility(View.VISIBLE);
                            binding.resultNotes.setVisibility(View.VISIBLE);
                            planningPokerManager.addNote(username, salon, idUS, "?");
                            if (scrum.equals(userId)) {
                                binding.btnUsSuivante.setVisibility(View.VISIBLE);
                            }
                        });

                        binding.btnUsSuivante.setOnClickListener(view -> {
                            planningPokerManager.finishUS(salon, idUS, true);
                            binding.messageContainer.setVisibility(View.VISIBLE);
                        });

                        if (us.isFinished()) {
                            binding.btnNotes.setVisibility(View.GONE);
                            binding.btnModifReponse.setVisibility(View.GONE);
                            binding.btnUsSuivante.setVisibility(View.GONE);
                            binding.resultNotes.setVisibility(View.GONE);
                            binding.textUs.setText("Le scrum master a fermé les votes.\nEn attente d'une nouvelle US..");
                        } else {
                            binding.messageContainer.setVisibility(View.GONE);
                        }
                    }
                }
                else{
                    binding.btnNotes.setVisibility(View.GONE);
                    binding.btnModifReponse.setVisibility(View.GONE);
                    binding.btnUsSuivante.setVisibility(View.GONE);
                }
            }
        });

        if(scrum.equals(userId)){
            binding.messageContainer.setVisibility(View.VISIBLE);
            binding.btnUsSuivante.setVisibility(View.VISIBLE);
        }
        else{
            binding.btnUsSuivante.setVisibility(View.GONE);
            binding.messageContainer.setVisibility(View.GONE);
        }

        if(binding.textUs.getText().equals(R.string.nom_us)){
            Log.e("string",String.valueOf(binding.textUs.getText().equals("En attente d'US du scrum master..")));
            binding.btnModifReponse.setVisibility(View.GONE);
            binding.btnNotes.setVisibility(View.GONE);
            binding.btnVoirNotes.setVisibility(View.GONE);
            binding.btnUsSuivante.setVisibility(View.GONE);
            binding.resultNotes.setVisibility(View.GONE);
        }

        // Send button
        binding.sendButton.setOnClickListener(view -> {
            sendUS();
        });


    }

    // Configure RecyclerView
    private void configureRecyclerView(String chatName, String idUS) {
        //Track current chat name
        this.currentChatName = chatName;
        //Configure Adapter & RecyclerView
        this.planningPokerAdapter = new PlanningPokerAdapter(
                generateOptionsForAdapter(planningPokerManager.getAllNotes(salon,idUS)),
                this);

        binding.resultNotes.setLayoutManager(new GridLayoutManager(this,3,GridLayoutManager.VERTICAL,false));
        binding.resultNotes.setAdapter(this.planningPokerAdapter);
    }

    // Create options for RecyclerView from a Query
    private FirestoreRecyclerOptions<Note> generateOptionsForAdapter(Query query) {
        return new FirestoreRecyclerOptions.Builder<Note>()
                .setQuery(query, Note.class)
                .setLifecycleOwner(this)
                .build();
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


    @Override
    public void onDataChanged() {

    }
}
