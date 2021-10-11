package com.openclassrooms.firebaseoc.ui.addGroup;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.openclassrooms.firebaseoc.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.Query;
import com.openclassrooms.firebaseoc.databinding.ActivityAddGroupBinding;
import com.openclassrooms.firebaseoc.databinding.ActivityGroupsBinding;
import com.openclassrooms.firebaseoc.manager.GroupManager;
import com.openclassrooms.firebaseoc.manager.UserManager;
import com.openclassrooms.firebaseoc.models.Salon;
import com.openclassrooms.firebaseoc.models.User;
import com.openclassrooms.firebaseoc.ui.BaseActivity;
import com.openclassrooms.firebaseoc.ui.chat.MentorChatActivity;
import com.openclassrooms.firebaseoc.ui.groups.GroupAdapter;

import java.util.ArrayList;
import java.util.List;

public class AddGroupActivity extends BaseActivity<ActivityAddGroupBinding> {


        private AddGroupAdapter addGroupAdapter;
        private String currentChatName;

        private static final String SALON_NAME = "salon1";

        private UserManager userManager = UserManager.getInstance();
        private GroupManager groupManager = GroupManager.getInstance();
        public FirebaseFirestore db;
         public Spinner spinner;


        @Override
        protected ActivityAddGroupBinding getViewBinding() {
            return ActivityAddGroupBinding.inflate(getLayoutInflater());
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);

        }

        private void getUsersForSpinner(){
            setContentView(R.layout.activity_add_group);
            List<String> subjects = new ArrayList<>();
            db = FirebaseFirestore.getInstance();
            spinner = (Spinner) findViewById(R.id.fab);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, subjects);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

            db.collection("users")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener < QuerySnapshot > () {
                        @Override
                        public void onComplete(@NonNull Task < QuerySnapshot > task) {
                            if (task.isSuccessful()) {
                                for (DocumentSnapshot document: task.getResult()) {
                                    Log.v("FIRESTORE", document.getId() + " => " + document.get("username"));
                                    String subject = document.getString("username");
                                    subjects.add(subject);

                                }
                                adapter.notifyDataSetChanged();

                            } else {
                                Log.v("WARNING", "Error getting documents.users", task.getException());
                            }
                        }
                    });
        }








}
