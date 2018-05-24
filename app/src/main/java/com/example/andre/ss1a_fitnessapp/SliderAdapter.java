package com.example.andre.ss1a_fitnessapp;

import android.content.Context;
import android.media.Image;
import android.support.v4.view.PagerAdapter;
import android.support.v4.widget.TextViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.RelativeLayout;

public class SliderAdapter extends PagerAdapter{

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    public int[] slide_images = {
            R.drawable.oneup_main,
            R.drawable.track,
            R.drawable.eat,
            R.drawable.profile
    };

    public String [] slide_headings = {
            "WELCOME",
            "TRAIN",
            "EAT",
            "SOCIAL"
    };

    public String[] slide_descs = {

            "Thank you for downloading 1UP Fitness App.\n\n" +
                    "This short guide will help you how to use our user friendly fitness app",
            "By selecting Weight or Cardio on the homepage, you can browse through our wide array of exercise videos.",
            "Our calorie calculator can you help you achieve the dream weight you always wanted.\n\n" +
                    "We can show you the amount calorie you need daily.",
            "Edit your profile so friends can see you.\n\n" +
                    "You can show off your fitness goals and achievement with friends."
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
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

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
