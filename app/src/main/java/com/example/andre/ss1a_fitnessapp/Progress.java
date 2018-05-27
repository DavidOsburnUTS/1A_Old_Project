package com.example.andre.ss1a_fitnessapp;

<<<<<<< HEAD
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
=======
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

>>>>>>> f8fc1e502aa93e679d686f53ee12e390c16ffc0a
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.Series;

<<<<<<< HEAD
import java.util.HashMap;


public class Progress extends AppCompatActivity {
    EditText Goal, Start, Current;
    Button EnterButton;
    DatabaseReference UserRef;

    String currentUserID;
=======



public class Progress extends AppCompatActivity {
>>>>>>> f8fc1e502aa93e679d686f53ee12e390c16ffc0a

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

<<<<<<< HEAD
        UserRef = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserID);

        Goal = (EditText) findViewById(R.id.goalEditText);
        Start = (EditText) findViewById(R.id.startWeightEditText);
        Current = (EditText) findViewById(R.id.currentWeightEditText);

=======
>>>>>>> f8fc1e502aa93e679d686f53ee12e390c16ffc0a

        /*
        GraphView graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        graph.addSeries(series);*/
    }

<<<<<<< HEAD
    private void SaveAccountInfo() {
        String goal = Goal.getText().toString();
        String start = Start.getText().toString();
        String current = Current.getText().toString();

        if (TextUtils.isEmpty(goal)) {
            //Toast.makeText(this, "Please enter an age",Toast.LENGTH_SHORT).show();
            Goal.setError("Please enter your goal weight");
            return;
        }
        if (TextUtils.isEmpty(start)) {
            //Toast.makeText(this, "Please enter an age",Toast.LENGTH_SHORT).show();
            Start.setError("Please enter your starting weight");
            return;
        }
        if (TextUtils.isEmpty(current)) {
            //Toast.makeText(this, "Please enter a weight", Toast.LENGTH_SHORT).show();
            Current.setError("Please enter your current weight");
            return;
        } else {
            HashMap userMap = new HashMap();
            userMap.put("goalweight", goal);
            userMap.put("startweight", start);
            userMap.put("currentweight", current);
            UserRef.updateChildren(userMap).addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(Progress.this, "Recorded  successful", Toast.LENGTH_LONG).show();
                        //startActivity(new Intent(EditProfileActivity.this, ProfileActivity.class));
                        finish();
                    } else {
                        String message = task.getException().getMessage();
                        Toast.makeText(Progress.this, "Oh no something went wrong OWO" + message, Toast.LENGTH_SHORT).show();
                    }
                }
            });

=======
    public void progressOnClick(View view) {
        switch(view.getId()){
            case R.id.progressBackBtn:
                finish();
                break;
            case R.id.progressSubmitBtn:
                break;
>>>>>>> f8fc1e502aa93e679d686f53ee12e390c16ffc0a
        }
    }
}
