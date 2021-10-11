package com.openclassrooms.firebaseoc.ui.createGroup;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.Nullable;

import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.material.snackbar.Snackbar;

import com.openclassrooms.firebaseoc.R;
import com.openclassrooms.firebaseoc.databinding.ActivityCreateGroupBinding;
import com.openclassrooms.firebaseoc.manager.ChatManager;
import com.openclassrooms.firebaseoc.manager.GroupManager;
import com.openclassrooms.firebaseoc.manager.UserManager;

import com.openclassrooms.firebaseoc.ui.BaseActivity;
import com.openclassrooms.firebaseoc.ui.groups.GroupsActivity;

public class CreateGroupActivity extends BaseActivity<ActivityCreateGroupBinding> {

    private UserManager userManager = UserManager.getInstance();
    private GroupManager groupManager = GroupManager.getInstance();
    private ChatManager chatManager = ChatManager.getInstance();



    @Override
    protected ActivityCreateGroupBinding getViewBinding() {
        return ActivityCreateGroupBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupListeners();
    }


    private void setupListeners() {
        binding.createButton.setOnClickListener(view -> {
            createSalon();
        });



    }

    public void createSalon() {

        boolean canCreate = !TextUtils.isEmpty(binding.TextCreate.getText()) && userManager.isCurrentUserLogged();

        if (canCreate){
            // Create a new message for the chat
            groupManager.createGroup(binding.TextCreate.getText().toString());
            // Reset text field
            binding.TextCreate.setText("");
            showSnackBar("Le salon a été crée !");
        }
        else{
            showSnackBar("Vous devez dabord entrer un nomde groupe");
        }
    }
    private void startGroupActivity(){
        Intent intent = new Intent(this, GroupsActivity.class);
        startActivity(intent);
    }

    // Show Snack Bar with a message
    private void showSnackBar( String message){
        Snackbar.make(binding.createLayout, message, Snackbar.LENGTH_SHORT).show();
    }
}
