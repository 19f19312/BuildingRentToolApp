package com.example.ishaq_buildingrenttoolsapp.Owner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ishaq_buildingrenttoolsapp.R;
import com.example.ishaq_buildingrenttoolsapp.ToolsFile;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AddTools extends AppCompatActivity {

    private Button upload, select;
    private int Request_Code= 234;
    private Uri filepath;
    private ImageView img;
    private EditText price, name, detail;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private String u_price, u_name, u_detail;
    private FirebaseAuth firebaseAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tools);

        upload=(Button) findViewById(R.id.upload);
        select= (Button) findViewById(R.id.select);

        price= (EditText) findViewById(R.id.price);
        name= (EditText) findViewById(R.id.name);
        detail= (EditText) findViewById(R.id.detail);
        img= (ImageView)findViewById(R.id.img);

        firebaseAuth= FirebaseAuth.getInstance();
        storageReference= FirebaseStorage.getInstance().getReference();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference= FirebaseDatabase.getInstance().getReference("Tools").child(user.getUid()).child("all");

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImageChooser();
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(filepath!=null){
                    if (validate()){
                        upload();
                    }
                }
                else{
                    Toast.makeText(AddTools.this, "Select tool image", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private Boolean validate(){
        boolean result= false;

        u_price= price.getText().toString();
        u_detail= detail.getText().toString();
        u_name= name.getText().toString();
        if(u_price.isEmpty() || u_detail.isEmpty() || u_name.isEmpty() ){
            Toast.makeText(AddTools.this, "First add all details of Tools", Toast.LENGTH_SHORT).show();
        }else {
            result= true;
        }
        return result;
    }

    private void showImageChooser(){
        Intent intent= new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== 1 && resultCode== RESULT_OK && data != null && data.getData() != null){
            filepath= data.getData();
            img.setImageURI(filepath);

        }
    }

    private String getExtension(Uri uri){
        ContentResolver cr= getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }
    private void upload() {

        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Uploading...");
        progressDialog.show();

        StorageReference reference= storageReference.child("uploads/"+System.currentTimeMillis()+ "."+getExtension(filepath));

        reference.putFile(filepath)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> uri =taskSnapshot.getStorage().getDownloadUrl();
                        while(!uri.isComplete());
                        Uri url= uri.getResult();

                        ToolsFile toolFile = new ToolsFile(u_name, u_price, u_detail, url.toString());
                        databaseReference.child(databaseReference.push().getKey()).setValue(toolFile);

                        progressDialog.dismiss();
                        Toast.makeText(AddTools.this, "Tool details uploaded Successfully", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(AddTools.this, OwnerHome.class));
                        finish();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                        double progress= (100.0 * snapshot.getBytesTransferred()) / snapshot.getTotalByteCount();
                        progressDialog.setMessage("uploaded: " +(int)progress+" %");
                    }
                });

    }


    @Override
    public void onBackPressed() {
        //Display alert message when back button has been pressed
        startActivity(new Intent(AddTools.this, OwnerHome.class));
        finish();
    }
}