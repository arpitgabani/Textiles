package com.example.textiles;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.sip.SipAudioCall;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends AppCompatActivity {

    Button signUpBtn, viewCatBtn, signInBtn;
    EditText emailET, passwordET;
    TextView forgotpassTV, signInTV;
    private ProgressDialog progressDialog;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signUpBtn = findViewById(R.id.signUpBtn);
        viewCatBtn = findViewById(R.id.viewCatBtn);
        signInBtn = findViewById(R.id.signInBtn);
        emailET = findViewById(R.id.emailET);
        passwordET = findViewById(R.id.passwordET);
        forgotpassTV = findViewById(R.id.forgotpassTV);
        signInTV = findViewById(R.id.signInTV);


        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });

        viewCatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, HomeActivity2.class));
            }
        });


        if (SharedPrefManager.getInstance(getApplicationContext()).isLoggedIn()){
            finish();
            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
            return;
        }

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("please wait...");

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLoginAdmin();
            }
        });

    }

    private void userLoginAdmin(){
        final String email = emailET.getText().toString().trim();
        final String password = passwordET.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        //To check if fieds is empty or not
        if(TextUtils.isEmpty(email)){
            Toast.makeText(getApplicationContext(), "Please Enter Email",Toast.LENGTH_LONG).show();
        }

        //To check user enter valid email or not
        if (!(email.matches(emailPattern)))
        {
            Toast.makeText(getApplicationContext(),"Invalid email address",Toast.LENGTH_SHORT).show();
        }

        //To check if fieds is empty or not
        if(TextUtils.isEmpty(password)){
            Toast.makeText(getApplicationContext(), "Please Enter password",Toast.LENGTH_LONG).show();
        }

        //To check password length is greater than 6
        if(password.length() < 8){
            Toast.makeText(getApplicationContext(), "Enter minimum 8 digit password",Toast.LENGTH_LONG).show();
        }

        progressDialog.show();
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if(!jsonObject.getBoolean("error")){
                        Toast.makeText(LoginActivity.this,"Clsoo Textile is working Succesfully",Toast.LENGTH_LONG).show();
                        SharedPrefManager.getInstance(LoginActivity.this).userLogin(email);
                        Toast.makeText(LoginActivity.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                        startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                        finish();

                    }else{
                        Toast.makeText(LoginActivity.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                    }
                    Toast.makeText(LoginActivity.this,"Clsoo Textile is working Succesfully",Toast.LENGTH_LONG).show();



                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("email",email);
                params.put("password",password);
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

}