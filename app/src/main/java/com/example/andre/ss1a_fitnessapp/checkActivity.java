package com.example.andre.ss1a_fitnessapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class checkActivity extends AppCompatActivity {

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        mAuth = FirebaseAuth.getInstance();

        SharedPreferences pref = getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        boolean isRemembered = pref.getBoolean("isRemembered", false);


        if(isRemembered) {
            userLogin();
            Intent homePageIntent = new Intent(checkActivity.this, HomepageActivity.class);
            startActivity(homePageIntent);
            finish();
        }
        else {
            Intent intent = new Intent(checkActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void userLogin(){

        SharedPreferences pref = getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        String email = pref.getString("rememberEmail", "DEFAULT");
        String password = pref.getString("rememberPw", "DEFAULT");

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Logged in!",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
