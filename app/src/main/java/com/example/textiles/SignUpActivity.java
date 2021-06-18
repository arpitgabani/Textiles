package com.example.textiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {

    EditText firstNameET , lastNameET , email2ET , mobileET , dateOfBirthET , dateOfAnniversary , firstName2ET , gstNumberET , panNumberET , address1ET , address2ET , password2ET , confirmPasswordET ;
    Button signUp2Btn , signIn2Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firstNameET = findViewById(R.id.firstNameET);
        lastNameET = findViewById(R.id.lastNameET);
        email2ET = findViewById(R.id.email2ET);
        mobileET = findViewById(R.id.mobileET);
        dateOfBirthET = findViewById(R.id.dateOfBirthET);
        dateOfAnniversary = findViewById(R.id.dateOfAnniversary);
        gstNumberET = findViewById(R.id.gstNumberET);
        panNumberET = findViewById(R.id.panNumberET);
        address1ET = findViewById(R.id.address1ET);
        address2ET = findViewById(R.id.address2ET);
        password2ET = findViewById(R.id.password2ET);
        confirmPasswordET = findViewById(R.id.confirmPasswordET);
        signUp2Btn = findViewById(R.id.signUp2Btn);
        signIn2Btn = findViewById(R.id.signIn2Btn);

        signIn2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, HomeActivity.class));
            }
        });
    }
}