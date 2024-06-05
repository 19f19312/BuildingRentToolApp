package com.example.ishaq_buildingrenttoolsapp.Owner;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ishaq_buildingrenttoolsapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class OwnerHome extends AppCompatActivity {
    Button add, remove, who_rent, available, returned, rented, paid, delete_acc, logout;

    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    public static final String SHARED_PREFS = "sharedPrefs";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_home);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        add= findViewById(R.id.add);
        remove= findViewById(R.id.remove);
        who_rent= findViewById(R.id.who_rent);
        available= findViewById(R.id.available);
        returned= findViewById(R.id.returned);
        rented= findViewById(R.id.rented);
        paid= findViewById(R.id.paid);
        delete_acc= findViewById(R.id.o_delete_acc);
        logout= findViewById(R.id.o_logout);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OwnerHome.this, AddTools.class));
                finish();
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OwnerHome.this, DeleteTools.class));
                finish();
            }
        });

        who_rent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OwnerHome.this, CheckToolRenter.class));
                finish();
            }
        });

        available.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OwnerHome.this, CheckAvailableTools.class));
                finish();
            }
        });

        returned.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OwnerHome.this, CheckReturnedTools.class));
                finish();
            }
        });

        rented.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OwnerHome.this, CheckRentedTools.class));
                finish();
            }
        });

        paid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OwnerHome.this, CheckPaidRentedTools.class));
                finish();
            }
        });

        delete_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OwnerHome.this, OwnerDeleteAccount.class));
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
                Toast.makeText(OwnerHome.this, "Account Logout", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(OwnerHome.this, OwnerLogin.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}