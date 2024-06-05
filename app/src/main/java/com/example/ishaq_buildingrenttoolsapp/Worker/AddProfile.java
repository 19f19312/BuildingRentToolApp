package com.example.ishaq_buildingrenttoolsapp.Worker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ishaq_buildingrenttoolsapp.R;
import com.example.ishaq_buildingrenttoolsapp.WorkerProfile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddProfile extends AppCompatActivity {
    NetworkInfo nInfo;
    private FirebaseAuth firebaseAuth;
    private EditText skills, name, phone, gender, address;
    private Button create;
    String  user_skills, user_name, user_phone, user_gender, user_address;
    String status="not-hired";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_profile);

        firebaseAuth= FirebaseAuth.getInstance();
        ConnectivityManager cManager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);

        nInfo = cManager.getActiveNetworkInfo();

        skills= (EditText) findViewById(R.id.skills);
        name= (EditText) findViewById(R.id.name);
        phone= (EditText) findViewById(R.id.phone);
        gender= (EditText) findViewById(R.id.gender);
        address= (EditText) findViewById(R.id.address);
        create= (Button) findViewById(R.id.create);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid()).child("Profile");
        ref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    Toast.makeText(AddProfile.this, "You already have a profile", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddProfile.this, WorkerHome.class));
                    finish();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(AddProfile.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                    if (firebaseUser != null) {
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
                        WorkerProfile userInformation = new WorkerProfile(user_name, user_phone, user_address, user_gender, user_skills, status);
                        ref.child(firebaseUser.getUid()).child("Profile").setValue(userInformation);
                        Toast.makeText(AddProfile.this, "Profile Created", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(AddProfile.this, WorkerHome.class));finish();
                        finish();
                    }
                }
            }
        });

    }

    private Boolean validate(){
        boolean result= false;

        user_skills = skills.getText().toString();
        user_name = name.getText().toString();
        user_phone = phone.getText().toString();
        user_gender =gender.getText().toString();
        user_address= address.getText().toString();
        if(user_name.isEmpty() || user_skills.isEmpty() || user_gender.isEmpty() ||
                user_phone.isEmpty() || user_address.isEmpty()){
            Toast.makeText(this, "Fill every required information", Toast.LENGTH_SHORT).show();
        }else {
            result= true;
        }
        return result;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(AddProfile.this, WorkerHome.class));
        //Display alert message when back button has been pressed
        finish();
    }
}