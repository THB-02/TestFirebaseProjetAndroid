package com.openclassrooms.firebaseoc.ui.groups;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.Query;
import com.openclassrooms.firebaseoc.R;
import com.openclassrooms.firebaseoc.databinding.ActivityGroupsBinding;
import com.openclassrooms.firebaseoc.manager.GroupManager;
import com.openclassrooms.firebaseoc.manager.UserManager;
import com.openclassrooms.firebaseoc.models.Salon;
import com.openclassrooms.firebaseoc.ui.BaseActivity;
import com.openclassrooms.firebaseoc.ui.chat.MentorChatActivity;

public class GroupsActivity extends BaseActivity<ActivityGroupsBinding> implements GroupAdapter.Listener {

    private GroupAdapter groupAdapter;
    private String currentChatName;

    private static final String SALON_NAME = "salon1";

    private UserManager userManager = UserManager.getInstance();
    private GroupManager groupManager = GroupManager.getInstance();



    @Override
    protected ActivityGroupsBinding getViewBinding() {
        return ActivityGroupsBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configureRecyclerView(SALON_NAME);
        setupListeners();

    }

    private void setupListeners() {
        binding.fab.setOnClickListener(view -> {

            startAddGroupActivity();
        });
    }

    // Configure RecyclerView
    private void configureRecyclerView(String chatName) {
        //Track current chat name
        this.currentChatName = chatName;
        //Configure Adapter & RecyclerView
        this.groupAdapter = new GroupAdapter(
                generateOptionsForAdapter(groupManager.getAllRooms()),
                this);

        groupAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                binding.chatRecyclerView.smoothScrollToPosition(groupAdapter.getItemCount()); // Scroll to bottom on new messages
            }
        });

        binding.chatRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.chatRecyclerView.setAdapter(this.groupAdapter);
    }

    // Create options for RecyclerView from a Query
    private FirestoreRecyclerOptions<Salon> generateOptionsForAdapter(Query query) {
        return new FirestoreRecyclerOptions.Builder<Salon>()
                .setQuery(query, Salon.class)
                .setLifecycleOwner(this)
                .build();
    }

    public void onDataChanged() {
        // Show TextView in case RecyclerView is empty
        binding.emptyRecyclerView.setVisibility(this.groupAdapter.getItemCount() == 0 ? View.VISIBLE : View.GONE);
    }

    private void startAddGroupsActivity() {
        Intent intent = new Intent(this, MentorChatActivity.class);
        startActivity(intent);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private void startAddGroupActivity() {
        Intent intent = new Intent(this, AddGroupActivity.class);
        startActivity(intent);
    }
}
