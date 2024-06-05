package com.example.ishaq_buildingrenttoolsapp.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ishaq_buildingrenttoolsapp.R;
import com.example.ishaq_buildingrenttoolsapp.Worker.AddProfile;
import com.example.ishaq_buildingrenttoolsapp.Worker.WorkerDeleteAccount;
import com.example.ishaq_buildingrenttoolsapp.Worker.WorkerHome;
import com.example.ishaq_buildingrenttoolsapp.Worker.WorkerLogin;
import com.example.ishaq_buildingrenttoolsapp.Worker.WorkerRentTool;
import com.example.ishaq_buildingrenttoolsapp.Worker.WorkerReturnTool;
import com.example.ishaq_buildingrenttoolsapp.Worker.WorkerViewTool;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserHome extends AppCompatActivity {
    Button hire, rent, returned, view, delete_acc, logout;

    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    public static final String SHARED_PREFS = "sharedPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        hire= findViewById(R.id.hire);;
        rent= findViewById(R.id.u_rent);
        returned= findViewById(R.id.u_retun);
        delete_acc= findViewById(R.id.u_delete_acc);
        view= findViewById(R.id.u_view);
        logout= findViewById(R.id.u_logout);

        hire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserHome.this, HireWorker.class));
                finish();
            }
        });

        rent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserHome.this, UserRentTool.class));
                finish();
            }
        });

        returned.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserHome.this, UserReturnTool.class));
                finish();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserHome.this, UserViewTool.class));
                finish();
            }
        });

        delete_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserHome.this, UserDeleteAccount.class));
                finish();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                firebaseAuth.signOut();
                finish();
                Toast.makeText(UserHome.this, "Account Logout", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(UserHome.this, WorkerLogin.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}