package com.example.taqtile.myfirstapp.adapters.listUserAdapters;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.taqtile.myfirstapp.R;
import com.example.taqtile.myfirstapp.activities.SignInActivity;
import com.example.taqtile.myfirstapp.models.UserRemoteResponse;
import com.example.taqtile.myfirstapp.models.deleteUser.DeleteUserRemoteResponse;
import com.example.taqtile.myfirstapp.providers.deleteUser.DeleteUserProvider;
import com.example.taqtile.myfirstapp.reclycerView.viewHolders.ListUsersViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by taqtile on 5/5/17.
 */

public class ListUsersAdapter extends RecyclerView.Adapter<ListUsersViewHolder> {

    private static final int PENDING_REMOVAL_TIMEOUT = 2000; // 3sec

    private DeleteUserProvider deleteUserProvider;

    private String token;

    private List<UserRemoteResponse> usersList;
    private List<UserRemoteResponse> usersListPendingRemoval;

    boolean undoOn = true; // is undo on, you can turn it on from the toolbar menu

    private UserListListener listener;

    private Handler handler = new Handler(); // hanlder for running delayed runnables
    HashMap<UserRemoteResponse, Runnable> pendingRunnables = new HashMap<>(); // map of items to pending runnables, so we can cancel a removal if need be


    // Provide a suitable constructor (depends on the kind of dataset)
    public ListUsersAdapter(List<UserRemoteResponse> myDataset, String token) {
        this.usersList = myDataset;
        this.token = token;
        usersListPendingRemoval = new ArrayList<UserRemoteResponse>();
        deleteUserProvider = new DeleteUserProvider();
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return usersList.size();
    }

    @Override
    public ListUsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View contentView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_holder_list_users, parent, false);

        return new ListUsersViewHolder(contentView);
    }

    @Override
    public void onBindViewHolder(ListUsersViewHolder holder, int position) {
        final UserRemoteResponse item = usersList.get(position);
        final int pos = holder.getAdapterPosition();


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onUserClick(pos);
                }
            }
        });

        if (usersListPendingRemoval != null && usersListPendingRemoval.contains(item)) {
            // we need to show the "undo" state of the row
            holder.itemView.setBackgroundColor(Color.RED);
            holder.nameTextView.setVisibility(View.GONE);
            holder.idTextView.setVisibility(View.GONE);
            holder.emailTextView.setVisibility(View.GONE);
            holder.undoButton.setVisibility(View.VISIBLE);
            holder.undoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // user wants to undo the removal, let's cancel the pending task
                    Runnable pendingRemovalRunnable = pendingRunnables.get(item);
                    pendingRunnables.remove(item);
                    if (pendingRemovalRunnable != null)
                        handler.removeCallbacks(pendingRemovalRunnable);
                    usersListPendingRemoval.remove(item);
                    // this will rebind the row in "normal" state
                    notifyItemChanged(usersList.indexOf(item));
                }
            });
        } else {
            // we need to show the "normal" state
            holder.itemView.setBackgroundColor(Color.WHITE);

            holder.nameTextView.setVisibility(View.VISIBLE);
            holder.idTextView.setVisibility(View.VISIBLE);
            holder.emailTextView.setVisibility(View.VISIBLE);


            holder.setNameTextView(usersList.get(position).getName());
            holder.setIdTextView(String.valueOf(usersList.get(position).getId()));
            holder.setEmailTextView(usersList.get(position).getEmail());


            holder.undoButton.setVisibility(View.GONE);
            holder.undoButton.setOnClickListener(null);
        }


    }

    public void setListener(UserListListener listener) {
        this.listener = listener;
    }

    public void pendingRemoval(int position) {
        final UserRemoteResponse item = usersList.get(position);

        if (!usersListPendingRemoval.contains(item)) {
            usersListPendingRemoval.add(item);
            // this will redraw row in "undo" state
            notifyItemChanged(position);
            // let's create, store and post a runnable to remove the item
            Runnable pendingRemovalRunnable = new Runnable() {
                @Override
                public void run() {
                    remove(usersList.indexOf(item));
                }
            };
            handler.postDelayed(pendingRemovalRunnable, PENDING_REMOVAL_TIMEOUT);
            pendingRunnables.put(item, pendingRemovalRunnable);
        }
    }

    public void remove(int position) {
        UserRemoteResponse item = usersList.get(position);

        if (usersListPendingRemoval.contains(item)) {
            deleteUserProvider.performRequest(token, item.getId(), new Callback<DeleteUserRemoteResponse>() {
                @Override
                public void onResponse(Call<DeleteUserRemoteResponse> call, Response<DeleteUserRemoteResponse> response) {
                    if (response.isSuccessful()) {
                        Log.d("TAG1", "Deu certin!");
                    } else {
                        Log.d("TAG2", "Deu errado: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<DeleteUserRemoteResponse> call, Throwable t) {
                    Log.d("TAG1", "Erro de conexão com o servidor" + t.toString());
                }
            });
            usersListPendingRemoval.remove(item);
        }
        if (usersList.contains(item)) {
            usersList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public boolean isPendingRemoval(int position) {
        UserRemoteResponse item = usersList.get(position);

        return usersListPendingRemoval.contains(item);
    }
}
