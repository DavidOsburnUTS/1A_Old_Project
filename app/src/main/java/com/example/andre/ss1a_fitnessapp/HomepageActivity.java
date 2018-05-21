package com.example.andre.ss1a_fitnessapp;

import android.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class HomepageActivity extends AppCompatActivity {

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;

    private HomeFragment homeFragment;
    private RunFragment runFragment;
    private SettingsFragment settingsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        mMainFrame = (FrameLayout) findViewById(R.id.homepage_main_frame);
        mMainNav = (BottomNavigationView) findViewById(R.id.main_nav);

        homeFragment = new HomeFragment();
        runFragment = new RunFragment();
        settingsFragment = new SettingsFragment();

        setFragment(homeFragment);

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        mMainNav.setItemBackgroundResource(R.color.colorAccent);
                        setFragment(homeFragment);
                        return true;
                    case R.id.navigation_start_run:
                        mMainNav.setItemBackgroundResource(R.color.colorGray);
                        setFragment(runFragment);
                        return true;
                    case R.id.navigation_settings:
                        setFragment(settingsFragment);
                        mMainNav.setItemBackgroundResource(R.color.colorPrimaryDark);
                        return true;

                    default:
                        return false;
                }
            }
        });
    }

    private void setFragment(Fragment fragment) {

        FragmentTransaction fT = getSupportFragmentManager().beginTransaction();
        fT.replace(R.id.homepage_main_frame, fragment);
        fT.commit();
    }
}
