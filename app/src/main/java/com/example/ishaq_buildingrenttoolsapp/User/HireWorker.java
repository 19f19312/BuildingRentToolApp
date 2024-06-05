package com.example.ishaq_buildingrenttoolsapp.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ishaq_buildingrenttoolsapp.R;
import com.example.ishaq_buildingrenttoolsapp.ToolsFile;
import com.example.ishaq_buildingrenttoolsapp.UserToolRent;
import com.example.ishaq_buildingrenttoolsapp.Worker.AddProfile;
import com.example.ishaq_buildingrenttoolsapp.Worker.WorkerHome;
import com.example.ishaq_buildingrenttoolsapp.WorkerProfile;
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

public class HireWorker extends AppCompatActivity implements ImageAdapterHireWorker.OnItemClickListener{
    private RecyclerView mRecyclerView;
    private ImageAdapterHireWorker mAdapter;
    private DatabaseReference mDatabaseRef, ref, ref2, ref1, ref3;
    private FirebaseStorage mStrorage;
    private FirebaseAuth firebaseAuth;

    private ValueEventListener mDBListener;
    private FirebaseUser user;
    private List<WorkerProfile> mUploads, mUploads1;
    private ProgressBar mProgressCircle;
    private FirebaseDatabase firebaseDatabase;
    WorkerProfile selectedItem, selectedUid;
    String selectedKey;
    String check_val;
    String get_key;
    String get_Uid;
    String status="not-hired";
    String w_Name, w_phone, w_address, w_gender, w_skills;
    String new_status="hired";


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hire_worker);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        mStrorage = FirebaseStorage.getInstance();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view12);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mProgressCircle = (ProgressBar) findViewById(R.id.progress_circle12);

        mUploads = new ArrayList<>();
        mUploads1 = new ArrayList<>();
        mAdapter = new ImageAdapterHireWorker(HireWorker.this, mUploads);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(HireWorker.this);

        mDatabaseRef=FirebaseDatabase.getInstance().getReference("Users");
            mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mUploads.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    for (DataSnapshot ds : postSnapshot.getChildren()) {
                        if (ds.exists()){
                        check_val = ds.child("workerStatus").getValue().toString();
                        if (check_val.equals(status)) {
                            WorkerProfile workerProfile = ds.getValue(WorkerProfile.class);
                           // workerProfile.setKey(ds.getKey());
                            mUploads.add(workerProfile);

                            WorkerProfile workerProfile1= postSnapshot.getValue(WorkerProfile.class);
                            workerProfile1.setKey(postSnapshot.getKey());
                            mUploads1.add(workerProfile1);
                        }
                        }
                    }

                }
                mAdapter.notifyDataSetChanged();
                mProgressCircle.setVisibility(View.INVISIBLE);

                }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HireWorker.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressCircle.setVisibility(View.INVISIBLE);

            }
        });
    }

    @Override
    public void onItemClick(int position) {
        selectedItem = mUploads.get(position);
        selectedKey = selectedItem.getKey();

        selectedUid= mUploads1.get(position);
        get_Uid= selectedUid.getKey();

        //to copy and save to add to return node
        w_Name=selectedItem.getWorkerName();
        w_phone=selectedItem.getWorkerPhone();
        w_address=selectedItem.getWorkerAddress();
        w_gender=selectedItem.getWorkerGender();
        w_skills=selectedItem.getWorkerSkills();

        ref1 = FirebaseDatabase.getInstance().getReference("Users").child(get_Uid).child("Profile");
        WorkerProfile userInformation = new WorkerProfile(w_Name, w_phone, w_address, w_gender, w_skills, new_status);
        ref1.setValue(userInformation);
        Toast.makeText(HireWorker.this, "Worker is Hired, now you can contact for further details", Toast.LENGTH_LONG).show();
        startActivity(new Intent(HireWorker.this, UserHome.class));
        finish();




    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDatabaseRef.removeEventListener(mDBListener);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(HireWorker.this, UserHome.class));
        finish();
    }
}