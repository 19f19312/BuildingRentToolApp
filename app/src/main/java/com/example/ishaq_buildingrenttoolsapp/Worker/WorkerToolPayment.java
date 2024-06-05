package com.example.ishaq_buildingrenttoolsapp.Worker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ishaq_buildingrenttoolsapp.R;
import com.example.ishaq_buildingrenttoolsapp.ToolsFile;
import com.example.ishaq_buildingrenttoolsapp.UserToolRent;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class WorkerToolPayment extends AppCompatActivity {

    TextView bo_name, bo_price, bo_detail;
    EditText credit, us_address, us_name, us_phone, us_days;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    Button confirm, btn_cal;
    String user_credit, text, u_address, u_name, u_phone, u_days;
    String getKey, getUid;
    String img_price, img_name, img_url;
    ImageView imgGet;
    TextView here, cal_price;

    private FirebaseStorage mStrorage;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    DatabaseReference ref, ref1, ref2;
    String rent_date, return_date;
    double d, d1, d2;
    String final_price;
    LocalDate currentDate;
    DateTimeFormatter formatter;
    int daysToAdd;

    LocalDate newDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_tool_payment);

        radioGroup= (RadioGroup) findViewById(R.id.radioGroup);
        confirm= (Button) findViewById(R.id.confirm);
        btn_cal= (Button) findViewById(R.id.btn_cal);
        bo_name= (TextView) findViewById(R.id.bo_name);
        bo_price= (TextView) findViewById(R.id.bo_price);
        credit= (EditText) findViewById(R.id.credit);
        us_address= (EditText) findViewById(R.id.renter_Address);
        us_name= (EditText) findViewById(R.id.renter_name);
        us_days= (EditText) findViewById(R.id.renter_days);
        us_phone= (EditText) findViewById(R.id.renter_phone);
        here= (TextView) findViewById(R.id.here);
        btn_cal= (Button) findViewById(R.id.btn_cal);
        cal_price= (TextView) findViewById(R.id.cal_price);

        imgGet= (ImageView) findViewById(R.id.imgGet);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        if (getIntent().hasExtra("key") || getIntent().hasExtra("uid")) {
            getKey = getIntent().getStringExtra("key");
            getUid= getIntent().getStringExtra("uid");
        }

        credit.setVisibility(View.INVISIBLE);
        here.setVisibility(View.INVISIBLE);
        radioGroup.setVisibility(View.INVISIBLE);
        confirm.setVisibility(View.INVISIBLE);

        mStrorage = FirebaseStorage.getInstance();
        ref = FirebaseDatabase.getInstance().getReference("Tools").child(getUid).child("all").child(getKey);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    ToolsFile toolFile = snapshot.getValue(ToolsFile.class);
                    bo_name.setText(toolFile.getToolName());
                    bo_price.setText(toolFile.getToolPrice());
                    Picasso.get()
                            .load(toolFile.getToolUrl())
                            .placeholder(R.mipmap.ic_launcher)
                            .fit()
                            .centerCrop()
                            .into(imgGet);

                    img_name= toolFile.getToolName();
                    img_price= toolFile.getToolPrice();
                    img_url= toolFile.getToolUrl();

                }
                if (!snapshot.exists()){
                    Toast.makeText(WorkerToolPayment.this, "", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(WorkerToolPayment.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();

        btn_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate1()){
                    d = Double.valueOf(u_days);
                    d1 = Double.valueOf(img_price);
                    d2= d*d1;
                    final_price = Double.toString(d2);

                    cal_price.setText("Price for renting tool for mentioned days is: "+ final_price +" OMR");

                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        currentDate = LocalDate.now();
                        // Format the date as a string
                        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                        rent_date = currentDate.format(formatter);

                        daysToAdd=Integer.parseInt(u_days);
                        newDate = currentDate.plusDays(daysToAdd);

                        return_date = newDate.format(formatter);

                        here.setVisibility(View.VISIBLE);
                        radioGroup.setVisibility(View.VISIBLE);
                        confirm.setVisibility(View.VISIBLE);

                    }

                }
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                text= radioButton.getText().toString();
                if (text.equals("Cash on delivery")) {
                    if (validate1()) {
                        ref1=FirebaseDatabase.getInstance().getReference("Rented").child(user.getUid()).child("all");
                        UserToolRent userToolRent = new UserToolRent(img_name, final_price, img_url, u_name, u_phone, u_address, u_days, rent_date, return_date);
                        ref1.child(ref1.push().getKey()).setValue(userToolRent);
                        Toast.makeText(WorkerToolPayment.this, "Tool Rented Successfully", Toast.LENGTH_LONG).show();
                        Toast.makeText(WorkerToolPayment.this, "Tool will be delivered to you today", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(WorkerToolPayment.this, WorkerHome.class));
                        finish();
                    }
                } else {
                    credit.setVisibility(View.VISIBLE);
                    if (validate1()){
                        if (validate()) {
                            ref1=FirebaseDatabase.getInstance().getReference("Rented").child(user.getUid()).child("all");
                            ref2=FirebaseDatabase.getInstance().getReference("Rented").child(user.getUid()).child("paid");
                            UserToolRent userToolRent = new UserToolRent(img_name, final_price, img_url, u_name, u_phone, u_address, u_days, rent_date, return_date);
                            ref1.child(ref1.push().getKey()).setValue(userToolRent);
                            ref2.child(ref2.push().getKey()).setValue(userToolRent);
                            Toast.makeText(WorkerToolPayment.this, "Tool Rented Successfully", Toast.LENGTH_LONG).show();
                            Toast.makeText(WorkerToolPayment.this, "Tool will be delivered to you today", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(WorkerToolPayment.this, WorkerHome.class));
                            finish();
                        }//validate end
                    }
                }
            }
        });
    }

    private Boolean validate(){
        boolean result= false;

        user_credit = credit.getText().toString();

        if(user_credit.isEmpty()){
            Toast.makeText(this, "Enter credit card number", Toast.LENGTH_SHORT).show();
        }else {
            result= true;
        }
        return result;
    }

    private Boolean validate1(){
        boolean result= false;

        u_address= us_address.getText().toString();
        u_name = us_name.getText().toString();
        u_phone= us_phone.getText().toString();
        u_days= us_days.getText().toString();

        if(u_address.isEmpty() || u_name.isEmpty() || u_phone.isEmpty() || u_days.isEmpty()){
            Toast.makeText(this, "Enter all required details", Toast.LENGTH_SHORT).show();
        }else {
            result= true;
        }
        return result;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(WorkerToolPayment.this, WorkerRentTool.class));
        finish();
    }
}