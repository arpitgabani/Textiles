package com.example.textiles;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    private EditText firstNameET , lastNameET , email2ET , mobileET , dateOfBirthET , dateOfAnniversary , firstName2ET , gstNumberET , panNumberET , pincodeET , address1ET , address2ET , password2ET , confirmPasswordET ;
    private Button signUp2Btn , signIn2Btn;
    private ProgressDialog progressDialog;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);



        firstNameET = findViewById(R.id.firstNameET);
        lastNameET = findViewById(R.id.lastNameET);
        firstName2ET = findViewById(R.id.firstName2ET);
        email2ET = findViewById(R.id.email2ET);
        mobileET = findViewById(R.id.mobileET);
        dateOfBirthET = findViewById(R.id.dateOfBirthET);
        dateOfAnniversary = findViewById(R.id.dateOfAnniversary);
        gstNumberET = findViewById(R.id.gstNumberET);
        panNumberET = findViewById(R.id.panNumberET);
        address1ET = findViewById(R.id.address1ET);
        address2ET = findViewById(R.id.address2ET);
        password2ET = findViewById(R.id.password2ET);
        pincodeET = findViewById(R.id.pincodET);
        confirmPasswordET = findViewById(R.id.confirmPasswordET);
        signUp2Btn = findViewById(R.id.signUp2Btn);
        signIn2Btn = findViewById(R.id.signIn2Btn);

        progressDialog = new ProgressDialog(this);

//        dateOfBirthET.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Calendar calendar = Calendar.getInstance();
//                int year=calendar.get(Calendar.YEAR);
//                int month=calendar.get(Calendar.MONTH);
//                int day=calendar.get(Calendar.DAY_OF_MONTH);
//
//                    datePickerDialog=new DatePickerDialog(SignUpActivity.this , new DatePickerDialog.OnDateSetListener() {
//                        @Override
//                        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
//                            dateOfBirthET.setText(year+"/"+(month+1)+"/"+day);
//
//                        }
//                    },year,month,day);
//                    datePickerDialog.show();
//            }
//        });
//
//        dateOfAnniversary.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Calendar calendar = Calendar.getInstance();
//                int year=calendar.get(Calendar.YEAR);
//                int month=calendar.get(Calendar.MONTH);
//                int day=calendar.get(Calendar.DAY_OF_MONTH);
//
//                datePickerDialog=new DatePickerDialog(SignUpActivity.this , new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
//                        dateOfBirthET.setText(year+"/"+(month+1)+"/"+day);
//
//                    }
//                },year,month,day);
//                datePickerDialog.show();
//            }
//        });


        findViewById(R.id.signUp2Btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if user pressed on button register
                //here we will register the user to server
                registerUser();

            }
        });

//        signIn2Btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(SignUpActivity.this, HomeActivity.class));
//            }
//        });
    }

    private void registerUser(){


        final String firm_name = firstName2ET.getText().toString().trim();
        final String gst_number = gstNumberET.getText().toString().trim();
        final String first_name = firstNameET.getText().toString().trim();
        final String last_name = lastNameET.getText().toString().trim();
        final String phone_number = mobileET.getText().toString().trim();
        final String email = email2ET.getText().toString().trim();
        final String password = password2ET.getText().toString().trim();
        final String pan_card_number = panNumberET.getText().toString().trim();
        final String address = address1ET.getText().toString().trim();
        final String address2 = address2ET.getText().toString().trim();
        final String pincode = pincodeET.getText().toString().trim();
        final String birth_date = dateOfBirthET.getText().toString().trim();
        final String anniversary_date = dateOfAnniversary.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        //To check if fieds is empty or not
        if(TextUtils.isEmpty(email)){
            Toast.makeText(getApplicationContext(), "Pleas Enter Email",Toast.LENGTH_LONG).show();
        }

        //To check if fieds is empty or not
        if(TextUtils.isEmpty(password)){
            Toast.makeText(getApplicationContext(), "Pleas Enter password",Toast.LENGTH_LONG).show();
        }

        //To check password length is greater than 6
        if(password.length() < 8){
            Toast.makeText(getApplicationContext(), "Enter minimum 8 digit password",Toast.LENGTH_LONG).show();
        }

        progressDialog.setMessage("Registering user.....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_REGISTER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    startActivity(new  Intent(SignUpActivity.this,LoginActivity.class));

                    Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }

                }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("firm_name",""+firm_name);
                params.put("gst_number",""+gst_number);
                params.put("first_name",""+first_name);
                params.put("last_name",""+last_name);
                params.put("phone_number",""+phone_number);
                params.put("email",""+email);
                params.put("password",""+password);
                params.put("pan_card_number",""+pan_card_number);
                params.put("address",""+address+"\n"+address2);
                //params.put("address",""+address2ET);
                params.put("pincode",""+pincode);
                params.put("birth_date",""+birth_date);
                params.put("anniversary_date",""+anniversary_date);
                return params;
            }
        };

        com.example.textiles.RequestHandler.getInstance(this).addToRequestQueue(stringRequest);


    }
}