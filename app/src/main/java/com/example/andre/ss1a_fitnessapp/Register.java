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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class Register extends AppCompatActivity implements View.OnClickListener {

 EditText editText2,editText3,editText4,editText6;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);
        editText4 = (EditText) findViewById(R.id.editText4);
        editText6 = (EditText) findViewById(R.id.editText6);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button6).setOnClickListener(this);

    }

    private void registerUser(){
        String email = editText2.getText().toString().trim();
        String password = editText3.getText().toString().trim();
        String check = editText4.getText().toString().trim();
        String name = editText6.getText().toString().trim();

        if(email.isEmpty()){
            editText2.setError("Email is required");
            editText2.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){   // THIS METHOD CHECKS IF ITS A REAL EMAIL
            editText2.setError("Please enter a valid email");
            editText2.requestFocus();
            return;
        }
        if(password.isEmpty()){
            editText3.setError("Password is required");
            editText3.requestFocus();
            return;
        }
        if(password.length()< 6){
            editText3.setError("Minimum characters for a password is 6");
            editText3.requestFocus();
            return;
        }
        if(!password.equals(check)){
            editText4.setError("Passwords don't match");
            editText4.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"You've Successfully Registered", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Register.this, LoginActivity.class));
                }else{
                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                    Toast.makeText(getApplicationContext(), "You've already registered mate", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.button:
                registerUser();
                break;
            case R.id.button6:
//                startActivity(new Intent(this, LoginActivity.class));
                finish();
        }

    }
}
