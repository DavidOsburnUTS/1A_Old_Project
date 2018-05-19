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
            R.drawable.icon_slide1,
            R.drawable.icon_slide2,
            R.drawable.icon_slide3,
            R.drawable.icon_slide4
    };

    public String [] slide_headings = {
            "WELCOME",
            "SLIDE2",
            "SLIDE3",
            "SLIDE4"
    };

    public String[] slide_descs = {

            "This is the description for slide 1",
            "This is the description for slide 2",
            "This is the description for slide 3",
            "This is the description for slide 4"
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
