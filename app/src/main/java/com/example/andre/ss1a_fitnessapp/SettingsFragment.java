package com.example.andre.ss1a_fitnessapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment implements View.OnClickListener {

    private FirebaseAuth mAuth;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        view.findViewById(R.id.logoutSettingFragmentBtn).setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        // Inflate the layout for this fragment
        return view;
    }

    public void logoutUser() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getActivity(), Login.class));
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.logoutSettingFragmentBtn:
                logoutUser();
                break;
        }
    }
}
