package com.example.andre.ss1a_fitnessapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class RunFragment extends Fragment implements View.OnClickListener {

    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;

    private TextView[] mDots;

    private RunSliderAdapter sliderAdapter;

    public RunFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_run, container, false);

        //Buttons
        view.findViewById(R.id.startRunFragmentBtn).setOnClickListener(this);

        //ViewPager
        mSlideViewPager = (ViewPager) view.findViewById(R.id.slideViewPagerRun);
        mDotLayout = (LinearLayout) view.findViewById(R.id.dotRunLayout);

        sliderAdapter = new RunSliderAdapter(getActivity());

        mSlideViewPager.setAdapter(sliderAdapter);

        addDotsIndicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListener);

        java.util.Timer timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 2000,4000);
        return view;

    }


    public class MyTimerTask extends TimerTask {

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
        switch (view.getId()) {
            case R.id.startRunFragmentBtn:
                startActivity(new Intent(getActivity(), TrackRunActivity.class));
                break;

        }
    }

    public void addDotsIndicator(int position) {
        mDots = new TextView[4];
        mDotLayout.removeAllViews();

        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(getActivity());
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorGray));

            mDotLayout.addView(mDots[i]);
        }

        if (mDots.length > 0) {
            mDots[position].setTextColor(getResources().getColor(R.color.colorPrimary));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }
        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
        }
        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };

}
