package com.example.jakrarinsrimakut.classtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "buttonClick";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onMyButtonClick(View v){
        TextView t = findViewById(R.id.our_label);
        t.setText("Hello World");
        Log.d(TAG,"clicked");
    }

}
