package com.example.h.checkplz;

public class PersonOrder {

    private String mOrderName;
    private double mCost;
    private int mOrderAmount;
    private int orderCounter=1;

    public PersonOrder(){
        mOrderName="Order"+orderCounter;
        mOrderAmount=0;
        mCost=0;
        orderCounter++;
    }

    public double getmCost() {
        return mCost;
    }

    public void setmCost(double mCost) {
        this.mCost = mCost;
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
