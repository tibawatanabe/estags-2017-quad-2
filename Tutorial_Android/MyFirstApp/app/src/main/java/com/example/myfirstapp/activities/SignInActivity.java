package com.example.myfirstapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myfirstapp.R;
import com.example.myfirstapp.models.signIn.error.SignInRemoteError;
import com.example.myfirstapp.models.signIn.request.SignInRemoteRequest;
import com.example.myfirstapp.models.signIn.response.SignInRemoteResponse;
import com.example.myfirstapp.providers.signIn.SignInProvider;
import com.example.myfirstapp.providers.signIn.error.ErrorParser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by taqtile on 5/4/17.
 */

public class SignInActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private SignInProvider signInProvider;

    public static String USER_EXTRA = "com.example.myfirstapp.USER";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        bindViews();
        setup();
    }

    private void bindViews() {
        emailEditText = (EditText) findViewById(R.id.activity_sign_in_email);
        passwordEditText = (EditText) findViewById(R.id.activity_sign_in_password);
        loginButton = (Button) findViewById(R.id.activity_sign_in_button);
    }

    private void setup(){
        signInProvider = new SignInProvider();
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoginSelected();
            }
        });
    }

    private void onLoginSelected(){
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        SignInRemoteRequest params = new SignInRemoteRequest();

        params.setUser(email);
        params.setPassword(password);

        signInProvider.performRequest(params, new Callback<SignInRemoteResponse>() {
            @Override
            public void onResponse(Call<SignInRemoteResponse> call, Response<SignInRemoteResponse> response) {
                if (response.isSuccessful()) {
                    onLoginSuccess(response.body());
                } else {
                    SignInRemoteError error = ErrorParser.parse(response);
                    onLoginError(error);
                }

            }

            @Override
            public void onFailure(Call<SignInRemoteResponse> call, Throwable t) {
                onLoginFailure();
            }
        });

    }

    private void onLoginSuccess(SignInRemoteResponse signInRemoteResponse){
        String name = "";
        if(signInRemoteResponse.getData() != null
                && signInRemoteResponse.getData().getUser() != null){
            name = signInRemoteResponse.getData().getUser().getName();
        }
        Toast.makeText(this, "Bem-vindo(a), "+ name +"!",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, ListUsersActivity.class);
        intent.putExtra(USER_EXTRA, signInRemoteResponse.getData().getToken());
        startActivity(intent);
    }

    private void onLoginFailure(){
        Toast.makeText(this, "Login falhou!", Toast.LENGTH_LONG).show();
    }

    private void onLoginError(SignInRemoteError error){

        String message = error.getErrors().get(0).getMessage();
        String defaultMessage = error.getErrors().get(0).getDefaultMessage();

        Toast.makeText(this, "Erro: "+message+"!\n"+defaultMessage, Toast.LENGTH_LONG).show();

    }
}
