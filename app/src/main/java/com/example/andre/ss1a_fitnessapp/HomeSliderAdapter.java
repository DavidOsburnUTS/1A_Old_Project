package com.example.andre.ss1a_fitnessapp;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HomeSliderAdapter extends PagerAdapter{

    Context context;
    LayoutInflater layoutInflater;

    public HomeSliderAdapter(Context context) { this.context = context; }

    public String [] slide_headings = {
            "FACTS ABOUT COOKIES",
            "FACTS ABOUT COOKIES",
            "FACTS ABOUT COOKIES",
            "FACTS ABOUT COOKIES"
    };

    public String[] slide_descs = {

            "The ANZAC biscuit is indeed not a cookie",
            "When Yao Ming played his first game in Miami, Miami Heat promoted the game by passing out 8,000 fortune cookies.",
            "Bruce Willis bought 12,000 girl scout cookies from his daughter and sent them to troops in the middle east and sailors among the USS John F Kennedy.",
            "Oreo cookies are actually the knock-off brand of another company, Hydrox."
    };

    @Override
    public int getCount() { return slide_headings.length; }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (RelativeLayout) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.home_slide_layout, container, false);

        TextView slideHeading = (TextView) view.findViewById(R.id.heading_slide);
        TextView slideDescription = (TextView) view.findViewById(R.id.desc_slide);

        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_descs[position]);

        container.addView(view);

        return view;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout)object);
    }

}
