package com.example.myfirstapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfirstapp.R;

public class UserDetailsActivity extends AppCompatActivity {

    private int id;
    private String name, email, createdAt, updatedAt, token;

    private TextView textViewName, textViewEmail, textViewcreatedAt, textViewupdatedAt, textViewId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        id = getIntent().getIntExtra(ListUsersActivity.EXTRA_ID, -1);
        name = getIntent().getStringExtra(ListUsersActivity.EXTRA_NAME);
        email = getIntent().getStringExtra(ListUsersActivity.EXTRA_EMAIL);
        createdAt = getIntent().getStringExtra(ListUsersActivity.EXTRA_CREATED);
        updatedAt = getIntent().getStringExtra(ListUsersActivity.EXTRA_UPDATED);
        token = getIntent().getStringExtra(ListUsersActivity.EXTRA_TOKEN);

        if(id < 0){
            Toast.makeText(this, "Erro na passagem do usuario (-1)", Toast.LENGTH_LONG).show();
        }

        bindViews();
        setTextViews();
    }

    private void bindViews() {

        textViewName = (TextView) findViewById(R.id.activity_user_details_name);
        textViewEmail = (TextView) findViewById(R.id.activity_user_details_email);
        textViewId = (TextView) findViewById(R.id.activity_user_details_id);
        textViewcreatedAt = (TextView) findViewById(R.id.activity_user_details_created);
        textViewupdatedAt = (TextView) findViewById(R.id.activity_user_details_updated);

    }

    private void setTextViews() {

        textViewName.setText(name);
        textViewEmail.setText(email);
        textViewId.setText(Integer.toString(id));
        textViewcreatedAt.setText(createdAt);
        textViewupdatedAt.setText(updatedAt);
    }

}
