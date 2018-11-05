package com.example.android.quakereport;

public class Earthquake {

    private double mMagnitude;
    private String mPlace;
    private long mTimeInMilliSeconds;
    private String mUrl;
    /**
     * Constructs a new {@link Earthquake} object.
     *
     * @param magnitude is the magnitude (size) of the earthquake
     * @param //location is the location where the earthquake happened
     * @param //timeInMilliseconds is the time in milliseconds (from the Epoch) when the
     *                           earthquake happened
     * @param url is the website URL to find more details about the earthquake
     */
    public Earthquake(double magnitude, String place, long timeInMilliSeconds, String url){
        mMagnitude = magnitude;
        mPlace = place;
        mTimeInMilliSeconds = timeInMilliSeconds;
        mUrl = url;
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
    /**
     * Returns the website URL to find more information about the earthquake.
     */
    public String getUrl(){
        return mUrl;
    }
}
