package com.openclassrooms.firebaseoc.ui.groups;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.openclassrooms.firebaseoc.databinding.ActivityAddGroupBinding;
import com.openclassrooms.firebaseoc.manager.UserManager;
import com.openclassrooms.firebaseoc.ui.BaseActivity;

public class AddGroupActivity extends BaseActivity<ActivityAddGroupBinding> {


    private static final int RC_SIGN_IN = 123;
    private UserManager userManager = UserManager.getInstance();



    @Override
    protected ActivityAddGroupBinding getViewBinding() {
        return ActivityAddGroupBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
