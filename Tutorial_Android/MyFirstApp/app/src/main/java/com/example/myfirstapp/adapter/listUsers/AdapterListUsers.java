package com.example.myfirstapp.adapter.listUsers;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.models.listUsers.response.ListUsersRemoteResponse;
import com.example.myfirstapp.viewHolders.UsersViewHolder;

/**
 * Created by taqtile on 5/5/17.
 */

public class AdapterListUsers extends RecyclerView.Adapter<UsersViewHolder> {

    private ListUsersRemoteResponse listUsersRemoteResponse;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder

   /* public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }*/

    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterListUsers(ListUsersRemoteResponse listUsersRemoteResponse) {
        this.listUsersRemoteResponse = listUsersRemoteResponse;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return listUsersRemoteResponse.getData().size();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public UsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View contentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_users, parent, false);

        return new UsersViewHolder(contentView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(UsersViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.setText(listUsersRemoteResponse.getData().get(position).getName());

    }

}
