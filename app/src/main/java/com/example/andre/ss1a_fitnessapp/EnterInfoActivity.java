package com.example.andre.ss1a_fitnessapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class EnterInfoActivity extends AppCompatActivity {

    EditText Age,Height,Weight, Name;
    RadioGroup Gender;
    RadioButton Male_Female;
    Button doneBtn;

    FirebaseAuth mAuth;
    DatabaseReference UserRef,UserReff,databaseReference,UserReference;
    FirebaseUser user;


    String currentUserID,uid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_info);

        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        UserRef = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserID);
        UserReff = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserID).child("weightHistory");
        UserReference = FirebaseDatabase.getInstance().getReference().child("Leaderboard").child(currentUserID);

        Age = (EditText) findViewById(R.id.ageEditText);
        Height = (EditText) findViewById(R.id.heightEditText);
        Weight = (EditText) findViewById(R.id.weightEditText);
        Gender = (RadioGroup) findViewById(R.id.genderRadioBtn);
        doneBtn = (Button) findViewById(R.id.doneBtn);
        Name = (EditText) findViewById(R.id.nameEditText);

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveAccountInfo();
            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference();

        RadioButton maleBtn = (RadioButton) findViewById(R.id.editProfileMaleBtn);
        maleBtn.setChecked(true);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //    WWeight =Integer.parseInt(dataSnapshot.child("Users").child(uid).child("weight").getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void SaveAccountInfo() {
        final String age = Age.getText().toString();
        String height = Height.getText().toString();
        String weight = Weight.getText().toString();
        int genderID = Gender.getCheckedRadioButtonId();
        String name = Name.getText().toString();
        Male_Female = (RadioButton) findViewById(genderID);
        HashMap weightMap = new HashMap();
        HashMap leaderboard = new HashMap();
        HashMap userMap = new HashMap();

        if(TextUtils.isEmpty(name)){
            //Toast.makeText(this, "Please enter an age",Toast.LENGTH_SHORT).show();
            Name.setError("Please enter a name");
            return;
        }
        if(TextUtils.isEmpty(age)){
            //Toast.makeText(this, "Please enter an age",Toast.LENGTH_SHORT).show();
            Age.setError("Please enter an age");
            return;
        }
        if(TextUtils.isEmpty(height)) {
            //Toast.makeText(this, "Please enter a weight", Toast.LENGTH_SHORT).show();
            Height.setError("Please enter a height");
            return;
        }
        if(TextUtils.isEmpty(weight)){
            //Toast.makeText(this, "Please enter a weight",Toast.LENGTH_SHORT).show();
            Weight.setError("Please enter a weight");
            return;
        }else{
            userMap.put("age", age);
            userMap.put("height", height);
            userMap.put("weight", weight);

            userMap.put("name", name);
            userMap.put("gender", Male_Female.getText());
            //userMap.put("kgSoFar", WWeight - Integer.parseInt(weight));
            userMap.put("points", "?");


            Calendar calForDate = Calendar.getInstance();
            SimpleDateFormat currentDate = new SimpleDateFormat("dd-MMMM-yyyy");
            final String saveCurrentDate = currentDate.format(calForDate.getTime());

            Calendar calForTime = Calendar.getInstance();
            SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss");
            final String saveCurrentTime = currentTime.format(calForTime.getTime());

            final String RandomKey = currentUserID + saveCurrentDate + saveCurrentTime;

            leaderboard.put(name , "points : ");
            UserReference.updateChildren(leaderboard).addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {

                }
            });

            weightMap.put("weight", weight);
            weightMap.put("date", saveCurrentDate +  " at " + saveCurrentTime);
            UserReff.child(RandomKey).updateChildren(weightMap).addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                }
            });
            UserRef.updateChildren(userMap).addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getApplicationContext(), "Entered user info successfully!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EnterInfoActivity.this, GettingStartedActivity.class));
                        finish();
                    }else{
                        String message = task.getException().getMessage();
                        Toast.makeText(getApplicationContext(), "Oh no something went wrong OWO" + message, Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }
}
