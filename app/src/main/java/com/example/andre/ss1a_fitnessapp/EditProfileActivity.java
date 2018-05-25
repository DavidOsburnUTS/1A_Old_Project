package com.example.andre.ss1a_fitnessapp;

import android.content.Intent;
import android.provider.ContactsContract;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.acl.Group;
import java.util.HashMap;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener {
    EditText Age,Height,Weight;
    RadioGroup Gender;
    RadioButton Male_Female;
    Button editProfileDoneBtn;


    FirebaseAuth mAuth;
    DatabaseReference UserRef;

    String currentUserID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        UserRef = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserID);

        findViewById(R.id.editProfileDoneBtn).setOnClickListener(this);
        findViewById(R.id.editProfileExitBtn).setOnClickListener(this);

        Age = (EditText) findViewById(R.id.ageEditText);
        Height = (EditText) findViewById(R.id.heightEditText);
        Weight = (EditText) findViewById(R.id.weightEditText);
        Gender = (RadioGroup) findViewById(R.id.genderRadioBtn);
        editProfileDoneBtn = (Button) findViewById(R.id.editProfileDoneBtn);

        editProfileDoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveAccountInfo();
            }
        });
    }

    private void SaveAccountInfo() {
        String age = Age.getText().toString();
        String height = Height.getText().toString();
        String weight = Weight.getText().toString();
        int genderID = Gender.getCheckedRadioButtonId();
        Male_Female = (RadioButton) findViewById(genderID);




        if(TextUtils.isEmpty(age)){
            Toast.makeText(this, "Please enter an age",Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(age)){
            Toast.makeText(this, "Please enter a height",Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(age)){
            Toast.makeText(this, "Please enter a weight",Toast.LENGTH_SHORT).show();
        }else{
            HashMap userMap = new HashMap();
            userMap.put("age", age);
            userMap.put("height", height);
            userMap.put("weight", weight);

            userMap.put("name", "?");
            userMap.put("gender", Male_Female.getText());
            userMap.put("kgGoal", "?");
            userMap.put("kgSoFar", "?");
            userMap.put("points", "?");
            UserRef.updateChildren(userMap).addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if(task.isSuccessful()){
                        Toast.makeText(EditProfileActivity.this, "Profile edit successful", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(EditProfileActivity.this, ProfileActivity.class));
                    }else{
                        String message = task.getException().getMessage();
                        Toast.makeText(EditProfileActivity.this, "Oh no somthing went wrong " + message, Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.editProfileDoneBtn:
                //Add method with FireBase that grabs info from table and uses setMethod()
                finish();
                break;
            case R.id.editProfileExitBtn:
                finish();
                break;
        }
    }
}
