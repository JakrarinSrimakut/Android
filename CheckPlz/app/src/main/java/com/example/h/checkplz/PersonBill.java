package com.example.h.checkplz;

import java.util.LinkedHashMap;

public class PersonBill {
    private String mName;
    private static int personCount=1;
    private int mTotalBill;
    private int mTax;
    private int mTip;
    private LinkedHashMap<String, Integer> mItemList = new LinkedHashMap<>();

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

    public int getmTotalBill() {
        return mTotalBill;
    }

    public void setmTotalBill(int mTotalBill) {
        this.mTotalBill = mTotalBill;
    }

    public int getmTax() {
        return mTax;
    }

    public void setmTax(int mTax) {
        this.mTax = mTax;
    }

    public int getmTip() {
        return mTip;
    }

    public void setmTip(int mTip) {
        this.mTip = mTip;
    }

    public LinkedHashMap<String, Integer> getmItemList() {
        return mItemList;
    }

    public void setmItemList(LinkedHashMap<String, Integer> mItemList) {
        this.mItemList = mItemList;
    }
}
