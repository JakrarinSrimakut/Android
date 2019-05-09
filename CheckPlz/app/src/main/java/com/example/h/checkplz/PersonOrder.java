package com.example.h.checkplz;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class PersonOrder implements Parcelable {

    private String mOrderName;
    private double mOrderCost;
    private int mOrderAmount;

    public PersonOrder(){
        mOrderName="";
        mOrderCost=0;
        mOrderAmount=0;
    }

    protected PersonOrder(Parcel in) {
        mOrderName = in.readString();
        mOrderCost = in.readDouble();
        mOrderAmount = in.readInt();
    }

    public static final Creator<PersonOrder> CREATOR = new Creator<PersonOrder>() {
        @Override
        public PersonOrder createFromParcel(Parcel in) {
            return new PersonOrder(in);
        }

        @Override
        public PersonOrder[] newArray(int size) {
            return new PersonOrder[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mOrderName);
        parcel.writeDouble(mOrderCost);
        parcel.writeInt(mOrderAmount);
    }
}
