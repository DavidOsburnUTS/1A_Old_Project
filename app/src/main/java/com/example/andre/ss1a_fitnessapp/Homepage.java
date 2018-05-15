package com.example.andre.ss1a_fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Homepage extends AppCompatActivity {

    //random comment
    // david can you see this?

    private TextView mTextMessage;

    //Bottom Navigation View
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Button calorieBtn = (Button) findViewById(R.id.calorieBtn);
        Button profileBtn = (Button) findViewById(R.id.profileBtn);
        Button cardioBtn = (Button) findViewById(R.id.cardioBtn);
        Button muscleBtn = (Button) findViewById(R.id.muscleBtn);
    }

    public void homeOnClick(View view) {
        switch(view.getId()) {
            case R.id.calorieBtn:
                Intent calorieIntent = new Intent(Homepage.this, CalorieCalc.class);
                startActivity(calorieIntent);
                break;
            case R.id.profileBtn:
                Intent profileIntent = new Intent(Homepage.this, Profile.class);
                startActivity(profileIntent);
                break;
            case R.id.cardioBtn:
                Intent cardioIntent = new Intent(Homepage.this, CardioTraining.class);
                startActivity(cardioIntent);
                break;
            case R.id.weightTrainingBtn:
                Intent weightTrainingIntent = new Intent(Homepage.this, WeightTraining.class);
                startActivity(weightTrainingIntent);
                break;
        }
    }
}
