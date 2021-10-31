package com.openclassrooms.firebaseoc.ui.planningPoker;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.openclassrooms.firebaseoc.R;
import com.openclassrooms.firebaseoc.models.Note;
import com.openclassrooms.firebaseoc.models.Salon;
import com.openclassrooms.firebaseoc.ui.groups.GroupAdapter;
import com.openclassrooms.firebaseoc.ui.groups.GroupsViewHolder;

public class PlanningPokerAdapter extends FirestoreRecyclerAdapter<Note, PlanningPokerViewHolder> {

    public interface Listener {
        void onDataChanged();
    }

    // VIEW TYPES

    private PlanningPokerAdapter.Listener callback;

    public PlanningPokerAdapter(@NonNull FirestoreRecyclerOptions<Note> options, PlanningPokerAdapter.Listener callback) {
        super(options);
        this.callback = callback;
    }

    @Override
    protected void onBindViewHolder(@NonNull PlanningPokerViewHolder holder, int position, @NonNull Note model) {
        holder.itemView.invalidate();
        holder.updateWithMessage(model);
    }

    @Override
    public PlanningPokerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PlanningPokerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_groups, parent, false));
    }

    @Override
    public void onDataChanged() {
        super.onDataChanged();
        this.callback.onDataChanged();
    }

}
