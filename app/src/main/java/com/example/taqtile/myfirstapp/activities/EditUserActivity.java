package com.example.taqtile.myfirstapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.taqtile.myfirstapp.R;
import com.example.taqtile.myfirstapp.models.editUser.EditUserRemoteRequest;
import com.example.taqtile.myfirstapp.models.editUser.EditUserRemoteResponse;
import com.example.taqtile.myfirstapp.providers.editUser.EditUserProvider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditUserActivity extends AppCompatActivity {

    private String token;

    private EditText nameEditText;
    private EditText emailEditText;
    private Button saveButton;

    public static int id;
    private String newName;
    private String newEmail;
    private String oldName;
    private String oldEmail;

    private EditUserProvider editUserProvider;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        id = getIntent().getIntExtra("userId", -1);
        oldName = getIntent().getStringExtra("userName");
        oldEmail = getIntent().getStringExtra("userEmail");
        getToken();
        bindViews();
    }

    private void getToken() {
        token = getSharedPreferences(SignInActivity.PREF_TOKEN, MODE_PRIVATE).getString("token", "empty_token");

        if (token.equals("empty_token")) {
            Toast.makeText(this, "Erro no token", Toast.LENGTH_LONG).show();
        }
    }

    private void bindViews() {
        nameEditText = (EditText) findViewById(R.id.activity_edit_user_name);
        emailEditText = (EditText) findViewById(R.id.activity_edit_user_email);
        saveButton = (Button) findViewById(R.id.activity_edit_user_save_button);

        nameEditText.setText(oldName);
        emailEditText.setText(oldEmail);

        setup();
    }

    private void setup() {
        editUserProvider = new EditUserProvider();
        intent = new Intent(EditUserActivity.this, UserDetailsActivity.class);
        intent.putExtra("userId", id);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editRequest();
            }
        });
    }

    private void editRequest() {
        newName = nameEditText.getText().toString();
        newEmail = emailEditText.getText().toString();

        EditUserRemoteRequest editUserRemoteRequest = new EditUserRemoteRequest();

        editUserRemoteRequest.setName(newName);
        editUserRemoteRequest.setEmail(newEmail);

        editUserProvider.performRequest(token, id, editUserRemoteRequest, new Callback<EditUserRemoteResponse>() {
            @Override
            public void onResponse(Call<EditUserRemoteResponse> call, Response<EditUserRemoteResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(EditUserActivity.this, "User successfully updated!", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                } else {
                    Toast.makeText(EditUserActivity.this, "Error: " + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<EditUserRemoteResponse> call, Throwable t) {
                Toast.makeText(EditUserActivity.this, "Erro de conexão com o servidor", Toast.LENGTH_LONG).show();
            }
        });
    }
}
