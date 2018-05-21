package com.example.andre.ss1a_fitnessapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editText;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        editText = (EditText) findViewById(R.id.forgotPasswordEmailEditText);

        //Init Firebase
        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.resetPasswordBtn).setOnClickListener(this);
    }

    private void resetPassword() {
        String email = editText.getText().toString().trim();

        if (email.isEmpty()) {
            editText.setError("Email is required");
            editText.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){   // THIS METHOD CHECKS IF ITS A REAL EMAIL
            editText.setError("Please enter a valid email");
            editText.requestFocus();
            return;
        }

        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),"You've successfully reset your password", Toast.LENGTH_SHORT).show();
                            //startActivity(new Intent(ForgotPasswordActivity.this, Login.class));
                        }
                    }
                });
    }

    //Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.resetPasswordBtn:
                resetPassword();
                break;
        }
    }
}
