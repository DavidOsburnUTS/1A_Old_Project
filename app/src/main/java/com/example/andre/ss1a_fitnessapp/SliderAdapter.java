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
            R.drawable.gsfragmentslide1,
            R.drawable.gsfragmentslide2,
            R.drawable.gsfragmentslide3,
            R.drawable.gsfragmentslide4,
            R.drawable.gsfragmentslide5
    };

    public String [] slide_headings = {
            "WELCOME TO 1UP AT UTS!",
            "TRAIN",
            "CALCULATE",
            "SOCIAL",
            "RUN"
    };

    public String[] slide_descs = {

            "Thank you for signing up at" + "\n1UP Fitness App at UTS.\n\n" +
                    "This short guide will help navigate through our fitness app.",
            "Using 1UP, you can view our extensive list of exercise videos by clicking on Weight or Cardio Training.",
            "Our innovative calorie calculator can you help you achieve the dream weight you always wanted.\n\n" +
                    "1UP calculates the precise amount of calories you need daily/weekly on the fly.",
            "Edit your profile so friends can see you.\n\n" +
                    "You can show off your fitness goals and ranked against friends using our leaderboard system.",
            "Run with 1UP Smart Feature.\n\n Using the phones in-built sensors, we can see how far, fast and quick you run."
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
