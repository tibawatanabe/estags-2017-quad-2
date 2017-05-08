package com.example.myfirstapp.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.myfirstapp.R;

/**
 * Created by taqtile on 5/5/17.
 */

public class UsersViewHolder extends RecyclerView.ViewHolder {

    private TextView textView;

    public UsersViewHolder(View itemView) {
        super(itemView);
        bindViews();
    }

    private void bindViews() {

        textView = (TextView) itemView.findViewById(R.id.view_holder_users_text_view);

    }

    public void setText(String text){
        textView.setText(text);
    }


}
