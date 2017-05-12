package com.example.taqtile.myfirstapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.taqtile.myfirstapp.R;
import com.example.taqtile.myfirstapp.models.signIn.BadSignInRemoteResponse;
import com.example.taqtile.myfirstapp.models.signIn.ErrorParser;
import com.example.taqtile.myfirstapp.models.signIn.SignInRemoteRequest;
import com.example.taqtile.myfirstapp.models.signIn.SignInRemoteResponse;
import com.example.taqtile.myfirstapp.providers.signIn.SignInProvider;

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
    public static String PREF_TOKEN = "token_pref";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
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

    private void setup() {
        signInProvider = new SignInProvider();
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoginSelected();
            }
        });
    }

    private void onLoginSelected() {
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
                    BadSignInRemoteResponse error = ErrorParser.parseError(response);
                    onLoginError(error);
                }

            }

            @Override
            public void onFailure(Call<SignInRemoteResponse> call, Throwable t) {
                onLoginFailure();
            }
        });


    }

    private void onLoginSuccess(SignInRemoteResponse signInRemoteResponse) {
        String name = "";

        if (signInRemoteResponse != null && signInRemoteResponse.getData() != null && signInRemoteResponse.getData().getUser() != null) {
            name = signInRemoteResponse.getData().getUser().getName();

            Toast.makeText(this, "Bem-vindo, " + name, Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, ListUsersActivity.class);
            //intent.putExtra("token", signInRemoteResponse.getData().getToken());
            SharedPreferences.Editor editor = getSharedPreferences(PREF_TOKEN, MODE_PRIVATE).edit();
            editor.putString("token", signInRemoteResponse.getData().getToken());
            editor.commit();
            startActivity(intent);
        } else {
            Toast.makeText(this, "E-mail e/ou senha incorreto(s)" + name, Toast.LENGTH_LONG).show();
        }


    }

    private void onLoginFailure() {
        Toast.makeText(this, "Login falhou. Tente novamente.", Toast.LENGTH_LONG).show();
    }

    private void onLoginError(BadSignInRemoteResponse error) {
        String message = error.getSignInErrorRemoteResponse().get(0).getMessage();
        String defaultMessage = error.getSignInErrorRemoteResponse().get(0).getDefaultMessage();


        Toast.makeText(this, "Login falhou. Tente novamente.\n" + message + "\n" + defaultMessage, Toast.LENGTH_LONG).show();
    }
    //Toast.makeText(this, email, Toast.LENGTH_LONG).show();

}
