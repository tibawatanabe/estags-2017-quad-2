package com.example.taqtile.myfirstapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import com.example.taqtile.myfirstapp.R;
import com.example.taqtile.myfirstapp.adapters.listUserAdapters.ListUsersAdapter;
import com.example.taqtile.myfirstapp.adapters.listUserAdapters.UserListListener;
import com.example.taqtile.myfirstapp.helpers.UserListAnimationDecoratorHelper;
import com.example.taqtile.myfirstapp.helpers.UserListItemTouchHelper;
import com.example.taqtile.myfirstapp.models.PaginationRemoteRequest;
import com.example.taqtile.myfirstapp.models.UserRemoteResponse;
import com.example.taqtile.myfirstapp.models.listUsers.ListUsersRemoteResponse;
import com.example.taqtile.myfirstapp.providers.listUsers.ListUsersProvider;
import com.example.taqtile.myfirstapp.reclycerView.endlessScrolling.EndlessRecyclerViewScrollListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListUsersActivity extends AppCompatActivity implements UserListListener {

    private EndlessRecyclerViewScrollListener scrollListener;

    private RecyclerView mRecyclerView;
    private FloatingActionButton addUserFab;
    private String token;
    private ListUsersAdapter mAdapter;
    private ListUsersProvider listUsersProvider;
    private PaginationRemoteRequest paginationRemoteRequest;
    private List<UserRemoteResponse> usersList = new ArrayList<UserRemoteResponse>();
    private LinearLayoutManager mLayoutManager;
    private UserListItemTouchHelper userListItemTouchHelper;
    private UserListAnimationDecoratorHelper userListAnimationDecoratorHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_users);
        getToken();

        listUsersProvider = new ListUsersProvider();
        finalRequest(1);
    }

    private void getToken(){
        SharedPreferences prefs = getSharedPreferences(SignInActivity.PREF_TOKEN, MODE_PRIVATE);
        token = prefs.getString("token", "empty_token");

        if (token.equals("empty_token")) {
            Toast.makeText(this, "Erro no token", Toast.LENGTH_LONG).show();
        }
    }

    private void bindViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.activity_list_reclycler_view);
        addUserFab = (FloatingActionButton) findViewById(R.id.activity_list_add_user_fab);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        addUserFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUserClicked();
            }
        });

        scrollListener = new

                EndlessRecyclerViewScrollListener(mLayoutManager) {
                    @Override
                    public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                        // Triggered only when new data needs to be appended to the list
                        // Add whatever code is needed to append new items to the bottom of the list
                        loadNextDataFromApi(page);
                    }
                }

        ;
        // Adds the scroll listener to RecyclerView
        mRecyclerView.addOnScrollListener(scrollListener);

        setupAdapter();

    }

    private void addUserClicked() {
        Intent intent = new Intent(this, CreateUserActivity.class);
        startActivity(intent);
    }

    // Append the next page of data into the adapter
    // This method probably sends out a network request and appends new data items to your adapter.
    public void loadNextDataFromApi(int offset) {
        // Send an API request to retrieve appropriate paginated data
        //  --> Send the request including an offset value (i.e `page`) as a query parameter.
        //  --> Deserialize and construct new model objects from the API response
        //  --> Append the new data objects to the existing set of items inside the array of items
        //  --> Notify the adapter of the new items made with `notifyItemRangeInserted()`
        paginationRemoteRequest = new PaginationRemoteRequest();
        paginationRemoteRequest.setPage(offset);
        paginationRemoteRequest.setWindow(15);

        listUsersProvider.performRequest(token, paginationRemoteRequest, new Callback<ListUsersRemoteResponse>() {
            @Override
            public void onResponse(Call<ListUsersRemoteResponse> call, Response<ListUsersRemoteResponse> response) {
                if (response.isSuccessful()) {
                    usersList.addAll(response.body().getListUsersDataRemoteResponse());
                    mAdapter.notifyItemRangeInserted(mAdapter.getItemCount(), usersList.size() - 1);
                } else {
                    Toast.makeText(ListUsersActivity.this, "erro " + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ListUsersRemoteResponse> call, Throwable t) {
                Toast.makeText(ListUsersActivity.this, "Falha na conexão com o servidor.", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void finalRequest(int page) {
        paginationRemoteRequest = new PaginationRemoteRequest();
        paginationRemoteRequest.setPage(page);
        paginationRemoteRequest.setWindow(15);

        listUsersProvider.performRequest(token, paginationRemoteRequest, new Callback<ListUsersRemoteResponse>() {
            @Override
            public void onResponse(Call<ListUsersRemoteResponse> call, Response<ListUsersRemoteResponse> response) {
                if (response.isSuccessful()) {
                    usersList = response.body().getListUsersDataRemoteResponse();
                    bindViews();
                } else {
                    Toast.makeText(ListUsersActivity.this, "erro " + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ListUsersRemoteResponse> call, Throwable t) {
                int b;
            }
        });
    }

    private void setupAdapter() {
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ListUsersAdapter(usersList, token);

        userListItemTouchHelper = new UserListItemTouchHelper();
        userListItemTouchHelper.setActivity(this);
        userListItemTouchHelper.setmAdapter(mAdapter);
        userListItemTouchHelper.setmRecyclerView(mRecyclerView);

        mAdapter.setListener(this);
        mRecyclerView.setAdapter(mAdapter);
        userListItemTouchHelper.setupItemTouchHelper();

        userListAnimationDecoratorHelper = new UserListAnimationDecoratorHelper();
        userListAnimationDecoratorHelper.setmRecyclerView(mRecyclerView);

    }

    @Override
    public void onUserClick(int position) {
        usersList.get(position);

        Intent intent = new Intent(this, UserDetailsActivity.class);
        intent.putExtra("token", token);
        intent.putExtra("userId", usersList.get(position).getId());
        intent.putExtra("userName", usersList.get(position).getName());
        intent.putExtra("userEmail", usersList.get(position).getEmail());
        intent.putExtra("userType", usersList.get(position).getType());
        intent.putExtra("userCreatedAt", usersList.get(position).getCreatedAt());
        intent.putExtra("userUpdatedAt", usersList.get(position).getUpdatedAt());
        startActivity(intent);
    }
}
