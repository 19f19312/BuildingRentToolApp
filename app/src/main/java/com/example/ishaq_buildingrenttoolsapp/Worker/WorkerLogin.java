package com.example.ishaq_buildingrenttoolsapp.Worker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ishaq_buildingrenttoolsapp.Owner.OwnerLogin;
import com.example.ishaq_buildingrenttoolsapp.R;
import com.example.ishaq_buildingrenttoolsapp.User.UserHome;
import com.example.ishaq_buildingrenttoolsapp.User.UserLogin;
import com.example.ishaq_buildingrenttoolsapp.User.UserRegistration;
import com.example.ishaq_buildingrenttoolsapp.UserTYpe;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class WorkerLogin extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private EditText p_email, p_password;
    private TextView sign;
    private Button login;
    private FirebaseUser user;
    private ProgressDialog progressDialog;
    NetworkInfo nInfo;
    public static final String SHARED_PREFS= "sharedPrefs";
    String password;
    String email;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_login);

        p_email= (EditText) findViewById(R.id.p_email2);
        p_password= (EditText) findViewById(R.id.p_password2);
        sign= (TextView) findViewById(R.id.sign2);
        login= (Button) findViewById(R.id.login2);

        firebaseAuth= FirebaseAuth.getInstance();
        progressDialog= new ProgressDialog(this);
        user= firebaseAuth.getCurrentUser();

        ConnectivityManager cManager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        nInfo = cManager.getActiveNetworkInfo();

        //to keep user logged in this method calll

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valid()) {
                    if(nInfo!=null && nInfo.isConnected()) {
                        progressDialog.setMessage("Wait for the authentication approval");
                        progressDialog.show();

                        firebaseAuth.signInWithEmailAndPassword(password, email).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    progressDialog.dismiss();
                                    //to keep user logged in
                                    SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                                    SharedPreferences.Editor editor= sharedPreferences.edit();
                                    editor.putString("name", "worker");
                                    editor.apply();
                                    //end
                                    Toast.makeText(WorkerLogin.this, "Login Successful", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(WorkerLogin.this, WorkerHome.class));
                                    finish();
                                } else {
                                    Toast.makeText(WorkerLogin.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                }
                            }
                        });
                    } else {
                        Toast.makeText(WorkerLogin.this, "Network is not available", Toast.LENGTH_LONG).show();}

                }
            }
        });

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WorkerLogin.this, WorkerRegistration.class));
                finish();
            }
        });

    }

    private Boolean valid(){
        boolean result= false;
        password= p_email.getText().toString();
        email= p_password.getText().toString();

        if(password.isEmpty() || email.isEmpty()){
            Toast.makeText(this, "Fill both options", Toast.LENGTH_SHORT).show();
        }else {
            result= true;
        }
        return result;
    }

    @Override
    public void onBackPressed() {

        startActivity(new Intent(WorkerLogin.this, UserTYpe.class));
        finish();
    }
}