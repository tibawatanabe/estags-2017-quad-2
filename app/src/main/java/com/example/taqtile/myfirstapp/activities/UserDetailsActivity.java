package com.example.taqtile.myfirstapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taqtile.myfirstapp.R;
import com.example.taqtile.myfirstapp.models.UserDataRemoteResponse;
import com.example.taqtile.myfirstapp.models.editUser.EditUserRemoteRequest;
import com.example.taqtile.myfirstapp.providers.getUser.GetUserProvider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserDetailsActivity extends AppCompatActivity {
    private String token;
    private int userId;

    private GetUserProvider getUserProvider;


    private String userName;
    private String userEmail;
    private String userType;
    private String userCreatedAt;
    private String userUpdatedAt;


    private TextView idTextView;
    private TextView nameTextView;
    private TextView emailTextView;
    private TextView typeTextView;
    private TextView createdAtTextView;
    private TextView updatedAtTextView;
    private Button editButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        getToken();
        userId = getIntent().getIntExtra("userId", -1);
        if(userId == -1)
            userId = EditUserActivity.id;

        bindViews();
    }

    private void getToken(){
        SharedPreferences prefs = getSharedPreferences(SignInActivity.PREF_TOKEN, MODE_PRIVATE);
        token = prefs.getString("token", "empty_token");

        if (token.equals("empty_token")) {
            Toast.makeText(this, "Erro no token", Toast.LENGTH_LONG).show();
        }
    }

    private void bindViews() {
        idTextView = (TextView) findViewById(R.id.activity_user_details_id);
        nameTextView = (TextView) findViewById(R.id.activity_user_details_name);
        emailTextView = (TextView) findViewById(R.id.activity_user_details_email);
        typeTextView = (TextView) findViewById(R.id.activity_user_details_type);
        createdAtTextView = (TextView) findViewById(R.id.activity_user_details_created_at);
        updatedAtTextView = (TextView) findViewById(R.id.activity_user_details_updated_at);
        editButton = (Button) findViewById(R.id.activity_user_details_edit_button);

        setup();

    }

    private void setViews(){
        idTextView.setText(String.valueOf(userId));
        nameTextView.setText(userName);
        emailTextView.setText(userEmail);
        typeTextView.setText(userType);
        createdAtTextView.setText(userCreatedAt);
        updatedAtTextView.setText(userUpdatedAt);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEditClick();
            }
        });
    }

    private void setup() {
        getUserProvider = new GetUserProvider();

        getUserProvider.performRequest(token, userId, new Callback<UserDataRemoteResponse>() {
            @Override
            public void onResponse(Call<UserDataRemoteResponse> call, Response<UserDataRemoteResponse> response) {
                if (response.isSuccessful()) {
                    userName = response.body().getData().getName();
                    userEmail = response.body().getData().getEmail();
                    userType = response.body().getData().getType();
                    userCreatedAt = response.body().getData().getCreatedAt();
                    userUpdatedAt = response.body().getData().getUpdatedAt();
                    setViews();
                } else {
                    Toast.makeText(UserDetailsActivity.this, "Error: " + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserDataRemoteResponse> call, Throwable t) {
                Toast.makeText(UserDetailsActivity.this, "Erro de conexão com o servidor", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void onEditClick(){
        Intent intent = new Intent(UserDetailsActivity.this, EditUserActivity.class);
        intent.putExtra("userId", userId);
        intent.putExtra("userName", userName);
        intent.putExtra("userEmail", userEmail);
        startActivity(intent);
    }
}
