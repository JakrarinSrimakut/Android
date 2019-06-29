package com.example.h.checkplz;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class PersonBill implements Parcelable {
    private String mName;
    private static int personCount=1;
    private double mTotalBill;
    private double mSubTotalBill;
    private double mTax;
    private double mTip;
    private ArrayList<PersonOrder> mPersonOrders = new ArrayList<>();

    public PersonBill(){
        mName="Person"+personCount;
        mTotalBill=0;
        mSubTotalBill=0;
        mTax=0;
        mTip=0;
        personCount++;
    }

    protected PersonBill(Parcel in) {
        mName = in.readString();
        mTotalBill = in.readDouble();
        mSubTotalBill = in.readDouble();
        mTax = in.readDouble();
        mTip = in.readDouble();
        mPersonOrders = in.createTypedArrayList(PersonOrder.CREATOR);
    }

    public static final Creator<PersonBill> CREATOR = new Creator<PersonBill>() {
        @Override
        public PersonBill createFromParcel(Parcel in) {
            return new PersonBill(in);
        }

        @Override
        public PersonBill[] newArray(int size) {
            return new PersonBill[size];
        }
    };

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public double getmTotalBill() {
        return mTotalBill;
    }

    public void setmTotalBill(double mTotalBill) {
        this.mTotalBill = mTotalBill;
    }

    public double getmSubTotalBill() {
        return mSubTotalBill;
    }

    public void setmSubTotalBill(double mSubTotalBill) {
        this.mSubTotalBill = mSubTotalBill;
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

    public void setmTip(double mTip) {
        this.mTip = mTip;
    }

    public ArrayList<PersonOrder> getmPersonOrders() {
        return mPersonOrders;
    }

    public void setmPersonOrders(ArrayList<PersonOrder> mPersonOrders) {
        this.mPersonOrders = mPersonOrders;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mName);
        parcel.writeDouble(mTotalBill);
        parcel.writeDouble(mSubTotalBill);
        parcel.writeDouble(mTax);
        parcel.writeDouble(mTip);
        parcel.writeTypedList(mPersonOrders);
    }
}
