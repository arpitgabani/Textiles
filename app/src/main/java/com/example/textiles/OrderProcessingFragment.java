package com.example.textiles;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.RequestBody;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderProcessingFragment extends Fragment  {

    //a list to store all the products
    List<orderProcessing_list> orders;

    //the recyclerview
    RecyclerView mrecyclerView;



    private Spinner spi3;
    private String[] test3 = {"Sorting option","Newest First","Oldest First","Order ID(Ascending)"};


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_processing,container,false);
        spi3 = (Spinner) view.findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> mSortAdapter = new ArrayAdapter<CharSequence>(getActivity(), android.R.layout.simple_spinner_item, test3);
        mSortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spi3.setAdapter(mSortAdapter);
        return view;
    }

    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        orders = new ArrayList<>();
    }

    private void loadCustomer() {

        /*
         * Creating a String Request
         * The request type is GET defined by first parameter
         * The URL is defined in the second parameter
         * Then we have a Response Listener and a Error Listener
         * In response listener we will get the JSON response as a String
         * */
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.URL_ORDER,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject aalist = array.getJSONObject(i);

                                //adding the product to product list
                                orders.add(new orderProcessing_list(
                                        aalist.getString("order_date"),
                                        aalist.getString("order_number")  ,
                                        aalist.getString("total_lot"),
                                        aalist.getString("processed_lot"),
                                        aalist.getString("pending_lot")
                                ));

                                mrecyclerView = (RecyclerView) mrecyclerView.findViewById(R.id.orderProcessingRy);
                                LinearLayoutManager manager = new LinearLayoutManager(getContext());
                                mrecyclerView.setLayoutManager(manager);
                                mrecyclerView.setHasFixedSize(true);

                                //creating adapter object and setting it to recyclerview
                                orderAdapter adapter = new orderAdapter(getContext(), orders);
                                mrecyclerView.setAdapter(adapter);
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse( VolleyError error) {

                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(getContext()).add(stringRequest);
    }

    }




