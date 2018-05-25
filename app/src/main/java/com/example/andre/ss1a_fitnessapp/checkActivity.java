package com.example.andre.ss1a_fitnessapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class checkActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        SharedPreferences pref = getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        boolean isRemembered = pref.getBoolean("isRemembered", false);

        if(isRemembered) {
            Intent homePageIntent = new Intent(checkActivity.this, HomepageActivity.class);
            startActivity(homePageIntent);
            finish();
        }
        else {
            Intent intent = new Intent(checkActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
