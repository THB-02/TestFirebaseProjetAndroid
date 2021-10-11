package com.openclassrooms.firebaseoc.ui.addGroup;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import android.view.ViewGroup;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.openclassrooms.firebaseoc.R;
import com.openclassrooms.firebaseoc.models.User;
public class AddGroupAdapter extends FirestoreRecyclerAdapter<User, AddGroupViewHolder>{

    public interface Listener {
        void onDataChanged();
    }

    // VIEW TYPES

    private AddGroupAdapter.Listener callback;

    public AddGroupAdapter(@NonNull FirestoreRecyclerOptions<User> options, AddGroupAdapter.Listener callback) {
        super(options);
        this.callback = callback;
    }

    @Override
    protected void onBindViewHolder(@NonNull AddGroupViewHolder holder, int position, @NonNull User user) {
        holder.itemView.invalidate();
        holder.updateWithMessage(user);
    }

    @Override
    public AddGroupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AddGroupViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_add_group, parent, false));
    }

    @Override
    public void onDataChanged() {
        super.onDataChanged();
        this.callback.onDataChanged();
    }




}
