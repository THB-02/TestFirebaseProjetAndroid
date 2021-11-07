package com.openclassrooms.firebaseoc.ui.usSummary;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import com.google.firebase.firestore.Query;

import com.openclassrooms.firebaseoc.databinding.ActivityUsSummaryBinding;
import com.openclassrooms.firebaseoc.manager.GroupManager;
import com.openclassrooms.firebaseoc.manager.PlanningPokerManager;
import com.openclassrooms.firebaseoc.manager.UserManager;
import com.openclassrooms.firebaseoc.models.US;
import com.openclassrooms.firebaseoc.ui.BaseActivity;
import com.openclassrooms.firebaseoc.ui.planningPoker.PlanningPokerAdapter;

import java.util.Map;

public class UsSummaryActivity extends BaseActivity<ActivityUsSummaryBinding> implements UsSummaryAdapter.Listener {

    private String salon;
    private String scrum;
    private  UsSummaryAdapter usSummaryAdapter;
    private PlanningPokerAdapter planningPokerAdapter;
    private String currentChatName;

    private UserManager userManager = UserManager.getInstance();
    private PlanningPokerManager planningPokerManager = PlanningPokerManager.getInstance();
    private GroupManager groupManager = GroupManager.getInstance();

    @Override
    protected ActivityUsSummaryBinding getViewBinding() {
        return ActivityUsSummaryBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                salon = extras.getString("salon");
                configureRecyclerView(salon);
            }
        }
    }


    // Configure RecyclerView
    private void configureRecyclerView(String chatName) {
        //Track current chat name
        this.currentChatName = chatName;
        //Configure Adapter & RecyclerView
        this.usSummaryAdapter = new UsSummaryAdapter( generateOptionsForAdapter(planningPokerManager.getAllUs(salon)),
                this);

        binding.listeResultat.setLayoutManager(new LinearLayoutManager(this));
        binding.listeResultat.setAdapter(this.usSummaryAdapter);
    }

    // Create options for RecyclerView from a Query
    private FirestoreRecyclerOptions<US> generateOptionsForAdapter(Query query) {
        return new FirestoreRecyclerOptions.Builder<US>()
                .setQuery(query, US.class)
                .setLifecycleOwner(this)
                .build();
    }



    @Override
    public void onDataChanged() {
        binding.emptyRecyclerView2.setVisibility(this.usSummaryAdapter.getItemCount() == 0 ? View.VISIBLE : View.GONE);

    }
}
