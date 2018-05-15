package com.example.andre.ss1a_fitnessapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class CalorieCalc extends AppCompatActivity {

    private EditText ageEt;
    private EditText heightEt;
    private EditText weightEt;
    private TextView calcResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_calc);

        Button calculateBtn = (Button) findViewById(R.id.calculateBtn);
        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.genderRadioBtn);
        final Spinner activityLvlSpin = (Spinner) findViewById(R.id.activitylevel_spinner);
        int genderId = radioGroup.getCheckedRadioButtonId();
        final RadioButton radioButton = (RadioButton) findViewById(genderId);

        ageEt = (EditText) findViewById(R.id.ageEditText);
        heightEt = (EditText) findViewById(R.id.heightEditText);
        weightEt = (EditText) findViewById(R.id.weightEditText);
        calcResult = (TextView) findViewById(R.id.calcResultTv);


        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int age = Integer.parseInt(ageEt.toString());
                double height = Double.parseDouble(heightEt.toString());
                double weight = Double.parseDouble(weightEt.toString());
                String gender = (String) radioButton.getText();

                calculate(age, height, weight, gender);
            }
        });

        activityLvlSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                int activityId = activityLvlSpin.getSelectedItemPosition();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
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

    private void calculate(int age, double height, double weight, String gender) {
        double result = 0;
        if(gender == "Male") {
            result = 10 * weight * 6.25 * height - 5 * age + 5;
        } else if(gender == "Female") {
            result = 10 * weight * 6.25 * height - 5 * age - 161;
        }

        if(result != 0) {
            calcResult.setText(Double.toString(result));
        } else {
            calcResult.setText("Invalid details");
        }

    }

    //private double activityLvl() {

   // }

}
