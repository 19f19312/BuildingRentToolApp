package com.example.ishaq_buildingrenttoolsapp.Worker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ishaq_buildingrenttoolsapp.Owner.AddTools;
import com.example.ishaq_buildingrenttoolsapp.Owner.CheckAvailableTools;
import com.example.ishaq_buildingrenttoolsapp.Owner.CheckPaidRentedTools;
import com.example.ishaq_buildingrenttoolsapp.Owner.CheckRentedTools;
import com.example.ishaq_buildingrenttoolsapp.Owner.CheckReturnedTools;
import com.example.ishaq_buildingrenttoolsapp.Owner.CheckToolRenter;
import com.example.ishaq_buildingrenttoolsapp.Owner.DeleteTools;
import com.example.ishaq_buildingrenttoolsapp.Owner.OwnerDeleteAccount;
import com.example.ishaq_buildingrenttoolsapp.Owner.OwnerHome;
import com.example.ishaq_buildingrenttoolsapp.Owner.OwnerLogin;
import com.example.ishaq_buildingrenttoolsapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class WorkerHome extends AppCompatActivity {
    Button add, rent, returned, view, delete_acc, logout;

    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    public static final String SHARED_PREFS = "sharedPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_home);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        add= findViewById(R.id.w_add);;
        rent= findViewById(R.id.w_rent);
        returned= findViewById(R.id.w_retun);
        delete_acc= findViewById(R.id.w_delete_acc);
        view= findViewById(R.id.w_view);
        logout= findViewById(R.id.w_logout);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WorkerHome.this, AddProfile.class));
                finish();
            }
        });

        rent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WorkerHome.this, WorkerRentTool.class));
                finish();
            }
        });

        returned.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WorkerHome.this, WorkerReturnTool.class));
                finish();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WorkerHome.this, WorkerViewTool.class));
                finish();
            }
        });

        delete_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WorkerHome.this, WorkerDeleteAccount.class));
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
                Toast.makeText(WorkerHome.this, "Account Logout", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(WorkerHome.this, WorkerLogin.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}