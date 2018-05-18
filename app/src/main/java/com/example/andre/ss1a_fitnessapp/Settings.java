package com.example.andre.ss1a_fitnessapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class Settings extends AppCompatActivity {

    private TextView mTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }
    //Bottom Navigation View
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    Intent startHomepageIntent = new Intent(Settings.this, Homepage.class);
                    startActivity(startHomepageIntent);
                    return true;
                case R.id.navigation_run:
                    mTextMessage.setText(R.string.title_start_run);
                    Intent runIntent = new Intent(Settings.this, Run.class);
                    startActivity(runIntent);
                    return true;
            }
            return false;
        }
    };
}
