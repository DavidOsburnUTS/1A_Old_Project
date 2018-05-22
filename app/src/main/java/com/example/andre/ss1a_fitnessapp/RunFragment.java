package com.example.andre.ss1a_fitnessapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class RunFragment extends Fragment implements View.OnClickListener {


    public RunFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_run, container, false);

        view.findViewById(R.id.startRunFragmentBtn).setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.startRunFragmentBtn:
                startActivity(new Intent(getActivity(), TrackRunActivity.class));
                break;

        }
    }
}
