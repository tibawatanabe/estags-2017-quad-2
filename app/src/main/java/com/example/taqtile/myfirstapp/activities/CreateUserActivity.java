package com.example.taqtile.myfirstapp.activities;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taqtile.myfirstapp.R;
import com.example.taqtile.myfirstapp.models.createUser.CreateUserRemoteRequest;
import com.example.taqtile.myfirstapp.models.createUser.CreateUserRemoteResponse;
import com.example.taqtile.myfirstapp.providers.createUser.CreateUserProvider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateUserActivity extends AppCompatActivity {

    private String token;
    private String name;
    private String email;
    private String type;
    private String password;
    private String confirmPassword;

    private Button createButton;

    private EditText nameEditText;
    private EditText emailEditText;
    private EditText typeEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;

    private CreateUserProvider createUserProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        SharedPreferences prefs = getSharedPreferences(SignInActivity.PREF_TOKEN, MODE_PRIVATE);
        token = prefs.getString("token", "empty_token");

        if (token.equals("empty_token")) {
            Toast.makeText(this, "Erro no token", Toast.LENGTH_LONG).show();
        }

        bindViews();
    }


    private void bindViews() {
        createButton = (Button) findViewById(R.id.activity_create_user_save_button);
        nameEditText = (EditText) findViewById(R.id.activity_create_user_name);
        emailEditText = (EditText) findViewById(R.id.activity_create_user_email);
        typeEditText = (EditText) findViewById(R.id.activity_create_user_type);
        passwordEditText = (EditText) findViewById(R.id.activity_create_user_password);
        confirmPasswordEditText = (EditText) findViewById(R.id.activity_create_user_confirm_password);

        setup();
    }

    private void setup(){
        createUserProvider = new CreateUserProvider();

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCreateButtonClicked();
            }
        });
    }

    private void onCreateButtonClicked(){
        CreateUserRemoteRequest createUserRemoteRequest = new CreateUserRemoteRequest();

        name = nameEditText.getText().toString();
        email = emailEditText.getText().toString();
        type = typeEditText.getText().toString();
        password = passwordEditText.getText().toString();
        confirmPassword = confirmPasswordEditText.getText().toString();

        createUserRemoteRequest.setName(name);
        createUserRemoteRequest.setEmail(email);
        createUserRemoteRequest.setType(type);
        if (password.equals(confirmPassword)){
            createUserRemoteRequest.setPassword(password);
            createUserProvider.performRequest(token, createUserRemoteRequest, new Callback<CreateUserRemoteResponse>() {
                @Override
                public void onResponse(Call<CreateUserRemoteResponse> call, Response<CreateUserRemoteResponse> response) {
                    if (response.isSuccessful()){
                        Toast.makeText(CreateUserActivity.this, "User successfully created!", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(CreateUserActivity.this, "Error " + response.code(), Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<CreateUserRemoteResponse> call, Throwable t) {
                    Toast.makeText(CreateUserActivity.this, "Error de conexão com o servidor.", Toast.LENGTH_LONG).show();
                }
            });
        }
        else{
            passwordsMatchError();
        }
    }

    private void passwordsMatchError(){
        Toast.makeText(this, "Passwords don't match.", Toast.LENGTH_LONG).show();
    }

}
