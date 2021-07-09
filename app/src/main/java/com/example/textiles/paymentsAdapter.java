package com.example.textiles;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class paymentsAdapter extends RecyclerView.Adapter<paymentsAdapter.PaymentsFragmentHolder> {

    private Context mCtx;
    private List<payments_list> paymentList;

    public paymentsAdapter( Context mCtx, List<payments_list> paymentList ) {
        this.mCtx = mCtx;
        this.paymentList = paymentList;
    }

    @NonNull
    @Override
    public paymentsAdapter.PaymentsFragmentHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.cl_payments, null);
        return new PaymentsFragmentHolder(view);
    }

    @Override
    public void onBindViewHolder( @NonNull PaymentsFragmentHolder holder, int position ) {
        payments_list customer = paymentList.get(position);
        holder.mdateOrder.setText(customer.getDateOrder());
        holder.morderId.setText(customer.getOrderId());
        holder.mtAmount.setText(customer.gettAmount());
        holder.madvancePay.setText(customer.getadvancePay());
        holder.mrAmount.setText(customer.getrAmount());
    }



    @Override
    public int getItemCount() {
        return paymentList.size();
    }

    class PaymentsFragmentHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mdateOrder, morderId, mtAmount, madvancePay, mrAmount;

        public PaymentsFragmentHolder( View itemView ) {
            super(itemView);

            itemView.setOnClickListener(this::onClick);
            mdateOrder = itemView.findViewById(R.id.cl_dateOrder);
            morderId = itemView.findViewById(R.id.cl_orderId);
            mtAmount = itemView.findViewById(R.id.cl_tAmount);
            madvancePay = itemView.findViewById(R.id.cl_advancePay);
            mrAmount = itemView.findViewById(R.id.cl_rAmount);
        }

        @Override
        public void onClick( View v ) {

            int position = this.getBindingAdapterPosition();
            payments_list acclist = paymentList.get(position);
            String dateOrder = acclist.getDateOrder();
            String orderId = acclist.getOrderId();
            String tAmount = acclist.gettAmount();
            String advancePay = acclist.getadvancePay();
            String rAmount = acclist.getrAmount();


            Intent intent = new Intent(mCtx, Dialog.class);
            intent.putExtra("order_date", dateOrder);
            intent.putExtra("order_number", orderId);
            intent.putExtra("total_amount", tAmount);
            intent.putExtra("advance_pay", advancePay);
            intent.putExtra("reamaining_amount", rAmount);
            mCtx.startActivity(intent);
        }
    }

}