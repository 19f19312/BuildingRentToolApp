package com.example.ishaq_buildingrenttoolsapp.User;

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
import com.example.ishaq_buildingrenttoolsapp.UserTYpe;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class UserRegistration extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    NetworkInfo nInfo;
    private EditText email, password, cPassword;
    private Button signup;
    String user_email, user_password, user_cPassword;
    private TextView log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        firebaseAuth= FirebaseAuth.getInstance();
        ConnectivityManager cManager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);

        nInfo = cManager.getActiveNetworkInfo();

        email= (EditText) findViewById(R.id.email);
        password= (EditText) findViewById(R.id.password);
        cPassword= (EditText) findViewById(R.id.c_password) ;
        signup= (Button) findViewById(R.id.signup);
        log= (TextView) findViewById(R.id.log);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateCheck()) {
                    if (paswordlength()) {
                        if(paswordmatch()){
                            if (nInfo != null && nInfo.isConnected()) {
                                firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(UserRegistration.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(UserRegistration.this, UserLogin.class));
                                            finish();

                                        } else {
                                            Toast.makeText(UserRegistration.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(UserRegistration.this, "Network is not available", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }
            }
        });

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserRegistration.this, UserLogin.class));
                finish();
            }
        });
    }

    private Boolean validateCheck(){
        boolean result= false;

        user_email = email.getText().toString();
        user_password = password.getText().toString();
        user_cPassword= cPassword.getText().toString();

        if(user_password.isEmpty() || user_email.isEmpty()|| user_cPassword.isEmpty()){
            Toast.makeText(this, "Fill all required option", Toast.LENGTH_SHORT).show();
        }else {
            result= true;
        }
        return result;
    }

    private Boolean paswordlength(){
        boolean result1= false;
        if (user_password.length() < 6 ){
            Toast.makeText(this, "Password should be of minimum length 6", Toast.LENGTH_SHORT).show();
        }else{
            result1= true;
        }
        return result1;
    }

    private Boolean paswordmatch(){
        boolean result2= false;
        if (!user_password.equals(user_cPassword)){
            Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show();
        }else{
            result2= true;
        }
        return result2;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(UserRegistration.this, UserTYpe.class));
        //Display alert message when back button has been pressed
        finish();
    }
}