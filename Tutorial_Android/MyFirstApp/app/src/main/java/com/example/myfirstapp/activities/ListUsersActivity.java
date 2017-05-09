package com.example.myfirstapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.myfirstapp.R;
import com.example.myfirstapp.adapter.listUsers.AdapterListUsers;
import com.example.myfirstapp.adapter.listUsers.ListenerListUsers;
import com.example.myfirstapp.models.listUsers.response.ListUsersRemoteResponse;
import com.example.myfirstapp.models.signIn.response.SignInRemoteResponse;
import com.example.myfirstapp.providers.listUsers.ListUsersProvider;
import com.example.myfirstapp.providers.listUsers.Pagination;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListUsersActivity extends AppCompatActivity implements ListenerListUsers{

    public static final String EXTRA_ID = "id";
    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_EMAIL = "email";
    public static final String EXTRA_CREATED = "createdAt";
    public static final String EXTRA_UPDATED = "updatedAt";
    public static final String EXTRA_TOKEN = "token";

    private RecyclerView mRecyclerView;
    private AdapterListUsers mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ListUsersProvider listUsersProvider;

    private ListUsersRemoteResponse listUsersRemoteResponse;

    private boolean flag;

    String token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_users);

        flag = true;
        listUsersProvider = new ListUsersProvider();
        //token = getIntent().getStringExtra(SignInActivity.USER_EXTRA);
        SharedPreferences sharedPreferences = getSharedPreferences(SignInActivity.PREF_TOKEN, MODE_PRIVATE);
        token = sharedPreferences.getString("token", "Empty token");
        if(token.equals("Empty token")){
            Toast.makeText(this, "Token incorreto", Toast.LENGTH_LONG).show();
        } else {
            buildListUsers(token, new Pagination(1, 1));
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

    private void listError(Response<ListUsersRemoteResponse> response) {

        Toast.makeText(this, "erro " + response.code(), Toast.LENGTH_LONG).show();

    }

    private void listSuccess(ListUsersRemoteResponse listUsersRemoteResponse) {

        this.listUsersRemoteResponse = new ListUsersRemoteResponse(listUsersRemoteResponse);

        Toast.makeText(this, "funcionou",Toast.LENGTH_LONG).show();

        if(flag){
            flag = false;
            buildListUsers(token, new Pagination(1, listUsersRemoteResponse.getPagination().getTotal()));
        }
        else {
            setupRecyclerView();
        }

    }

    private void setupRecyclerView() {

        mRecyclerView = (RecyclerView) findViewById(R.id.activity_list_users_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(ListUsersActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new AdapterListUsers(listUsersRemoteResponse);

        mAdapter.setListener(this);

        mRecyclerView.setAdapter(mAdapter);

    }

    private void listFailure(){

        Toast.makeText(this, "Connection error", Toast.LENGTH_LONG).show();
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
