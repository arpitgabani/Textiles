package com.example.textiles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class OrderDetailsFragment extends Fragment {

    private Spinner spi1,spi2;
    private String[] test1 = {"Order status","Delivered","Packed & Shipped","Processing"},test2= {"Sorting option","Newest First","Oldest First","Order ID(Ascending)"};


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_details,container,false);
        spi1 = (Spinner) view.findViewById(R.id.spinner1);
        spi2 = (Spinner) view.findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> mSortAdapter1 = new ArrayAdapter<CharSequence>(getActivity(), android.R.layout.simple_spinner_item, test1);
        ArrayAdapter<CharSequence> mSortAdapter2 = new ArrayAdapter<CharSequence>(getActivity(), android.R.layout.simple_spinner_item, test2);
        mSortAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSortAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spi1.setAdapter(mSortAdapter1);
        spi2.setAdapter(mSortAdapter2);
        return view;
    }
}
