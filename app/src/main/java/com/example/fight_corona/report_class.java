package com.example.fight_corona;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;

public class report_class extends AppCompatActivity {

    EditText txtName,txtPhone,txtAadhar,txtEmail;
    CircleImageView imageView;
    DatabaseReference reff;
    Reporters reporter;
    Button upload;
    long maxid = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_class);

        getSupportActionBar().setTitle("Fight Corona");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initialize();

        reporter = new Reporters();
        reff = FirebaseDatabase.getInstance().getReference().child("Reporters");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    maxid = (dataSnapshot.getChildrenCount());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reporter.setName(txtName.getText().toString().trim());
                reporter.setAadhar(txtAadhar.getText().toString().trim());
                reporter.setEmail(txtEmail.getText().toString().trim());
                reporter.setPhone(txtPhone.getText().toString().trim());
                reff.child(String.valueOf(maxid+1)).setValue(reporter);

                Toast.makeText(report_class.this, "Data inserted successfully!", Toast.LENGTH_SHORT).show();
            }
        });



    }

    private void initialize() {
        upload = findViewById(R.id.btnUp);
        txtPhone = findViewById(R.id.txtPhone);
        txtAadhar = findViewById(R.id.txtAadhar);
        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        imageView = findViewById(R.id.imageView);
    }


}