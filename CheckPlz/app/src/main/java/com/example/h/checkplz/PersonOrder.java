package com.example.h.checkplz;

public class PersonOrder {

    private String mOrderName;
    private double mOrderCost;
    private int mOrderAmount;
    private int orderCounter=1;

    public PersonOrder(){
        mOrderName="Order"+orderCounter;
        mOrderAmount=0;
        mOrderCost=0;
        orderCounter++;
    }

    public double getmOrderCost() {
        return mOrderCost;
    }

    public void setmOrderCost(double mOrderCost) {
        this.mOrderCost = mOrderCost;
    }

    public int getmOrderAmount() {
        return mOrderAmount;
    }

    public void setmOrderAmount(int mOrderAmount) {
        this.mOrderAmount = mOrderAmount;
    }

    public String getmOrderName() {
        return mOrderName;
    }

    public void setmOrderName(String mOrderName) {
        this.mOrderName = mOrderName;
    }
}
