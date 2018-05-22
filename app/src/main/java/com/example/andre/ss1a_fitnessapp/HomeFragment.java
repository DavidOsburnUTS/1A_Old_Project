package com.example.andre.ss1a_fitnessapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener
{

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        view.findViewById(R.id.calorieBtn).setOnClickListener(this);
        view.findViewById(R.id.profileBtn).setOnClickListener(this);
        view.findViewById(R.id.cardioBtn).setOnClickListener(this);
        view.findViewById(R.id.weightTrainingBtn).setOnClickListener(this);

        //return inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.calorieBtn:
                Intent calorieIntent = new Intent(getActivity(), CalorieCalculatorActivity.class);
                startActivity(calorieIntent);
                break;
            case R.id.profileBtn:
                Intent profileIntent = new Intent(getActivity(), ProfileActivity.class);
                startActivity(profileIntent);
                break;
            case R.id.cardioBtn:
                Intent cardioIntent = new Intent(getActivity(), CardioTrainingActivity.class);
                startActivity(cardioIntent);
                break;
            case R.id.weightTrainingBtn:
                Intent weightTrainingIntent = new Intent(getActivity(), WeightTrainingActivity.class);
                startActivity(weightTrainingIntent);
                break;
        }
    }
}
