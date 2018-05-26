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
            R.drawable.oneup_logo_500dp_v2,
            R.drawable.track,
            R.drawable.eat,
            R.drawable.profile
    };

    public String [] slide_headings = {
            "TRACK",
            "DISTANCE",
            "TIME",
            "EARN POINTS"
    };

    public String[] slide_descs = {

            "With 1UP, you can track your run every time",
            "Records how far you run",
            "Records how long you run",
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
