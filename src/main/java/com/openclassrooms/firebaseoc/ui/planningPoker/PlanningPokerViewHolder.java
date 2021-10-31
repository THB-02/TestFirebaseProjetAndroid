package com.openclassrooms.firebaseoc.ui.planningPoker;

import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.firebaseoc.R;
import com.openclassrooms.firebaseoc.databinding.ItemGroupsBinding;
import com.openclassrooms.firebaseoc.models.Note;
import com.openclassrooms.firebaseoc.models.Salon;

public class PlanningPokerViewHolder extends RecyclerView.ViewHolder {

    private ItemGroupsBinding binding;

    private final int color;

    public PlanningPokerViewHolder(@NonNull View itemView) {
        super(itemView);

        binding = ItemGroupsBinding.bind(itemView);

        // Setup default colors
        color = ContextCompat.getColor(itemView.getContext(), R.color.colorAccent);
    }

    public void updateWithMessage(Note note){

        // Update message
        binding.messageTextView.setText(note.getValue()+"\n"+note.getAutor());

    }



}
