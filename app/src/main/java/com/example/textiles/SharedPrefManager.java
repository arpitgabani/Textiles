package com.example.textiles;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Date;

public class SharedPrefManager {
    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private static final String SHARED_PREF_NAME = "mysharedpref12";
    public String KEY_EMAIL = "login-email";
//    private static final String KEY_USER_ID = "userid";
//    private static final String KEY_USER_FIRM_NAME = "userfirmname";
//    private static final String KEY_USER_GST_NUMBER = "usergstnumber";
//    private static final String KEY_USER_FIRST_NAME = "userfirstname";
//    private static final String KEY_USER_LAST_NAME = "userlastname";
//    private static final String KEY_USER_PHONE_NUMBER = "userphonenumber";
//    private static final String KEY_USER_EMAIL = "useremail";
//    private static final String KEY_USER_PAN_CARD_NUMBER = "userpancardnumber";
//    private static final String KEY_USER_PINCODE = "userpincode";
//    private static final String KEY_USER_BIRTH_DATE = "userbirthdate";
//    private static final String KEY_USER_ANNIVERSARY_DATE = "useranniversarydate";

    private SharedPrefManager(Context context){
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context){
        if (mInstance == null){
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public boolean userLogin(String email){

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_EMAIL, email);


        editor.apply();

        return true;
    }

    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        if (sharedPreferences.getString(KEY_EMAIL, null) != null){
            return true;
        }
        return false;
    }

    public boolean logout(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }
}
