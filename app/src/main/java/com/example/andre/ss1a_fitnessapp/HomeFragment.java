package com.example.andre.ss1a_fitnessapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener
{

    private ViewPager mSlideViewPager;

    private HomeSliderAdapter sliderAdapter;

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;

    private ProgressBar progressBar;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //Buttons
        view.findViewById(R.id.calorieBtn).setOnClickListener(this);
        view.findViewById(R.id.profileBtn).setOnClickListener(this);
        view.findViewById(R.id.cardioBtn).setOnClickListener(this);
        view.findViewById(R.id.weightTrainingBtn).setOnClickListener(this);
        view.findViewById(R.id.progresslayout).setOnClickListener(this);

        //Progress Bar
        progressBar = view.findViewById(R.id.progressBar);

        progressBar.setProgress(75);

        //ViewPager Facts
        mSlideViewPager = (ViewPager) view.findViewById(R.id.slideViewPagerHome);

        sliderAdapter = new HomeSliderAdapter(getActivity());

        mSlideViewPager.setAdapter(sliderAdapter);

        //ViewPager Auto Sweep
        //java.util.Timer timer = new java.util.Timer();
        //timer.scheduleAtFixedRate(new MyTimerTask(), 2000,10000);

        return view;
    }

        public class MyTimerTask extends java.util.TimerTask {
        @Override
        public void run() {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(mSlideViewPager.getCurrentItem() == 0) {
                        mSlideViewPager.setCurrentItem(1);
                    }
                    else if(mSlideViewPager.getCurrentItem() == 1) {
                        mSlideViewPager.setCurrentItem(2);
                    }
                    else if(mSlideViewPager.getCurrentItem() == 2) {
                        mSlideViewPager.setCurrentItem(3);
                    }
                    else if(mSlideViewPager.getCurrentItem() == 3) {
                        mSlideViewPager.setCurrentItem(0);
                    }
                }
            });
         }
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
            case R.id.progresslayout:
                Intent progressIntent = new Intent(getActivity(), Progress.class);
                startActivity(progressIntent);
                break;
        }
    }

}
