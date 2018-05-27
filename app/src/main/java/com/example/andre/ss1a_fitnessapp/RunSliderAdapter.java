package com.example.andre.ss1a_fitnessapp;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class RunSliderAdapter extends PagerAdapter{

    Context context;
    LayoutInflater layoutInflater;

    public RunSliderAdapter(Context context) {
        this.context = context;
    }

    public int[] slide_images = {
            R.drawable.runfragmentslide1,
            R.drawable.runfragmentslide2,
            R.drawable.runfragmentslide3,
            R.drawable.runfragmentslide4
    };

    public String [] slide_headings = {
            "TRACK",
            "DISTANCE",
            "TIME",
            "EARN POINTS"
    };

    public String[] slide_descs = {

            "Precise GPS tracks you running",
            "Measures how far you run",
            "Tracks how fast you are",
            "Earn points by running further and longer"
    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (RelativeLayout) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.run_slide_layout, container, false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.icon_slide);
        TextView slideHeading = (TextView) view.findViewById(R.id.heading_slide);
        TextView slideDescription = (TextView) view.findViewById(R.id.desc_slide);

        slideImageView.setImageResource(slide_images[position]);
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
