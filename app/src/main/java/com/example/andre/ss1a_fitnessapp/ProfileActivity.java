package com.example.andre.ss1a_fitnessapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
<<<<<<< HEAD
=======
import android.widget.LinearLayout;
>>>>>>> f8fc1e502aa93e679d686f53ee12e390c16ffc0a
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    FirebaseUser user;
    String uid;
    TextView Age,Height,Weight,Gender, Name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        findViewById(R.id.profileBackBtn).setOnClickListener(this);
        findViewById(R.id.profileEditBtn).setOnClickListener(this);

        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        Age = (TextView) findViewById(R.id.ageTextView);
        Height = (TextView) findViewById(R.id.heightTextView);
        Weight = (TextView) findViewById(R.id.weightTextView);
        Gender = (TextView) findViewById(R.id.genderTextView);
        Name = (TextView) findViewById(R.id.nameTextView);
        databaseReference = FirebaseDatabase.getInstance().getReference();
<<<<<<< HEAD

=======
        LinearLayout progressBtn = (LinearLayout) findViewById(R.id.progresslayout);

        progressBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Progress.class);
                startActivity(intent);

            }
        });
>>>>>>> f8fc1e502aa93e679d686f53ee12e390c16ffc0a

    databaseReference.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            String name = dataSnapshot.child("Users").child(uid).child("name").getValue(String.class);
            String age = dataSnapshot.child("Users").child(uid).child("age").getValue(String.class);
            String height = dataSnapshot.child("Users").child(uid).child("height").getValue(String.class);
            String weight = dataSnapshot.child("Users").child(uid).child("weight").getValue(String.class);
            String gender = dataSnapshot.child("Users").child(uid).child("gender").getValue(String.class);
            Name.setText(name);
            Age.setText(age);
            Height.setText(height);
            Weight.setText(weight);
            Gender.setText(gender);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.profileBackBtn:
                finish();
                break;
            case R.id.profileEditBtn:
                startActivity(new Intent(this, EditProfileActivity.class));
                break;
<<<<<<< HEAD
            case R.id.progresslayout:
                startActivity(new Intent(this, Progress.class));
                break;
=======
>>>>>>> f8fc1e502aa93e679d686f53ee12e390c16ffc0a
        }
    }

}
