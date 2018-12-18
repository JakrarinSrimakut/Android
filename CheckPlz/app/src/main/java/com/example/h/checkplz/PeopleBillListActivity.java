package com.example.h.checkplz;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

public class PeopleBillListActivity extends AppCompatActivity {

    ArrayList<PersonBill> peopleBills = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_bill_list);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("PeopleBillListActivity");

        // Lookup the recyclerview in activity layout
        RecyclerView rvPeopleBillList = (RecyclerView) findViewById(R.id.rv_people_bill_list);

        //Initialize bills
        PersonBill pBill = new PersonBill();
        peopleBills.add(pBill);
        peopleBills.add(new PersonBill());
        //Create adapter passing the sample user data
        PeopleBillListAdapter adapter = new PeopleBillListAdapter(peopleBills);
        //Attach the adapter to the recyclerview to populate items
        rvPeopleBillList.setAdapter(adapter);
        //Set Layout manager to position the items
        rvPeopleBillList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bill,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:
                Intent intent = new Intent(this, PersonInputBillActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
