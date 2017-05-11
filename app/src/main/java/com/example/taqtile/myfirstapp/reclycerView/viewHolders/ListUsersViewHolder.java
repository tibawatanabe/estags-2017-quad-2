package com.example.taqtile.myfirstapp.reclycerView.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.taqtile.myfirstapp.R;

/**
 * Created by taqtile on 5/5/17.
 */

public class ListUsersViewHolder extends RecyclerView.ViewHolder {

    public TextView nameTextView;
    public TextView idTextView;
    public TextView emailTextView;
    public Button undoButton;

    public ListUsersViewHolder(View itemView) {
        super(itemView);
        bindViews();
    }

    private void bindViews() {
        nameTextView = (TextView) itemView.findViewById(R.id.view_holder_name_text_view);
        idTextView = (TextView) itemView.findViewById(R.id.view_holder_id_text_view);
        emailTextView = (TextView) itemView.findViewById(R.id.view_holder_email_text_view);
        undoButton = (Button) itemView.findViewById(R.id.view_holder_undo_button);

    }

    public void setNameTextView(String text) {
        nameTextView.setText(text);
    }

    public void setIdTextView(String text) {
        idTextView.setText(text);
    }

    public void setEmailTextView(String text) {
        emailTextView.setText(text);
    }
}
