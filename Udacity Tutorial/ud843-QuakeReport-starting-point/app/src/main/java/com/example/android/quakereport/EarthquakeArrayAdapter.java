package com.example.android.quakereport;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.graphics.drawable.GradientDrawable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import android.support.v4.content.ContextCompat;

/*
 * {@link EarthquakeArrayAdapter} is an {@link ArrayAdapter} that can provide the layout for each list
 * based on a data source, which is a list of {@link Earthquake} objects.
 * */
public class EarthquakeArrayAdapter extends ArrayAdapter<Earthquake> {
    private static final String LOG_TAG = EarthquakeArrayAdapter.class.getSimpleName();

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *  @param context        The current context. Used to inflate the layout file.
     * @param earthQuake A List of AndroidFlavor objects to display in a list
     */

    public EarthquakeArrayAdapter(Context context, List<Earthquake> earthQuake){
        super(context,0, earthQuake);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
    // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        String locationOffSet = "";
        String location = "";
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent,false);
        }
        // Get the {@link Earthquake} object located at this position in the list
        Earthquake currentEarthquake = getItem(position);

        // Find the TextView in the list_item.xml layout with id magnitude
        TextView magnitudeTextView = (TextView)listItemView.findViewById(R.id.magnitude);

        // set the formatted magnitude text on the name TextView
        magnitudeTextView.setText(formatMagnitude(currentEarthquake.getMagnitude()));

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        //Split the string place into two substrings locationOffSet and location
        String place = currentEarthquake.getPlace();
        int placeLength = place.length();

        //Check if there's location off set
        if(place.contains("km")) {
            int indexToSplitString = place.indexOf("of")+2;
            locationOffSet = place.substring(0,indexToSplitString);
            location = place.substring(indexToSplitString, placeLength);
        }else{
            location = place;
        }


        // Find the TextView in the list_item.xml layout with id locationOffSet
        TextView locationOffSetTextView = (TextView)listItemView.findViewById(R.id.location_offset);
        // Get the locationOffSet from the current Earthquake object and
        // set this text on the name TextView
        locationOffSetTextView.setText(locationOffSet);

        // Find the TextView in the list_item.xml layout with id location
        TextView locationTextView = (TextView)listItemView.findViewById(R.id.location);
        // Get the location from the current Earthquake object and
        // set this text on the name TextView
        locationTextView.setText(location);

        // Create a new Date object from the time in milliseconds of the earthquake
        Date dateObject = new Date(currentEarthquake.getTimeInMilliSeconds());

        // Find the TextView in the list_item.xml layout with id date
        TextView dateTextView = (TextView)listItemView.findViewById(R.id.date);
        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = formatDate(dateObject);
        // Get the date from the current Earthquake object and
        // set this text on the name TextView
        dateTextView.setText(formattedDate);

        // Find the TextView in the list_item.xml layout with id time
        TextView timeTextView = (TextView)listItemView.findViewById(R.id.time);
        // Format the date string (i.e. "Mar 3, 1984")
        String formattedTime = formatTime(dateObject);
        // Get the date from the current Earthquake object and
        // set this text on the name TextView
        timeTextView.setText(formattedTime);

        // Return the whole list item layout (containing 3 TextViews)
        // so that it can be shown in the ListView
        return listItemView;
    }

    //return the background color of the magnitude given
    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId=0;//color resource IDs just point to the resource we defined, but not the value of the color.
        int magnitudeInt = (int)Math.floor(magnitude);

        switch (magnitudeInt){
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;

        }

        return ContextCompat.getColor(getContext(),magnitudeColorResourceId);

    }

    //return mag with applied format
    public String formatMagnitude(double mag){
        DecimalFormat formatter = new DecimalFormat("0.0");
        return formatter.format(mag);
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */

    public String formatDate(Date dateObject){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM DD, YYYY");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */

    public String formatTime(Date dateObject){
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

}
