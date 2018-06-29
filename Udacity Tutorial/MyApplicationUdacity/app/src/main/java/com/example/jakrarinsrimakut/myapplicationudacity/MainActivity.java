package com.example.jakrarinsrimakut.myapplicationudacity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textView = new TextView(this);
        textView.setText("Wow!");
        textView.setTextColor(Color.RED);
        textView.setTextSize(56);
        textView.setAllCaps(true);

        setContentView(textView);
    }
}
