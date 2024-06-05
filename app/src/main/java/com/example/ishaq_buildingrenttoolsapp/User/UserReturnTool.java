package com.example.ishaq_buildingrenttoolsapp.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ishaq_buildingrenttoolsapp.R;
import com.example.ishaq_buildingrenttoolsapp.UserReturnToolFile;
import com.example.ishaq_buildingrenttoolsapp.UserToolRent;
import com.example.ishaq_buildingrenttoolsapp.Worker.ImageAdapterWorkerReturnTool;
import com.example.ishaq_buildingrenttoolsapp.Worker.WorkerHome;
import com.example.ishaq_buildingrenttoolsapp.Worker.WorkerReturnTool;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class UserReturnTool extends AppCompatActivity implements ImageAdapterUserReturnTool.OnItemClickListener{
    private RecyclerView mRecyclerView;
    private ImageAdapterUserReturnTool mAdapter;
    private DatabaseReference mDatabaseRef, ref, ref2, ref1, ref3;
    private FirebaseStorage mStrorage;
    private FirebaseAuth firebaseAuth;

    private ValueEventListener mDBListener;
    private FirebaseUser user;
    private List<UserToolRent> mUploads, mUploads1;
    private ProgressBar mProgressCircle;
    private FirebaseDatabase firebaseDatabase;
    UserToolRent selectedItem;
    String selectedKey;
    String same_url, search_url;
    String get_key;
    String toolName, toolPrice, toolUrl, renteename, renteePhone, renteeAddress, rentDays, rentDate, returnDate;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_return_tool);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        mStrorage = FirebaseStorage.getInstance();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view9);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mProgressCircle = (ProgressBar) findViewById(R.id.progress_circle9);

        mUploads = new ArrayList<>();
        mUploads1 = new ArrayList<>();
        mAdapter = new ImageAdapterUserReturnTool(UserReturnTool.this, mUploads);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(UserReturnTool.this);

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Rented").child(user.getUid()).child("all");
        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mUploads.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    UserToolRent userToolRent = postSnapshot.getValue(UserToolRent.class);
                    userToolRent.setKey(postSnapshot.getKey());
                    mUploads.add(userToolRent);

                }
                mAdapter.notifyDataSetChanged();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UserReturnTool.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        selectedItem = mUploads.get(position);
        selectedKey = selectedItem.getKey();

        //to check through url
        same_url=  selectedItem.gettUrl();

        //to copy and save to add to return node
        toolName=selectedItem.gettName();
        toolPrice=selectedItem.gettPrice();
        toolUrl=selectedItem.gettUrl();
        renteename=selectedItem.getrName();
        renteePhone=selectedItem.getrPhone();
        renteeAddress=selectedItem.getrAddress();
        rentDays=selectedItem.getrDays();
        rentDate=selectedItem.getRentDate();
        returnDate=selectedItem.getReturnDate();


        ref= FirebaseDatabase.getInstance().getReference("Rented").child(user.getUid()).child("paid");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                        if (postSnapshot.child("tUrl").exists()) {
                            search_url = postSnapshot.child("tUrl").getValue().toString();
                            if (search_url.equals(same_url)) {
                                get_key = postSnapshot.getKey();
                                backButtonHandler();
                            }
                        }
                    }
                } if (!snapshot.exists()) {
                    backButtonHandler1();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UserReturnTool.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDatabaseRef.removeEventListener(mDBListener);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(UserReturnTool.this, WorkerHome.class));
        finish();
    }

    public void backButtonHandler() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                UserReturnTool.this);
        alertDialog.setTitle("Return Tool");
        alertDialog.setMessage("Are you sure you want to Return Tool?");
        alertDialog.setIcon(R.drawable.logo);
        alertDialog.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //to save into return
                        ref3=FirebaseDatabase.getInstance().getReference("Returned").child(user.getUid()).child("all");
                        UserReturnToolFile userReturnToolFile = new UserReturnToolFile(toolName, toolPrice, toolUrl, renteename, renteePhone, renteeAddress, rentDays, rentDate, returnDate);
                        ref3.child(ref3.push().getKey()).setValue(userReturnToolFile);

                        //to delete from all and paid node
                        ref1 = FirebaseDatabase.getInstance().getReference("Rented").child(user.getUid()).child("all").child(selectedKey);
                        ref2 = FirebaseDatabase.getInstance().getReference("Rented").child(user.getUid()).child("paid").child(get_key);
                        ref1.getRef().removeValue();
                        ref2.getRef().removeValue();
                        Toast.makeText(UserReturnTool.this, "Tool is Returned", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(UserReturnTool.this, UserHome.class));
                        finish();
                    }
                });
        alertDialog.setNegativeButton("NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialog.show();
    }

    public void backButtonHandler1() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                UserReturnTool.this);
        alertDialog.setTitle("Return Tool");
        alertDialog.setMessage("Are you sure you want to Return Tool?");
        alertDialog.setIcon(R.drawable.logo);
        alertDialog.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //to save into return
                        ref3=FirebaseDatabase.getInstance().getReference("Returned").child(user.getUid()).child("all");
                        UserReturnToolFile userReturnToolFile = new UserReturnToolFile(toolName, toolPrice, toolUrl, renteename, renteePhone, renteeAddress, rentDays, rentDate, returnDate);
                        ref3.child(ref3.push().getKey()).setValue(userReturnToolFile);

                        //to delete from all and paid node
                        ref1 = FirebaseDatabase.getInstance().getReference("Rented").child(user.getUid()).child("all").child(selectedKey);
                        ref1.getRef().removeValue();
                        Toast.makeText(UserReturnTool.this, "Tool is Returned", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(UserReturnTool.this, UserHome.class));
                        finish();
                    }
                });
        alertDialog.setNegativeButton("NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialog.show();
    }
}