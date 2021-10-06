package com.openclassrooms.firebaseoc.ui.groups;

import android.content.Intent;
import android.util.Log;
import android.view.View;


import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;


import com.openclassrooms.firebaseoc.R;
import com.openclassrooms.firebaseoc.databinding.ItemGroupsBinding;
import com.openclassrooms.firebaseoc.models.Salon;
import com.openclassrooms.firebaseoc.ui.chat.MentorChatActivity;


public class GroupsViewHolder extends RecyclerView.ViewHolder {

    private ItemGroupsBinding binding;

    private final int color;

    public GroupsViewHolder(@NonNull View itemView) {
        super(itemView);

        binding = ItemGroupsBinding.bind(itemView);

        // Setup default colors
        color = ContextCompat.getColor(itemView.getContext(), R.color.colorAccent);
    }

    public void updateWithMessage(Salon salon){

        // Update message
        binding.messageTextView.setText(salon.getNom());

    }

    public void openChat(Salon salon){

        itemView.setOnClickListener(view -> {
            Log.e("TEST ----", "    SETONCLICKLISTENER     " + salon.getId());
            Intent intent = new Intent(itemView.getContext(), MentorChatActivity.class);
            intent.putExtra("CHAT_NAME", salon.getId());
            itemView.getContext().startActivity(intent);
        });
    }



}
