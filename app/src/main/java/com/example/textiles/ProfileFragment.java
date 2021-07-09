package com.example.textiles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    TextView namePTV , emailPTV , mobileNumberPTV , dateOfBirthPTV, dateOfAnniversaryPTV;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        namePTV = (TextView) namePTV.findViewById(R.id.namePTV);
        emailPTV = (TextView) emailPTV.findViewById(R.id.emailPTV);
        mobileNumberPTV = (TextView) mobileNumberPTV.findViewById(R.id.mobileNumberPTV);
        dateOfAnniversaryPTV = (TextView) dateOfAnniversaryPTV.findViewById(R.id.dateOfAnniversaryPTV);
        dateOfBirthPTV = (TextView) dateOfBirthPTV.findViewById(R.id.dateOfBirthPTV);


        return inflater.inflate(R.layout.fragment_profile, container,false);
    }
}
