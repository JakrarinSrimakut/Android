package com.example.h.checkplz;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Parcelable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

import Utilities.Calculation;

public class PersonInputBillActivity extends AppCompatActivity {
    final String FROM_PERSON_ORDER_LIST_ADAPTER = "from-person-order-list-adapter";
    final String UPDATE_PERSON_ORDER_LIST = "update-person-order-list";
    final String PERSON_ORDER_BILL = "person-order-bill";
    final String PERSON_ORDER_BILL_EDIT = "person-order-bill-edit";
    final String PERSON_ORDER_BILL_POSITION = "person-order-bill-position";

    //private ArrayList<PersonOrder> personOrders = new ArrayList<>();
    private PersonBill mPersonBill;
    private ArrayList<PersonOrder> mPersonOrderList;//for adapter
    private int mPersonPosition=-1;

    private EditText editTextName;
    private EditText editTextTip;
    private TextView textViewTotalAmount;
    private TextView textView10PercentTip;
    private TextView textView15PercentTip;
    private TextView textView20PercentTip;

    PersonOrderListAdapter adapter;


    //TODO: Incorporate PersonBill to store total, tip, tax
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_input_bill);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("PersonInputBillActivity");

        mPersonOrderList = new ArrayList<PersonOrder>();
        //Edit mode
        Bundle extra = getIntent().getExtras();

        editTextName=(EditText) findViewById(R.id.person_name_input_bill_activity);
        editTextTip=(EditText) findViewById(R.id.tip_edit_text_input);
        textViewTotalAmount=(TextView) findViewById(R.id.person_total_amount);
        textView10PercentTip = (TextView) findViewById(R.id.tip_text_view_10_percent);
        textView15PercentTip = (TextView) findViewById(R.id.tip_text_view_15_percent);
        textView20PercentTip = (TextView) findViewById(R.id.tip_text_view_20_percent);

        //If editing populate name and person's orders else create new person bill
        if(extra != null){
            mPersonBill = extra.getParcelable(PERSON_ORDER_BILL_EDIT);
            mPersonPosition= extra.getInt(PERSON_ORDER_BILL_POSITION);
            mPersonOrderList = mPersonBill.getmPersonOrders();
            editTextName.setText(mPersonBill.getmName());
            editTextTip.setText(String.valueOf(mPersonBill.getmTip()));
        }
        else{
            mPersonBill = new PersonBill();
        }

        //Create recyclerView and adapter to be reference
        // Lookup the recyclerview in activity layout
        RecyclerView rvPersonOrdersList = (RecyclerView) findViewById(R.id.rv_person_order_list);
        
        //Create adapter passing the sample user data
        adapter = new PersonOrderListAdapter(mPersonOrderList);

        //Attach the adapter to the recyclerview to populate items
        rvPersonOrdersList.setAdapter(adapter);

        //Set Layout manager to position the items
        rvPersonOrdersList.setLayoutManager(new LinearLayoutManager(this));

        Button addOrderButton = (Button) findViewById(R.id.add_order_button);

        //Create new order
        addOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPersonOrderList.add(new PersonOrder());
                //update adapter personOrder with new person order
                adapter.notifyItemInserted(mPersonOrderList.size()-1);//Make the view appear dynamically in recyclerview by notifying adapter of insertion.
            }
        });


        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageListReceiver,
                new IntentFilter(FROM_PERSON_ORDER_LIST_ADAPTER));



        //Note: To grab something. recyclerView.getAdapter().getList().get(position)

    }

    private BroadcastReceiver mMessageListReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            mPersonOrderList = intent.getParcelableArrayListExtra(UPDATE_PERSON_ORDER_LIST);
            updateTotal();
            updateTips();

        }
    };

    private void updateTips() {
        double tenPercentTip = Calculation.calculate10PercentTip(mPersonBill.getmTotalBill());
        double fifteenPercentTip = Calculation.calculate15PercentTip(mPersonBill.getmTotalBill());
        double twentyPercentTip = Calculation.calculate20PercentTip(mPersonBill.getmTotalBill());
        textView10PercentTip.setText(String.format("%.2f", tenPercentTip));
        textView15PercentTip.setText(String.format("%.2f", fifteenPercentTip));
        textView20PercentTip.setText(String.format("%.2f", twentyPercentTip));

    }


    //Display the correct total value of bill
    private void updateTotal() {
        //
        double total = Calculation.calculateTotal(mPersonOrderList);
        mPersonBill.setmTotalBill(total);
        textViewTotalAmount.setText(String.format("%.2f", total));
    }


    @Override
    protected void onResume() {
        super.onResume();

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
                String tip = editTextTip.getText().toString();
                /*Set person's name and fill array of person's order*/
                mPersonBill.setmName(editTextName.getText().toString());
                if(!tip.isEmpty()){
                    mPersonBill.setmTip(Double.valueOf(tip));
                }
                mPersonBill.setmPersonOrders(mPersonOrderList);

                Intent intent = new Intent(this, PeopleBillListActivity.class);
                intent.putExtra(PERSON_ORDER_BILL, (Parcelable) mPersonBill);
                intent.putExtra(PERSON_ORDER_BILL_POSITION, mPersonPosition);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
