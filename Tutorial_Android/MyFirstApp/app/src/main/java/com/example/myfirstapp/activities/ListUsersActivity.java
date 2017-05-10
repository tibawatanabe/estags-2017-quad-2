package com.example.myfirstapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.myfirstapp.R;
import com.example.myfirstapp.adapter.listUsers.ListUsersAdapter;
import com.example.myfirstapp.adapter.listUsers.ListUsersListener;
import com.example.myfirstapp.models.listUsers.response.ListUsersDataRemoteResponse;
import com.example.myfirstapp.models.listUsers.response.ListUsersRemoteResponse;
import com.example.myfirstapp.providers.listUsers.ListUsersProvider;
import com.example.myfirstapp.providers.listUsers.Pagination;
import com.example.myfirstapp.recyclerView.endlessScrolling.EndlessRecyclerViewScrollListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListUsersActivity extends AppCompatActivity implements ListUsersListener {

    public static final String EXTRA_ID = "id";
    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_EMAIL = "email";
    public static final String EXTRA_CREATED = "createdAt";
    public static final String EXTRA_UPDATED = "updatedAt";
    public static final String EXTRA_TOKEN = "token";
    public static final int window = 15;

    private RecyclerView mRecyclerView;
    private ListUsersAdapter mAdapter;
    //private RecyclerView.LayoutManager mLayoutManager;
    private LinearLayoutManager mLayoutManager;

    private ListUsersProvider listUsersProvider;

    private ListUsersRemoteResponse listUsersRemoteResponse;

    private List<ListUsersDataRemoteResponse> userList = new ArrayList<ListUsersDataRemoteResponse>();

    String token;

    private EndlessRecyclerViewScrollListener scrollListener;

    private FloatingActionButton addUserFab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_users);

        listUsersProvider = new ListUsersProvider();
        //token = getIntent().getStringExtra(SignInActivity.USER_EXTRA);
        SharedPreferences sharedPreferences = getSharedPreferences(SignInActivity.PREF_TOKEN, MODE_PRIVATE);
        token = sharedPreferences.getString("token", "Empty token");
        if(token.equals("Empty token")){
            Toast.makeText(this, "Token incorreto", Toast.LENGTH_LONG).show();
        } else {
            buildListUsers(token, new Pagination(1, window));
        }

        //SignInRemoteResponse signInRemoteResponse = (SignInRemoteResponse) getIntent().getSerializableExtra(SignInActivity.USER_EXTRA);


    }

    private void buildListUsers(String token, Pagination pagination) {

        listUsersProvider.performRequest(token, pagination, new Callback<ListUsersRemoteResponse>() {
            @Override
            public void onResponse(Call<ListUsersRemoteResponse> call, Response<ListUsersRemoteResponse> response) {
                if(response.isSuccessful()){
                    listSuccess(response.body());
                }
                else{
                    listError(response);
                }

            }

            @Override
            public void onFailure(Call<ListUsersRemoteResponse> call, Throwable t) {

                listFailure();

            }
        });

    }


    private void listSuccess(ListUsersRemoteResponse listUsersRemoteResponse) {

        this.listUsersRemoteResponse = new ListUsersRemoteResponse(listUsersRemoteResponse);

//        Toast.makeText(this, "funcionou",Toast.LENGTH_LONG).show();

        /*if(flag){
            flag = false;
            buildListUsers(token, new Pagination(1, listUsersRemoteResponse.getPagination().getTotal()));
        }
        else {
            setupRecyclerView();
        }*/

        setupRecyclerView();
    }

    private void listError(Response<ListUsersRemoteResponse> response) {

        Toast.makeText(this, "erro " + response.code(), Toast.LENGTH_LONG).show();

    }

    private void listFailure(){

        Toast.makeText(this, "Connection error", Toast.LENGTH_LONG).show();
    }


    private void setupRecyclerView() {

        mRecyclerView = (RecyclerView) findViewById(R.id.activity_list_users_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(ListUsersActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        scrollListener = new EndlessRecyclerViewScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                loadNextDataFromApi(page);
//                view.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        mAdapter.notifyItemRangeInserted(mAdapter.getItemCount(), userList.size()-1);
//                    }
//                });
            }
        };

        mRecyclerView.addOnScrollListener(scrollListener);

        userList = listUsersRemoteResponse.getData();

        mAdapter = new ListUsersAdapter(userList);

        mAdapter.setListener(this);

        mRecyclerView.setAdapter(mAdapter);

        addUserFab = (FloatingActionButton) findViewById(R.id.activity_list_users_add_user_fab);

        addUserFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCreateUserSelected();
            }
        });

    }

    private void onCreateUserSelected() {

        Intent intent = new Intent(this, CreateUserActivity.class);
        startActivity(intent);

    }

    // Append the next page of data into the adapter
    // This method probably sends out a network request and appends new data items to your adapter.
    public void loadNextDataFromApi(int page) {
        // Send an API request to retrieve appropriate paginated data
        //  --> Send the request including an offset value (i.e `page`) as a query parameter.
        //  --> Deserialize and construct new model objects from the API response
        //  --> Append the new data objects to the existing set of items inside the array of items
        //  --> Notify the adapter of the new items made with `notifyItemRangeInserted()`

        listUsersProvider.performRequest(token, new Pagination(page, window), new Callback<ListUsersRemoteResponse>() {
            @Override
            public void onResponse(Call<ListUsersRemoteResponse> call, Response<ListUsersRemoteResponse> response) {
                if(response.isSuccessful()){
                    listSuccessScroll(response);
                } else {
                    listErrorScroll(response);
                }
            }

            @Override
            public void onFailure(Call<ListUsersRemoteResponse> call, Throwable t) {
                listFailureScroll();
            }
        });
    }

    private void listSuccessScroll(Response<ListUsersRemoteResponse> response) {

        userList.addAll(response.body().getData());
//        mAdapter.updateAdapter(response.body().getData());
        mAdapter.notifyItemRangeInserted(mAdapter.getItemCount(), userList.size()-1);

    }

    private void listErrorScroll(Response<ListUsersRemoteResponse> response) {

        Toast.makeText(this, "erro requisao scroll " + response.code(), Toast.LENGTH_LONG).show();

    }

    private void listFailureScroll() {

        Toast.makeText(this, "erro conexao scroll", Toast.LENGTH_LONG).show();

    }


    @Override
    public void onUserClick(int position) {
        Intent intent = new Intent(this, UserDetailsActivity.class);
        intent.putExtra(EXTRA_ID, listUsersRemoteResponse.getData().get(position).getId());
        intent.putExtra(EXTRA_NAME, listUsersRemoteResponse.getData().get(position).getName());
        intent.putExtra(EXTRA_EMAIL, listUsersRemoteResponse.getData().get(position).getEmail());
        intent.putExtra(EXTRA_CREATED, listUsersRemoteResponse.getData().get(position).getCreatedAt());
        intent.putExtra(EXTRA_UPDATED, listUsersRemoteResponse.getData().get(position).getUpdatedAt());
        intent.putExtra(EXTRA_TOKEN, token);
        startActivity(intent);
    }
}
