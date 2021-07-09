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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PaymentsFragment extends Fragment {

    //a list to store all the products
    List<payments_list> payments;

    //the recyclerview
    RecyclerView mrecyclerView;



    private Spinner spi5;
    private String[] test5 = {"Sorting option","Newest First","Oldest First","Order ID(Ascending)"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payments,container,false);
        spi5 = (Spinner) view.findViewById(R.id.spinner5);
        ArrayAdapter<CharSequence> mSortAdapter = new ArrayAdapter<CharSequence>(getActivity(), android.R.layout.simple_spinner_item, test5);
        mSortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spi5.setAdapter(mSortAdapter);
        return view;
    }


    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        payments = new ArrayList<>();
    }

    private void loadCustomer() {

        /*
         * Creating a String Request
         * The request type is GET defined by first parameter
         * The URL is defined in the second parameter
         * Then we have a Response Listener and a Error Listener
         * In response listener we will get the JSON response as a String
         * */
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.URL_PAYMENTS,
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
                                payments.add(new payments_list(
                                        aalist.getString("order_date"),
                                        aalist.getString("order_number")  ,
                                        aalist.getString("total_amount"),
                                        aalist.getString("advance_pay"),
                                        aalist.getString("remaining_amount")
                                ));

                                mrecyclerView = (RecyclerView) mrecyclerView.findViewById(R.id.orderPaymentsRy);
                                LinearLayoutManager manager = new LinearLayoutManager(getContext());
                                mrecyclerView.setLayoutManager(manager);
                                mrecyclerView.setHasFixedSize(true);

                                //creating adapter object and setting it to recyclerview
                                paymentsAdapter adapter = new paymentsAdapter(getContext(),payments);
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
