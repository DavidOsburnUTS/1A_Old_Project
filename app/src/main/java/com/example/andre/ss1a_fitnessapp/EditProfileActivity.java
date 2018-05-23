package com.example.andre.ss1a_fitnessapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        findViewById(R.id.editProfileDoneBtn).setOnClickListener(this);
        findViewById(R.id.editProfileExitBtn).setOnClickListener(this);
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
