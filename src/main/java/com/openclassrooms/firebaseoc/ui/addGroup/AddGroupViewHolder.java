package com.openclassrooms.firebaseoc.ui.addGroup;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.firebaseoc.R;
import com.openclassrooms.firebaseoc.databinding.ItemGroupsBinding;
import com.openclassrooms.firebaseoc.models.User;

public class AddGroupViewHolder extends RecyclerView.ViewHolder  {

    private ItemGroupsBinding binding;

    private final int color;

    private boolean isSender;

    public AddGroupViewHolder(@NonNull View itemView) {
        super(itemView);

        binding = ItemGroupsBinding.bind(itemView);

        // Setup default colors
        color = ContextCompat.getColor(itemView.getContext(), R.color.colorAccent);
    }

    public void updateWithMessage(User user){

        // Update message
        binding.messageTextView.setText(user.getUsername());

    }

}
