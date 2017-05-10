package com.example.myfirstapp.adapter.listUsers;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myfirstapp.R;
import com.example.myfirstapp.models.listUsers.response.ListUsersDataRemoteResponse;
import com.example.myfirstapp.models.listUsers.response.ListUsersRemoteResponse;
import com.example.myfirstapp.recyclerView.viewHolders.UsersViewHolder;

import java.util.List;

/**
 * Created by taqtile on 5/5/17.
 */

public class ListUsersAdapter extends RecyclerView.Adapter<UsersViewHolder> {

    private ListUsersListener listener;

    //private ListUsersRemoteResponse listUsersRemoteResponse;

    private List<ListUsersDataRemoteResponse> userList;

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
    public ListUsersAdapter(List<ListUsersDataRemoteResponse> userList) {
        this.userList = userList;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return userList.size();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public UsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View contentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_users, parent, false);

        return new UsersViewHolder(contentView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(UsersViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.setTextName(userList.get(position).getName());
        holder.setTextEmail(userList.get(position).getEmail());
        holder.setTextId(Integer.toString(userList.get(position).getId()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onUserClick(position);
            }
        });

    }

    public void updateAdapter(List<ListUsersDataRemoteResponse> userListUp){
        userList.addAll(userListUp);
    }

    public void setListener(ListUsersListener listener) {
        this.listener = listener;
    }
}
