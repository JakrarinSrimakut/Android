package com.example.h.checkplz;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PersonInputBillActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_input_bill);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("PersonInputBillActivity");
    }
}
