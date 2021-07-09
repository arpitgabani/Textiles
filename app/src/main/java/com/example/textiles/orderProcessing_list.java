package com.example.textiles;

public class orderProcessing_list {

    private  String  dateOrder , orderId , tGray , cGray , rGray;

    public  orderProcessing_list( String  dateOrder , String orderId , String tGray , String cGray , String rGray){

        this.dateOrder = dateOrder;
        this.orderId = orderId;
        this.tGray = tGray;
        this.cGray = cGray;
        this.rGray = rGray;

    }

    public String getDateOrder() {
        return dateOrder;
    }

    public String getOrderId() {
        return orderId;
    }

    public String gettGray() {
        return tGray;
    }

    public String getcGray() {
        return cGray;
    }

    public String getrGray() {
        return rGray;
    }


}
