package com.example.h.eventlogging;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.widget.EditText;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.view.View;
import android.content.Intent;
import android.widget.TextView;

public class AddText extends AppCompatActivity {
    private final String TAG = "TESTGPS";
    public static TextView latText;
    public static TextView longText;

    public JSONObject jo = null;
    public JSONArray ja = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Start up the Location Service

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_text);

        final EditText title = findViewById(R.id.editText);
        final EditText event = findViewById(R.id.editText2);
        Button b = findViewById(R.id.button);

        // Read the file


        try{
            File f = new File(getFilesDir(), "file.ser");
            FileInputStream fi = new FileInputStream(f);
            ObjectInputStream o = new ObjectInputStream(fi);
            // Notice here that we are de-serializing a String object (instead of
            // a JSONObject object) and passing the String to the JSONObject’s
            // constructor. That’s because String is serializable and
            // JSONObject is not. To convert a JSONObject back to a String, simply
            // call the JSONObject’s toString method.
            String j = null;
            try{
                j = (String) o.readObject();
            }
            catch(ClassNotFoundException c){
                c.printStackTrace();
            }
            try {
                jo = new JSONObject(j);
                ja = jo.getJSONArray("data");
            }
            catch(JSONException e){
                e.printStackTrace();
            }
        }
        catch(IOException e){
            // Here, initialize a new JSONObject
            jo = new JSONObject();
            ja = new JSONArray();
            try{
                jo.put("data", ja);
            }
            catch(JSONException j){
                j.printStackTrace();
            }
        }

        // This statement requests permission to the user.
        // If permissions are not set in the Manifest file, then access
        // will automatically be denied. Once the user chooses an option,
        // onRequestPermissionsResult is called.
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                99);

        b.setOnClickListener(new Button.OnClickListener(){
            @SuppressLint("MissingPermission")
            public void onClick(View v){
                // A reference to the location manager. The LocationManager has already
                // been set up in MyService, we're just getting a reference here.
                LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                List<String> providers = lm.getProviders(true);
                Location l;
                String longAndLatText = "t";
                // Go through the location providers starting with GPS, stop as soon
                // as we find one.
                for (int i=providers.size()-1; i>=0; i--) {
                    l = lm.getLastKnownLocation(providers.get(i));
                    longAndLatText = l.getLongitude() + "," + l.getLatitude();
                    if (l != null) break;
                }
                String titleText = title.getText().toString();
                String eventText = event.getText().toString();
                Date getCal = Calendar.getInstance().getTime();
                DateFormat dfTime = new SimpleDateFormat("hh:mm:ss a");
                DateFormat dfDate = new SimpleDateFormat("MM-dd-yyyy");
                String currentTimeText = dfTime.format(getCal);
                String currentDateText = dfDate.format(getCal);
                JSONObject temp = new JSONObject();
                try {
                    temp.put("title", titleText);
                    temp.put("time", currentTimeText);
                    temp.put("date", currentDateText);
                    temp.put("gps", longAndLatText);
                    temp.put("event", eventText);

                }
                catch(JSONException j){
                    j.printStackTrace();
                }

                ja.put(temp);

                // write the file
                try{
                    File f = new File(getFilesDir(), "file.ser");
                    FileOutputStream fo = new FileOutputStream(f);
                    ObjectOutputStream o = new ObjectOutputStream(fo);
                    String j = jo.toString();
                    o.writeObject(j);
                    o.close();
                    fo.close();
                }
                catch(IOException e){

                }

                //pop the activity off the stack
                Intent i = new Intent(AddText.this, EventListActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

    }
    // This class implements OnRequestPermissionsResultCallback, so when the
    // user is prompted for location permission, the below method is called
    // as soon as the user chooses an option.
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        Log.d(TAG, "callback");
        switch (requestCode) {
            case 99:
                // If the permissions aren't set, then return. Otherwise, proceed.
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                                        Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.INTERNET}
                                , 10);
                    }
                    Log.d(TAG, "returning program");
                    return;
                }
                else{
                    // Create Intent to reference MyService, start the Service.
                    Log.d(TAG, "starting service");
                    Intent i = new Intent(this, MyService.class);
                    if(i==null)
                        Log.d(TAG, "intent null");
                    else{
                        startService(i);
                    }

                }
                break;
            default:
                break;
        }
    }
    // Used for debugging. Below method is extraneous
    @SuppressLint("MissingPermission")
    public void doSomething(View view){
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        List<String> providers = lm.getProviders(true);
        Location l;
        for (int i=providers.size()-1; i>=0; i--) {
            l = lm.getLastKnownLocation(providers.get(i));
            longText.setText(Double.toString(l.getLongitude()));
            latText.setText(Double.toString(l.getLatitude()));
            if (l != null) break;
        }
    }

}
