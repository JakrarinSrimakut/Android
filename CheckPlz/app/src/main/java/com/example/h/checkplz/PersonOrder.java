package com.example.h.checkplz;

public class PersonOrder {

    private String mDishName;
    private double mCost;
    private int mDishAmount;
    private int dishCounter=1;

    public PersonOrder(){
        mDishName="Dish"+dishCounter;
        mDishAmount=0;
        mCost=0;
        dishCounter++;
    }

    public double getmCost() {
        return mCost;
    }

    public void setmCost(double mCost) {
        this.mCost = mCost;
    }

    public int getmDishAmount() {
        return mDishAmount;
    }

    public void setmDishAmount(int mDishAmount) {
        this.mDishAmount = mDishAmount;
    }

    public String getmDishName() {
        return mDishName;
    }

    public void setmDishName(String mDishName) {
        this.mDishName = mDishName;
    }
}
