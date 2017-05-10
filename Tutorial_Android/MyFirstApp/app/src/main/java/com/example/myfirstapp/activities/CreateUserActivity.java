package com.example.myfirstapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myfirstapp.R;
import com.example.myfirstapp.models.createUser.request.CreateUserRemoteRequest;
import com.example.myfirstapp.models.createUser.response.CreateUserRemoteResponse;
import com.example.myfirstapp.providers.createUser.CreateUserProvider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateUserActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String type;
    String token;

    CreateUserProvider createUserProvider;

    EditText nameEditText, emailEditText, passwordEditText;
    Button createUserButton;
    Spinner createUserSpinner;
    ArrayAdapter<CharSequence> createUserAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        token = getSharedPreferences(SignInActivity.PREF_TOKEN, MODE_PRIVATE).getString("token", "Empty token");
        bindViews();
        setup();
    }

    private void bindViews() {
        createUserButton = (Button) findViewById(R.id.activity_create_user_button);
        createUserSpinner = (Spinner) findViewById(R.id.activity_create_user_spinner);
        createUserAdapter = ArrayAdapter.createFromResource(this, R.array.spinner_choices, R.layout.spinner_create_user);
        nameEditText = (EditText) findViewById(R.id.activity_create_user_name);
        emailEditText = (EditText) findViewById(R.id.activity_create_user_email);
        passwordEditText = (EditText) findViewById(R.id.activity_create_user_password);
    }

    private void setup() {
        createUserProvider = new CreateUserProvider();
        createUserAdapter.setDropDownViewResource(R.layout.spinner_dropdonw_create_user);
        createUserSpinner.setAdapter(createUserAdapter);
        createUserSpinner.setOnItemSelectedListener(this);

        createUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(CreateUserActivity.this, type, Toast.LENGTH_LONG).show();
                onCreateSelected();
            }
        });
    }

    private void onCreateSelected() {
        String name = nameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        CreateUserRemoteRequest createUserRemoteRequest = new CreateUserRemoteRequest();
        createUserRemoteRequest.setName(name);
        createUserRemoteRequest.setEmail(email);
        createUserRemoteRequest.setPassword(password);
        createUserRemoteRequest.setType(type);

        createUserProvider.performRequest(token, createUserRemoteRequest, new Callback<CreateUserRemoteResponse>() {
            @Override
            public void onResponse(Call<CreateUserRemoteResponse> call, Response<CreateUserRemoteResponse> response) {
                if(response.isSuccessful()){
                    createUserSuccess(response);
                } else {
                    createUserError(response);
                }
            }

            @Override
            public void onFailure(Call<CreateUserRemoteResponse> call, Throwable t) {
                createUserFailure();
            }
        });

    }

    private void createUserSuccess(Response<CreateUserRemoteResponse> response) {
        Toast.makeText(this, "Criação do usuario " + response.body().getData().getName() + " com sucesso!", Toast.LENGTH_LONG).show();
    }

    private void createUserError(Response<CreateUserRemoteResponse> response){
        Toast.makeText(this, "Erro " + response.code(), Toast.LENGTH_LONG).show();
    }


    private void createUserFailure() {
        Toast.makeText(this, "Erro de conexao - create user", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        type = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(parent.getContext(), "Escolha um tipo de usuário", Toast.LENGTH_LONG).show();

    }
}
