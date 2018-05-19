package com.example.andre.ss1a_fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

public class Homepage extends AppCompatActivity {

    //random comment
    // david can you see this?

    private BottomNavigationView mainNav;
    private FrameLayout mainFrame;

    private TextView mTextMessage;


    //private HomeFragment homeFragment;
    //private StartRunFragment startRunFragment;
    //private SettingsFragment settingsFragment;

    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        mainFrame = (FrameLayout) findViewById(R.id.main_frame);
        mainNav = (BottomNavigationView) findViewById(R.id.mainNav);

        homeFragment = new HomeFragment();4 startRunFragment = new StartRunFragment();
        settingsFragment = new SettingsFragment();

        setFragment(homeFragment);

        //Bottom Navigation View
       mainNav.OnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener());

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.navigation_home:
                        //mTextMessage.setText(R.string.title_home);

                        setFragment(homeFragment);
                        return true;

                    case R.id.navigation_start_run:
                        //mTextMessage.setText(R.string.title_start_run);
                        Intent startRunIntent = new Intent(Homepage.this, Run.class);
                        startActivity(startRunIntent);

                        setFragment(startRunFragment);
                        return true;

                    case R.id.navigation_settings:
                        //mTextMessage.setText(R.string.title_settings);
                        Intent startSettingsIntent = new Intent(Homepage.this, Settings.class);
                        startActivity(startSettingsIntent);

                        setFragment(settingRunFragment);
                        return true;

                    default:
                        return false;
                }
            }
        };
    }

    private void setFragment (Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmm
    }
}*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView mainNav = (BottomNavigationView) findViewById(R.id.mainNav);
        //mainNav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //mainNav.OnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener());

        Button calorieBtn = (Button) findViewById(R.id.calorieBtn);
        Button profileBtn = (Button) findViewById(R.id.profileBtn);
        Button cardioBtn = (Button) findViewById(R.id.cardioBtn);
        Button weightTrainingBtn = (Button) findViewById(R.id.weightTrainingBtn);

        mainFrame = (FrameLayout) findViewById(R.id.main_frame);
        mainNav = (BottomNavigationView) findViewById(R.id.mainNav);

        //homeFragment = new HomeFragment();
        //startRunFragment = new StartRunFragment();
        //settingsFragment = new SettingsFragment();

        //setFragment(homeFragment);

        //Bottom Navigation View
        //mainNav.OnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener());
/*
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {

                case R.id.navigation_home:

                    return true;

                case R.id.navigation_start_run:

                    return true;

                case R.id.navigation_settings:

                    //setFragment(settingRunFragment);
                    return true;

                default:
                    return false;
            }
        }*/
    };

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
