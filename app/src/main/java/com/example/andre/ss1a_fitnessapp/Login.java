package com.example.andre.ss1a_fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private TextView result;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login_btn = (Button) findViewById(R.id.login_btn);
        Button forgot_password_btn = (Button) findViewById(R.id.forgot_password_btn);
//===============================================================================================
// DEMO
// Attempt to launch the register activity within the app
        Button registerBtn = (Button) findViewById(R.id.registerBtn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(Login.this, Register.class);
                startActivity(startIntent);
            }
        });
// ===============================================================================================

// Testing login
        email = (EditText) findViewById(R.id.emailEditText);
        password = (EditText) findViewById(R.id.passwordEditText);
        result = (TextView) findViewById(R.id.resultTv);
        login = (Button) findViewById(R.id.login_btn);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(email.getText().toString(), password.getText().toString());
            }
        });

    }

    private void validate(String userEmail, String userPassword) {
        if((userEmail.equals("")) && (userPassword.equals(""))) {
            Intent startIntent = new Intent(Login.this, Homepage.class);
            startActivity(startIntent);
        }
        else {
            result.setText("Email and/or password is invalid");
        }
    }
}
