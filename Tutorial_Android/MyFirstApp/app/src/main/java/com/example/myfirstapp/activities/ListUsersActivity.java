package com.example.myfirstapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.myfirstapp.R;
import com.example.myfirstapp.adapter.listUsers.AdapterListUsers;
import com.example.myfirstapp.models.listUsers.response.ListUsersRemoteResponse;
import com.example.myfirstapp.models.signIn.response.SignInRemoteResponse;
import com.example.myfirstapp.providers.listUsers.ListUsersProvider;
import com.example.myfirstapp.providers.listUsers.Pagination;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListUsersActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
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
        token = getIntent().getStringExtra(SignInActivity.USER_EXTRA);
        buildListUsers(token, new Pagination(1, 10));

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
        mRecyclerView.setAdapter(mAdapter);

    }

    private void listFailure(){

        Toast.makeText(this, "Connection error", Toast.LENGTH_LONG).show();

    }


}
