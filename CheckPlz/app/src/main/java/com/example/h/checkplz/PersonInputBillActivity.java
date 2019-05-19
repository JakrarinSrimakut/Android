package com.example.h.checkplz;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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

public class PersonInputBillActivity extends AppCompatActivity {
    final String FROM_PERSON_ORDER_LIST_ADAPTER = "from-person-order-list-adapter";
    final String UPDATE_PERSON_ORDER_LIST = "update-person-order-list";
    private ArrayList<PersonOrder> personOrders = new ArrayList<>();
    private PersonBill mPersonBill;
    private EditText editName;
    private ArrayList<PersonOrder> mPersonOrderList;
    public TextView totalAmount;
    PersonOrderListAdapter adapter;
    int maxEnterNumber = 1000;//Max amount of orders (rows for RV)
    Double[] enteredNumberCost = new Double[maxEnterNumber];
    Integer[] enteredNumberMultipleAmount = new Integer[maxEnterNumber];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_input_bill);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("PersonInputBillActivity");

        editName=(EditText) findViewById(R.id.person_name_input_bill_activity);
        totalAmount=(TextView) findViewById(R.id.person_total_amount);

        //Create recyclerView and adapter to be reference
        // Lookup the recyclerview in activity layout
        RecyclerView rvPersonOrdersList = (RecyclerView) findViewById(R.id.rv_person_order_list);
        
        //setItems();

        //Initialize Orders
        PersonOrder pOrder = new PersonOrder();
        personOrders.add(pOrder);
        personOrders.add(new PersonOrder());
        //Create adapter passing the sample user data
        adapter = new PersonOrderListAdapter(personOrders);
        //Attach the adapter to the recyclerview to populate items
        rvPersonOrdersList.setAdapter(adapter);
        //Set Layout manager to position the items
        rvPersonOrdersList.setLayoutManager(new LinearLayoutManager(this));

        Button addOrderButton = (Button) findViewById(R.id.add_order_button);

        //Create new order
        addOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                personOrders.add(new PersonOrder());
                //update adapter personOrder with new person order
                adapter.notifyItemInserted(personOrders.size()-1);//Make the view appear dynamically in recyclerview by notifying adapter of insertion.
            }
        });

        //TODO: REGSISTER MULTIPLE BROADCAST RECEIVERS? OR 1 AND INTENTFILTER TO IT?


        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageListReceiver,
                new IntentFilter(FROM_PERSON_ORDER_LIST_ADAPTER));
        /*Init PersonBill to hold person name and person's orders in arraylist
        * only when menu check list is click. But how about editing? When you edit and click check
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
    //TODO: RECEIVER FOR EACH UPDATE FOR ORDER NAME,COST,AMOUNT?
    private BroadcastReceiver mMessageListReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            mPersonOrderList = intent.getParcelableArrayListExtra(UPDATE_PERSON_ORDER_LIST);

//            for( int i=0; i<mPersonOrderList.size(); i++) {
//                Log.d("personOrderList", "Row:" + i + " OrderName: " + mPersonOrderList.get(i).getmOrderName());
//            }

//            mPersonOrderList = intent.getParcelableArrayListExtra( "mPersonOrderList");
//
//            //enteredNumberCost[position]=0.0;
//            //enteredNumberMultipleAmount[position]=1;
//            updateTotalValue();
//

        }
    };

    private void setItems() {
        for(int i=0; i<maxEnterNumber; i++){
            enteredNumberCost[i]=0.00;
            enteredNumberMultipleAmount[i]=1;
        }
    }

    private void updateTotalValue() {
        double sum = 0;

        /*
        for(int i = 0; i<maxEnterNumber; i++){
            sum += enteredNumberCost[i] * enteredNumberMultipleAmount[i] ;
        }
        */

        Iterator<PersonOrder> iter=mPersonOrderList.iterator();
        while(iter.hasNext()){
            sum += iter.next().getmOrderCost() * iter.next().getmOrderAmount();
        }
        totalAmount.setText(String.valueOf(sum));
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
                /*Init PersonBill by setting person's name and fill array of person's order*/
                //mPersonBill = new PersonBill();
                //mPersonBill.setmName(editName.getText().toString());
                //PersonOrderListAdapter mAdapter = (PersonOrderListAdapter) RecyclerView.getAdapter().getList();

               // mPersonBill.setmPersonOrders((PersonOrderListAdapter) RecyclerView.getAdapter().getList());

                Intent intent = new Intent(this, PeopleBillListActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
