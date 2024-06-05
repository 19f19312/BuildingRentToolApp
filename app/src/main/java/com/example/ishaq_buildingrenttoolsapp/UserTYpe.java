package com.example.ishaq_buildingrenttoolsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ishaq_buildingrenttoolsapp.Owner.OwnerHome;
import com.example.ishaq_buildingrenttoolsapp.Owner.OwnerRegistration;
import com.example.ishaq_buildingrenttoolsapp.User.UserHome;
import com.example.ishaq_buildingrenttoolsapp.User.UserRegistration;
import com.example.ishaq_buildingrenttoolsapp.Worker.WorkerHome;
import com.example.ishaq_buildingrenttoolsapp.Worker.WorkerRegistration;

public class UserTYpe extends AppCompatActivity {
    Button user, owner, worker;

    public static final String SHARED_PREFS= "sharedPrefs";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_type);

        user= findViewById(R.id.user);
        owner= findViewById(R.id.owner);
        worker= findViewById(R.id.worker);

        checkBox();

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserTYpe.this, UserRegistration.class));
                finish();
            }
        });

        owner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserTYpe.this, OwnerRegistration.class));
                finish();
            }
        });

        worker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserTYpe.this, WorkerRegistration.class));
                finish();
            }
        });
    }

    private void checkBox() {
        SharedPreferences sharedPreferences= getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String check= sharedPreferences.getString("name", "");

        if (check.equals("user")){
            startActivity(new Intent(UserTYpe.this, UserHome.class));
            finish();
        }

        if (check.equals("owner")){
            startActivity(new Intent(UserTYpe.this, OwnerHome.class));
            finish();
        }

        if (check.equals("worker")){
            startActivity(new Intent(UserTYpe.this, WorkerHome.class));
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        finish();

    }
}