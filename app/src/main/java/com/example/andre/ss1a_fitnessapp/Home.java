package com.example.andre.ss1a_fitnessapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button calorieBtn = (Button) findViewById(R.id.calorieBtn);
        Button profileBtn = (Button) findViewById(R.id.profileBtn);
        Button cardioBtn = (Button) findViewById(R.id.cardioBtn);
        Button weightTrainingBtn = (Button) findViewById(R.id.weightTrainingBtn);
    }
}
