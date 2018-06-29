/* This program demos the use of Location using a Service.
The main author is Dustin Adams, although some code has been used from
Luca de Alfaro and Stack Overflow.
Date: 4/18/2018
 */

package com.example.dustinadams.gpsexample4;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.location.Location;
import android.location.LocationManager;
import android.view.View;
import java.util.List;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements OnRequestPermissionsResultCallback{

    private final String TAG = "TESTGPS";
    public static TextView latText;
    public static TextView longText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // References for the widgets
        latText = findViewById(R.id.latitude);
        longText = findViewById(R.id.longitude);

        // This statement requests permission to the user.
        // If permissions are not set in the Manifest file, then access
        // will automatically be denied. Once the user chooses an option,
        // onRequestPermissionsResult is called.
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                99);

        Button b = findViewById(R.id.button);
        b.setOnClickListener(new Button.OnClickListener(){
            @SuppressLint("MissingPermission")
            public void onClick(View view){
                // A reference to the location manager. The LocationManager has already
                // been set up in MyService, we're just getting a reference here.
                LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                List<String> providers = lm.getProviders(true);
                Location l;
                // Go through the location providers starting with GPS, stop as soon
                // as we find one.
                for (int i=providers.size()-1; i>=0; i--) {
                    l = lm.getLastKnownLocation(providers.get(i));
                    longText.setText(Double.toString(l.getLongitude()));
                    latText.setText(Double.toString(l.getLatitude()));
                    if (l != null) break;
                }
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
