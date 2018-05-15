package com.example.andre.ss1a_fitnessapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CalorieCalc extends AppCompatActivity {

    private EditText age;
    private EditText height;
    private EditText weight;
    private TextView result;
    private Button calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_calc);


        Button calculateBtn = (Button) findViewById(R.id.calculateBtn);

        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        // for females = 10 x (Weight in kg) + 6.25 x (Height in cm) - 5 x age - 161
        //for males = 10 x (Weight in kg) + 6.25 x (Height in cm) - 5 x age + 5

       //sedentary and do not exercise, multiply your BMR by 1.2
        //exercise lightly one to three times per week, multiply by 1.375
        //exercise three to five days per week, multiply by 1.55
        // exercise six or seven days per week, multiply by 1.725
        // exercise seven days a week and also have a physically demanding job, multiply by 1.9.
    }
}
