package com.openclassrooms.firebaseoc.ui.usSummary;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.openclassrooms.firebaseoc.databinding.ItemNotesBinding;
import com.openclassrooms.firebaseoc.databinding.ItemUsBinding;
import com.openclassrooms.firebaseoc.models.US;

public class UsSummaryViewHolder extends RecyclerView.ViewHolder{

    private ItemUsBinding binding;


    public UsSummaryViewHolder(@NonNull View itemView) {
        super(itemView);

        binding = ItemUsBinding.bind(itemView);

    }

    public void updateWithMessage(US us){

        // Update message
        binding.messageTextView.setText(us.getEnonce());
        binding.Usnote.setText(us.getNoteFinal());

    }



}
