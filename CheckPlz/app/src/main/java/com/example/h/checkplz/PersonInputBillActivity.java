package com.example.h.checkplz;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

public class PersonInputBillActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_input_bill);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("PersonInputBillActivity");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.complete_person_input_bill_activity, menu);
        return true;
    }
}
