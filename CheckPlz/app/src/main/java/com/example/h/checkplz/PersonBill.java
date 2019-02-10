package com.example.h.checkplz;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class PersonBill {
    private String mName;
    private static int personCount=1;
    private double mTotalBill;
    private double mTax;
    private double mTip;
    private LinkedHashMap<String, Integer> mItemList = new LinkedHashMap<>();
    private ArrayList<PersonOrder> mPersonOrders = new ArrayList<>();

    public PersonBill(){
        mName="Person"+personCount;
        mTotalBill=0;
        mTax=0;
        mTip=0;
        personCount++;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public double getmTotalBill() {
        return mTotalBill;
    }

    public void setmTotalBill(int mTotalBill) {
        this.mTotalBill = mTotalBill;
    }

    public double getmTax() {
        return mTax;
    }

    public void setmTax(int mTax) {
        this.mTax = mTax;
    }

    public double getmTip() {
        return mTip;
    }

    public void setmTip(int mTip) {
        this.mTip = mTip;
    }

    //???????????
    public LinkedHashMap<String, Integer> getmItemList() {
        return mItemList;
    }

    public void setmItemList(LinkedHashMap<String, Integer> mItemList) {
        this.mItemList = mItemList;
    }
}
