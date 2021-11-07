package com.openclassrooms.firebaseoc.ui.planningPoker;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.openclassrooms.firebaseoc.databinding.ItemNotesBinding;
import com.openclassrooms.firebaseoc.models.Note;

public class PlanningPokerViewHolder extends RecyclerView.ViewHolder {

    private ItemNotesBinding binding;


    public PlanningPokerViewHolder(@NonNull View itemView) {
        super(itemView);

        binding = ItemNotesBinding.bind(itemView);

    }

    public void updateWithMessage(Note note){

        // Update message
        binding.messageTextView.setText(note.getValue());
        binding.textView3.setText(note.getAutor());

    }



}
