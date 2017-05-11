package com.example.taqtile.myfirstapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.taqtile.myfirstapp.R;


public class UserDetailsActivity extends AppCompatActivity {
    private String token;
    private int userId;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        token = getIntent().getStringExtra("token");
        userId = getIntent().getIntExtra("userId", -1);
        userName = getIntent().getStringExtra("userName");
        userEmail = getIntent().getStringExtra("userEmail");
        userType = getIntent().getStringExtra("userType");
        userCreatedAt = getIntent().getStringExtra("userCreatedAt");
        userUpdatedAt = getIntent().getStringExtra("userUpdatedAt");
        bindViews();
        setup();
    }

    private void bindViews() {
        idTextView = (TextView) findViewById(R.id.activity_user_details_id);
        nameTextView = (TextView) findViewById(R.id.activity_user_details_name);
        emailTextView = (TextView) findViewById(R.id.activity_user_details_email);
        typeTextView = (TextView) findViewById(R.id.activity_user_details_type);
        createdAtTextView = (TextView) findViewById(R.id.activity_user_details_created_at);
        updatedAtTextView = (TextView) findViewById(R.id.activity_user_details_updated_at);

        idTextView.setText(String.valueOf(userId));
        nameTextView.setText(userName);
        emailTextView.setText(userEmail);
        typeTextView.setText(userType);
        createdAtTextView.setText(userCreatedAt);
        updatedAtTextView.setText(userUpdatedAt);
    }

    private void setup() {

    }
}
