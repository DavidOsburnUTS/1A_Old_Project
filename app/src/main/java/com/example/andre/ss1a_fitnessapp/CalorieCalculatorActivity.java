package com.example.andre.ss1a_fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CalorieCalculatorActivity extends AppCompatActivity {

    private EditText ageEt;
    private EditText heightEt;
    private EditText weightEt;
    private TextView calcResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_calc);

        final RadioGroup radioGroup = findViewById(R.id.genderRadioBtn);
        final Spinner activity = findViewById(R.id.activity_level_spinner);
        final Spinner goal = findViewById(R.id.goal_spinner);

        ageEt = findViewById(R.id.ageEditText);
        heightEt = findViewById(R.id.heightEditText);
        weightEt = findViewById(R.id.weightEditText);
        calcResult = findViewById(R.id.calcResultTv);

        Button calculateBtn = findViewById(R.id.calculateBtn);

        final String[] Level = new String[]{
                "Sedentary (Little to no exercise)", "Light (1-3 days)", "Moderate (3-5 days)",
                "Very Active (6-7 days)", "Extra Active (7 days/physically demanding job)"};
        final String[] Goal = new String[]{
                "Maintain", "Lose", "Gain"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, Level);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activity.setAdapter(adapter);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, Goal);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        goal.setAdapter(adapter1);

        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int age = Integer.parseInt(ageEt.getText().toString());
                double height = Double.parseDouble(heightEt.getText().toString());
                double weight = Double.parseDouble(weightEt.getText().toString());
                int genderId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(genderId);
                String gender = radioButton.getText().toString();
                int activityId = activity.getSelectedItemPosition();
                int goalId = goal.getSelectedItemPosition();

                calculate(age, height, weight, gender, activityId, goalId);
            }
        });
    }

    private void calculate(int age, double height, double weight, String gender, int activityId, int goalId) {
        //males = 10 x (Weight in kg) + 6.25 x (Height in cm) - 5 x age + 5
        //females = 10 x (Weight in kg) + 6.25 x (Height in cm) - 5 x age - 161

        double result = 0;
        if(gender.equals("Male")) {
            result = (10 * weight + 6.25 * height - 5 * age + 5) * getActivityLvl(activityId);
        } else if(gender.equals("Female")) {
            result = (10 * weight + 6.25 * height - 5 * age - 161) * getActivityLvl(activityId);
        }

        if(age <= 0){
            ageEt.setError("Age is missing");
            ageEt.requestFocus();
            return;
        }

        if(result > 0) {
            if (goalId == 0) {
                calcResult.setText(String.format("%.0f", result) + " calories/day to maintain weight");
            } else if (goalId == 1) {
                calcResult.setText(String.format("%.0f", result - 1000) + " calories/day to lose 1kg/week");
            } else if (goalId == 2) {
                calcResult.setText(String.format("%.0f", result + 1000) + " calories/day to gain 1kg/week");
            }
        } else {
            calcResult.setText("Invalid details");
        }
    }

    private double getActivityLvl(int activityId) {

        //sedentary/no exercise = 1.2
        //exercise lightly one to three times per week = 1.375
        //exercise three to five days per week = 1.55
        //exercise six or seven days per week = 1.725
        //exercise seven days a week, have a physically demanding job = 1.9

        if(activityId == 0) {
            return 1.2;
        } else if(activityId == 1) {
            return 1.375;
        } else if(activityId == 2) {
            return 1.55;
        } else if(activityId == 3) {
            return 1.725;
        } else if(activityId == 4) {
            return 1.9;
        }
        return -1;
    }
}
/*
public class CalorieCalculatorActivity extends AppCompatActivity {

    private EditText ageEt;
    private EditText heightEt;
    private EditText weightEt;
    private TextView calcResult;
    //private Spinner goalSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_calc);

        //findViewById(R.id.calorieCalcBackBtn).setOnClickListener(this);
        //findViewById(R.id.calculateBtn).setOnClickListener(this);

        final RadioGroup radioGroup = findViewById(R.id.genderRadioBtn);
        final Spinner goal = findViewById(R.id.goal_spinner);
        final Spinner activity = findViewById(R.id.activity_level_spinner);

        ageEt = findViewById(R.id.ageEditText);
        heightEt = findViewById(R.id.heightEditText);
        weightEt = findViewById(R.id.weightEditText);
        calcResult = findViewById(R.id.calcResultTv);

        final String[] Goal = new String[]{
                "Maintain",
                "Lose",
                "Gain"
        };

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, Goal);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        goal.setAdapter(adapter1);

        final String[] Level = new String[]{
                "Sedentary (Little to no exercise)",
                "Light (1-3 days)",
                "Moderate (3-5 days)",
                "Very Active (6-7 days)",
                "Extra Active (7 days/physically demanding job)"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, Level);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activity.setAdapter(adapter);

        Button calculateBtn = findViewById(R.id.calculateBtn);
        Button calorieCalcBackBtn = findViewById(R.id.calorieCalcBackBtn);

        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int age = Integer.parseInt(ageEt.getText().toString());
                double height = Double.parseDouble(heightEt.getText().toString());
                double weight = Double.parseDouble(weightEt.getText().toString());
                int genderId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(genderId);
                String gender = radioButton.getText().toString();
                int activityId = activity.getSelectedItemPosition();
                int goalId = goal.getSelectedItemPosition();

                if(age <= 0) {
                    ageEt.setError("Age is missing");
                    ageEt.requestFocus();
                    return;
                }
                if(height <= 0){
                    heightEt.setError("Height is missing");
                    heightEt.requestFocus();
                    return;
                }
                if(weight <= 0){
                    weightEt.setError("Weight is missing");
                    weightEt.requestFocus();
                    return;
                }

                calculate(age, height, weight, gender, activityId, goalId);
            }
        });

        calorieCalcBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent backActivity = new Intent(new Intent(this, HomepageActivity.class));
                //startActivity(backActivity);
            }
        });
    }

    private void calculate(int age, double height, double weight, String gender, int activityId, int goalId) {
        //males = 10 x (Weight in kg) + 6.25 x (Height in cm) - 5 x age + 5
        //females = 10 x (Weight in kg) + 6.25 x (Height in cm) - 5 x age - 161

        double result = 0;
        if(gender.equals("Male")) {
            result = (10 * weight + 6.25 * height - 5 * age + 5) * getActivityLvl(activityId);
        } else if(gender.equals("Female")) {
            result = (10 * weight + 6.25 * height - 5 * age - 161) * getActivityLvl(activityId);
        }

        if(result > 0) {
            if (goalId == 0) {
                calcResult.setText(String.format("%.0f", result) + " calories/day to maintain weight");
            } else if (goalId == 1) {
                calcResult.setText(String.format("%.0f", result - 1000) + " calories/day to lose 1kg/week");
            } else if (goalId == 2) {
                calcResult.setText(String.format("%.0f", result + 1000) + " calories/day to gain 1kg/week");
            }
        } else {
            calcResult.setText("Invalid details");
        }
    }

    private double getActivityLvl(int activityId) {

        //sedentary/no exercise = 1.2
        //exercise lightly one to three times per week = 1.375
        //exercise three to five days per week = 1.55
        //exercise six or seven days per week = 1.725
        //exercise seven days a week, have a physically demanding job = 1.9

        if(activityId == 0) {
            return 1.2;
        } else if(activityId == 1) {
            return 1.375;
        } else if(activityId == 2) {
            return 1.55;
        } else if(activityId == 3) {
            return 1.725;
        } else if(activityId == 4) {
            return 1.9;
        }
        return -1;
    }
/*
    private void calculate() {

        //RadioGroup radioGroup = findViewById(R.id.genderRadioBtn);
        //Spinner activity = findViewById(R.id.activitylevel_spinner);
        //Spinner goal = findViewById(R.id.goal_spinner);

        //Spinner goal = findViewById(R.id.goal_spinner);
        //Spinner activity = findViewById(R.id.activitylevel_spinner);

        //RadioButton radioButton = findViewById(genderId);
        //String gender = radioButton.getText().toString();

        int age = Integer.parseInt(ageEt.getText().toString());
        double height = Double.parseDouble(heightEt.getText().toString());
        double weight = Double.parseDouble(weightEt.getText().toString());

        RadioButton female = (RadioButton) findViewById(R.id.femaleBtn);
        RadioButton male = (RadioButton) findViewById(R.id.femaleBtn);
        RadioGroup radioGroup = findViewById(R.id.genderRadioBtn);
        int genderId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(genderId);
        String gender = radioButton.getText().toString();

        //int activityId = activity.getSelectedItemPosition();
        //int goalId = goalSpinner.getSelectedItemPosition();

        female.setError(null);

        if(age <= 0){
            ageEt.setError("Age is missing");
            ageEt.requestFocus();
            return;
        }
        if(radioGroup.getCheckedRadioButtonId() == -1){//Grp is your radio group object
            if(!(female.isChecked() || male.isChecked())) {
                female.setError("Select Item");//Set error to last Radio button
                radioGroup.requestFocus();
                return;
            }
            //Toast.makeText(getApplicationContext(), "Please select Gender", Toast.LENGTH_SHORT).show();
        }
        if(height <= 0){
            heightEt.setError("Height is missing");
            heightEt.requestFocus();
            return;
        }
        if(weight <= 0){
            weightEt.setError("Weight is missing");
            weightEt.requestFocus();
            return;
        }

        //getCalorieCount(age, height, weight, gender, activityId, goalId);
    }
    */
/*
    private void getCalorieCount(int age, double height, double weight, String gender, int activityId, int goalId) {
        //males = 10 x (Weight in kg) + 6.25 x (Height in cm) - 5 x age + 5
        //females = 10 x (Weight in kg) + 6.25 x (Height in cm) - 5 x age - 161

        double result = 0;
        if(gender.equals("Male")) {
            result = (10 * weight + 6.25 * height - 5 * age + 5) * getActivityLvl(activityId);
        } else if(gender.equals("Female")) {
            result = (10 * weight + 6.25 * height - 5 * age - 161) * getActivityLvl(activityId);
        }

        if(result > 0) {
            if (goalId == 0) {
                calcResult.setText(String.format("%.0f", result) + " calories/day to maintain weight");
            } else if (goalId == 1) {
                calcResult.setText(String.format("%.0f", result - 1000) + " calories/day to lose 1kg/week");
            } else if (goalId == 2) {
                calcResult.setText(String.format("%.0f", result + 1000) + " calories/day to gain 1kg/week");
            }
        } else {
            calcResult.setText("Invalid details");
        }
    }*/
/*
    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.calculateBtn:
                calculate();
                break;
            case R.id.calorieCalcBackBtn:
                startActivity(new Intent(this, HomepageActivity.class));
                break;
        }
    }

}*/
