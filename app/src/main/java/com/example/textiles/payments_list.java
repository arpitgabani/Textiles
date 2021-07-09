package com.example.textiles;

public class payments_list {

    private  String  dateOrder , orderId , tAmount , advancePay , rAmount;

    public  payments_list( String  dateOrder , String orderId , String tAmount , String advancePay , String rAmount){

        this.dateOrder = dateOrder;
        this.orderId = orderId;
        this.tAmount = tAmount;
        this.advancePay = advancePay;
        this.rAmount = rAmount;

    }

    public String getDateOrder() {
        return dateOrder;
    }

    public String getOrderId() {
        return orderId;
    }

    public String gettAmount() {
        return tAmount;
    }

    public String getadvancePay() {
        return advancePay;
    }

    public String getrAmount() {
        return rAmount;
    }
}
