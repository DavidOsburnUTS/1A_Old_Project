package com.example.andre.ss1a_fitnessapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        findViewById(R.id.profileBackBtn).setOnClickListener(this);
        findViewById(R.id.profileEditBtn).setOnClickListener(this);
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
        }
    }

}
