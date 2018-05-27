package com.example.andre.ss1a_fitnessapp;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment implements View.OnClickListener {

    private FirebaseAuth mAuth;

    private ViewPager mSlideViewPager;

    private HomeSliderAdapter sliderAdapter;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        view.findViewById(R.id.logoutSettingFragmentBtn).setOnClickListener(this);
        view.findViewById(R.id.settingFragmentEditProfileBtn).setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        //ViewPager Facts
        mSlideViewPager = (ViewPager) view.findViewById(R.id.slideViewPagerHome);
        sliderAdapter = new HomeSliderAdapter(getActivity());
        mSlideViewPager.setAdapter(sliderAdapter);

        return view;
    }

    public void logoutUser() {
        FirebaseAuth.getInstance().signOut();
        //startActivity(new Intent(getActivity(), LoginActivity.class));
        //getActivity().finish();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.logoutSettingFragmentBtn:
                logoutUser();
                SharedPreferences pref = getActivity().getSharedPreferences("PREFERENCE",
                        Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putBoolean("isRemembered", false);
                editor.commit();
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
                break;
            case R.id.settingFragmentEditProfileBtn:
                startActivity(new Intent(getActivity(), EditProfileActivity.class));
                break;
        }
    }
}
