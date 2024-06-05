package com.example.ishaq_buildingrenttoolsapp.Worker;

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

import com.example.ishaq_buildingrenttoolsapp.Owner.DeleteTools;
import com.example.ishaq_buildingrenttoolsapp.Owner.ImageAdapterDeleteTool;
import com.example.ishaq_buildingrenttoolsapp.Owner.OwnerHome;
import com.example.ishaq_buildingrenttoolsapp.R;
import com.example.ishaq_buildingrenttoolsapp.ToolsFile;
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

public class WorkerRentTool extends AppCompatActivity implements ImageAdapterWorkerRentTools.OnItemClickListener{
    private RecyclerView mRecyclerView;
    private ImageAdapterWorkerRentTools mAdapter;
    private DatabaseReference mDatabaseRef;
    private FirebaseStorage mStrorage;
    private FirebaseAuth firebaseAuth;

    private ValueEventListener mDBListener;
    private FirebaseUser user;
    private List<ToolsFile> mUploads, mUploads1;
    private ProgressBar mProgressCircle;
    private FirebaseDatabase firebaseDatabase;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_rent_tool);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        mStrorage = FirebaseStorage.getInstance();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view2);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mProgressCircle = (ProgressBar) findViewById(R.id.progress_circle2);

        mUploads = new ArrayList<>();
        mUploads1 = new ArrayList<>();
        mAdapter = new ImageAdapterWorkerRentTools(WorkerRentTool.this, mUploads);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(WorkerRentTool.this);

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Tools");
        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mUploads.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    for (DataSnapshot ds : postSnapshot.child("all").getChildren()) {
                        ToolsFile toolFile = ds.getValue(ToolsFile.class);
                        toolFile.setKey(ds.getKey());
                        mUploads.add(toolFile);
                        ToolsFile toolFile1= postSnapshot.getValue(ToolsFile.class);
                        toolFile1.setKey(postSnapshot.getKey());
                        mUploads1.add(toolFile1);
                    }

                }
                mAdapter.notifyDataSetChanged();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(WorkerRentTool.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        ToolsFile selectUId= mUploads1.get(position);
        final String selectedUID= selectUId.getKey();

        ToolsFile selectedItem = mUploads.get(position);
        final String selectedKey = selectedItem.getKey();

        Intent intent = new Intent(WorkerRentTool.this, WorkerToolPayment.class);
        intent.putExtra("key", selectedKey);
        intent.putExtra("uid", selectedUID);
        startActivity(intent);
        finish();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDatabaseRef.removeEventListener(mDBListener);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(WorkerRentTool.this, WorkerHome.class));
        finish();
    }
}