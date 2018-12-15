package com.example.h.checkplz;

import java.util.LinkedHashMap;

public class PersonBill {
    private String mName;
    private int mTotalBill;
    private int mTax;
    private int mTip;
    private LinkedHashMap<String, Integer> mItemList;

    public PersonBill(){
        mTotalBill=0;
        mTax=0;
        mTip=0;
        for(int i=0; i<5; i++){
            mItemList.put("Item"+Integer.toString(i), 0);
        }
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
