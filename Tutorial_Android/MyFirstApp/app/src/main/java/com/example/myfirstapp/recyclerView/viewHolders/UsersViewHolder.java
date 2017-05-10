package com.example.myfirstapp.recyclerView.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.myfirstapp.R;

/**
 * Created by taqtile on 5/5/17.
 */

public class UsersViewHolder extends RecyclerView.ViewHolder {

    private TextView name, email, id;

    public UsersViewHolder(View itemView) {
        super(itemView);
        bindViews();
    }

    private void bindViews() {

        name = (TextView) itemView.findViewById(R.id.view_holder_users_text_view);
        email = (TextView) itemView.findViewById(R.id.view_holder_email_text_view);
        id = (TextView) itemView.findViewById(R.id.view_holder_id_text_view);

    }

    public void setTextName(String text){
        name.setText(text);
    }

    public void setTextEmail(String text){
        email.setText(text);
    }

    public void setTextId(String text){
        id.setText(text);
    }

}
