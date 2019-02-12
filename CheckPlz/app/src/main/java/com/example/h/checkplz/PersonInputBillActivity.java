package com.example.h.checkplz;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

public class PersonInputBillActivity extends AppCompatActivity {
    private PersonBill mPersonBill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_input_bill);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("PersonInputBillActivity");

        /*Init PersonBill to hold person name and person's orders in arraylist
        * only when menu check list is click so as to not have to create then delete
        * user click back button. But how about editing? When you edit and click check
        * mark it will generate a new instance. Answer: make a private PersonBill member
        * variable to set every different PersonBill
        *
        * How to populate PersonBill when it's sent here by intent? Send the PersonBill
        * here then run methods to fill the activity. Call PersonOrderListAdapter constructor
        * and send PersonOrderList
        *
        * To grab something. recyclerView.getAdapter().getList().get(position)
         *
         * Pass back PersonBill individually or as a List of Perosn Bill to
         * PeopleBillListActivity? Individual is more efficient. No need to update
         * entire list when it wasn't edited. But an PersonOrderListAdapter has
         * a constructor for taking a list of PersonOrder.
         *
         */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.complete_person_input_bill_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            /*Send completed personBill to PeopleBillListActivity*/
            case R.id.action_done:
                /*Init PersonBill by setting person's name and fill array of person's order*/
                mPersonBill = new PersonBill();
                mPersonBill.setmName();
                PersonOrderListAdapter final mAdapter = (PersonOrderListAdapter) RecyclerView.getAdapter().getName();

                Intent intent = new Intent(this, PeopleBillListActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
