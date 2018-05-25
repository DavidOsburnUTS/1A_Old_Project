package com.example.andre.ss1a_fitnessapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    FirebaseAuth mAuth;

    private EditText emailEditText;
    private EditText passwordEditText;
    private boolean isFirstRun;
    private CheckBox rememberMe;
    private boolean isRemembered;
    private String rememberEmail;
    private String rememberPw;
    private String mEmail;
    private String mPw;
    //private TextView result;
    //private Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = (EditText) findViewById(R.id.emailEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        rememberMe = (CheckBox) findViewById(R.id.rememberMe_checkBox);

        mAuth = FirebaseAuth.getInstance();

         findViewById(R.id.login_btn).setOnClickListener(this);
         findViewById(R.id.loginForgotPasswordBtn).setOnClickListener(this);

        isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);

        isRemembered = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isRemembered", false);

        rememberMe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked) {
                    isRemembered = getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                            .putBoolean("isRemembered", true).commit();

                }
                else {
                    isRemembered = getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                            .putBoolean("isRemembered", false).commit();
                }
            }
        });

//=================================================================================================
// Attempt to launch the register activity within the app
        Button registerBtn = (Button) findViewById(R.id.registerBtn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent RegisterIntent = new Intent(LoginActivity.this, Register.class);
                startActivity(RegisterIntent);
            }
        });
//=================================================================================================

        Button forgetPasswordBtn = (Button) findViewById(R.id.loginForgotPasswordBtn);

        forgetPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ForgotPasswordIntent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(ForgotPasswordIntent);
            }
        });
    }
//=================================================================================================
// If user is registered, user can login to the app using appropriate text
    private void userLogin(){
        String email = emailEditText.getText().toString().trim();
        mEmail = email;
        String password = passwordEditText.getText().toString().trim();
        mPw = password;

        if(email.isEmpty()){
            emailEditText.setError("Email is required");
            emailEditText.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){   // THIS METHOD CHECKS IF ITS A REAL EMAIL
            emailEditText.setError("Please enter a valid email");
            emailEditText.requestFocus();
            return;
        }
        if(password.isEmpty()){
            passwordEditText.setError("Password is required");
            passwordEditText.requestFocus();
            return;
        }
        if(password.length()< 6){
            passwordEditText.setError("Minimum characters for a password is 6");
            passwordEditText.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    if(isFirstRun) {
                        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                                .putBoolean("isFirstRun", false).commit();

                        Intent gettingStartedIntent = new Intent(LoginActivity.this, GettingStartedActivity.class);
                        gettingStartedIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(gettingStartedIntent);
                        finish();
                    }
                    else {
                        Intent homePageIntent = new Intent(LoginActivity.this, HomepageActivity.class);
                        startActivity(homePageIntent);
                        finish();
                    }

                } else {
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void checkRemember() {
        if(isRemembered) {
            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                    .putString("rememberEmail", mEmail).commit();

            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                    .putString("rememberPw", mPw).commit();

        }
        else {
            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                    .putString("rememberEmail", "").commit();

            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                    .putString("rememberPw", "").commit();
        }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.login_btn:
                userLogin();
                checkRemember();
                break;
        }
    }
}
