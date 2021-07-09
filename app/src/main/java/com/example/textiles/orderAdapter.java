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

public class orderAdapter extends RecyclerView.Adapter<orderAdapter.OrderProcessingFragmentHolder> {

    private Context mCtx;
    private List<orderProcessing_list> orderList;

    public orderAdapter( Context mCtx, List<orderProcessing_list> orderList ) {
        this.mCtx = mCtx;
        this.orderList = orderList;

    }

    @NonNull
    @Override
    public OrderProcessingFragmentHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.cl_orderprocessing_adapter, null);
        return new OrderProcessingFragmentHolder(view);
    }

    @Override
    public void onBindViewHolder( @NonNull OrderProcessingFragmentHolder holder, int position ) {
        orderProcessing_list customer = orderList.get(position);
        holder.mdateOrder.setText(customer.getDateOrder());
        holder.morderId.setText(customer.getOrderId());
        holder.mtGray.setText(customer.gettGray());
        holder.mcGray.setText(customer.getcGray());
        holder.mrGray.setText(customer.getrGray());

    }

    @Override
    public int getItemCount() {return orderList.size();
    }

    class OrderProcessingFragmentHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mdateOrder, morderId , mtGray, mcGray, mrGray;

        public OrderProcessingFragmentHolder( View itemView ) {
            super(itemView);

            itemView.setOnClickListener(this::onClick);
            mdateOrder = itemView.findViewById(R.id.cl_dateOrder);
            morderId = itemView.findViewById(R.id.cl_orderId);
            mtGray = itemView.findViewById(R.id.cl_tGray);
            mcGray = itemView.findViewById(R.id.cl_cGray);
            mrGray = itemView.findViewById(R.id.cl_rGray);
        }

        @Override
        public void onClick( View v ) {

            int position = this.getBindingAdapterPosition();
            orderProcessing_list acclist = orderList.get(position);
            String dateOrder = acclist.getDateOrder();
            String orderId = acclist.getOrderId();
            String tGray = acclist.gettGray();
            String cGray = acclist.getcGray();
            String rGray = acclist.getrGray();


            Intent intent = new Intent(mCtx, Dialog.class);
            intent.putExtra("order_date", dateOrder);
            intent.putExtra("order_number", orderId);
            intent.putExtra("total_lot", tGray);
            intent.putExtra("processed_lot", cGray);
            intent.putExtra("pending_lot", rGray);
            mCtx.startActivity(intent);
        }

//        @Override
//        public orderAdapter.OrderProcessingFragmentHolder onCreateViewHolder( ViewGroup parent, int viewType ) {
//            LayoutInflater inflater = LayoutInflater.from(mCtx);
//            View view = inflater.inflate(R.layout.cl_orderprocessing_adapter, null);
//            return new OrderProcessingFragmentHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder( orderAdapter.OrderProcessingFragmentHolder holder, int position ) {
//            orderProcessing_list customer = orderList.get(position);
//            holder.mdateOrder.setText(customer.getDateOrder());
//            holder.morderId.setText(customer.getOrderId());
//            holder.mtGray.setText(customer.gettGray());
//            holder.mcGray.setText(customer.getcGray());
//            holder.mrGray.setText(customer.getrGray());
//
//        }
//
//        @Override
//        public int getItemCount() {
//            return orderList.size();
//        }


    }
}
