package com.example.android.quakereport;

public class Earthquake {

    private double mMagnitude;
    private String mPlace;
    private long mTimeInMilliSeconds;

    public Earthquake(double magnitude, String place, long timeInMilliSeconds){
        mMagnitude = magnitude;
        mPlace = place;
        mTimeInMilliSeconds = timeInMilliSeconds;
    }

    public double getMagnitude(){
        return mMagnitude;
    }

    public String getPlace(){
        return mPlace;
    }

    public long getTimeInMilliSeconds(){
        return mTimeInMilliSeconds;
    }
}
