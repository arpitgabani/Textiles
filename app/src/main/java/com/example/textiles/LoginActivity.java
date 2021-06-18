package com.example.textiles;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class LoginActivity extends AppCompatActivity {

     private Button signUpBtn , viewCatBtn , signInBtn ;
     private EditText emailET, passwordET ;
     private TextView  forgotpassTV , signInTV ;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signUpBtn=findViewById(R.id.signUpBtn);
        viewCatBtn=findViewById(R.id.viewCatBtn);
        signInBtn=findViewById(R.id.signInBtn);
        emailET=findViewById(R.id.emailET);
        passwordET=findViewById(R.id.passwordET);
        forgotpassTV=findViewById(R.id.forgotpassTV);
        signInTV=findViewById(R.id.signInTV);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });

        viewCatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RetailActivity.class));
            }
        });



    }
}