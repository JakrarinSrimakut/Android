package com.example.h.checkplz;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PersonOrderListAdapter extends
        RecyclerView.Adapter<PersonOrderListAdapter.ViewHolder>{

    // Store a member variable for the contacts
    private ArrayList<PersonOrder> mPersonOrderList;
    public static int viewID; //Use to keep tab of EditText being changed so PersonInpuTBillActivity can set the correct value to it's respective array.
    final String PERSON_ORDER_LIST = "person-order-list";
    final String ORDER = "order";
//    final String DELETE_ORDER = "delete-order";
//    final String UPDATE_ORDER_NAME = "update-order-name";
//    final String UPDATE_ORDER_COST = "update-order-cost";
//    final String UPDATE_ORDER_AMOUNT = "update-order-amount";
    final String FROM_PERSON_ORDER_LIST_ADAPTER = "from-person-order-list-adapter";
    final String UPDATE_PERSON_ORDER_LIST = "update-person-order-list";

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder{
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public EditText personOrderNameEditText;
        public EditText personOrderCostEditText;
        public EditText personOrderMultipleAmountEditText;
        public ImageView personOrderDelete;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(final View itemView){
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            personOrderNameEditText = (EditText) itemView.findViewById(R.id.person_order_name);
            personOrderCostEditText = (EditText) itemView.findViewById(R.id.person_order_cost);
            personOrderMultipleAmountEditText = (EditText) itemView.findViewById(R.id.person_order_multiple_amount);
            personOrderDelete = (ImageView) itemView.findViewById(R.id.person_order_delete);

            //Delete order
            personOrderDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int currentPosition = getAdapterPosition();//gets updated position

                    try{
                        //delete item and update person order list
                        removeItem(currentPosition);
                    }catch(ArrayIndexOutOfBoundsException e){
                        e.printStackTrace();
                    }
                    sendListToActivity(itemView);
                }
            });
            personOrderNameEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    PersonOrder currentPersonOrder = mPersonOrderList.get(getAdapterPosition());//pull existed person order to ensure any value of other edittext remain as is
                    currentPersonOrder.setmOrderName(s.toString());
                    mPersonOrderList.set(getAdapterPosition(), currentPersonOrder);

                }

                @Override
                public void afterTextChanged(Editable s) {
                    Log.d("OrderName", String.valueOf(mPersonOrderList.get(getAdapterPosition()).getmOrderName()));
                    sendListToActivity(itemView);
                }
            });
            personOrderCostEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    PersonOrder currentPersonOrder = mPersonOrderList.get(getAdapterPosition());
                    if(s.toString().isEmpty()){//prevent crash due to "" can't be converted to double
                        currentPersonOrder.setmOrderCost(0.00);
                    }else{
                        if(s.toString().equals(".")){
                            s = "0.";
                        }
                        currentPersonOrder.setmOrderCost(Double.parseDouble(String.format("%.2f", Double.parseDouble(s.toString()))));
                    }
                    mPersonOrderList.set(getAdapterPosition(), currentPersonOrder);
                }

                @Override
                public void afterTextChanged(Editable s) {
                    sendListToActivity(itemView);
                }
            });
            personOrderMultipleAmountEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    PersonOrder currentPersonOrder = mPersonOrderList.get(getAdapterPosition());
                    if(s.toString().isEmpty()){
                        currentPersonOrder.setmOrderAmount(1);
                    }else{
                        currentPersonOrder.setmOrderAmount(Integer.parseInt(s.toString()));
                    }
                    mPersonOrderList.set(getAdapterPosition(), currentPersonOrder);
                }

                @Override
                public void afterTextChanged(Editable s) {
                    sendListToActivity(itemView);
                }
            });
        }
    }


    // Pass in the contact array into the constructor
    public PersonOrderListAdapter(ArrayList<PersonOrder> personOrderList){
        mPersonOrderList=personOrderList;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public PersonOrderListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_person_order, parent, false);

        // Return a new holder instance
        PersonOrderListAdapter.ViewHolder viewHolder = new PersonOrderListAdapter.ViewHolder(contactView);

        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(PersonOrderListAdapter.ViewHolder viewHolder, final int position) {
        int x = viewHolder.getLayoutPosition();

        //Display the correct values for edit text when rows are deleted
        if(mPersonOrderList.get(x).getmOrderName().length() > 0) {
            viewHolder.personOrderNameEditText.setText(mPersonOrderList.get(x).getmOrderName());
        }
        else{
            viewHolder.personOrderNameEditText.setText(null);
            //viewHolder.personOrderNameEditText.setHint(hint);
            viewHolder.personOrderNameEditText.requestFocus();
        }
        if(mPersonOrderList.get(x).getmOrderCost() > 0) {
            viewHolder.personOrderCostEditText.setText(String.valueOf(mPersonOrderList.get(x).getmOrderCost()));
        }
        else{
            viewHolder.personOrderCostEditText.setText(null);
            //viewHolder.personOrderNameEditText.setHint(hint);
            viewHolder.personOrderCostEditText.requestFocus();
        }
        if(mPersonOrderList.get(x).getmOrderAmount() > 0) {
            viewHolder.personOrderMultipleAmountEditText.setText(String.valueOf(mPersonOrderList.get(x).getmOrderAmount()));
        }
        else{
            viewHolder.personOrderMultipleAmountEditText.setText(null);
            //viewHolder.personOrderNameEditText.setHint(hint);
            viewHolder.personOrderMultipleAmountEditText.requestFocus();
        }

    }

    //Send personOrderList to PersonInputBillActivity
    private void sendListToActivity(View itemView){
        Intent intent = new Intent(FROM_PERSON_ORDER_LIST_ADAPTER);
        intent.putExtra(UPDATE_PERSON_ORDER_LIST, mPersonOrderList);
        LocalBroadcastManager.getInstance(itemView.getContext()).sendBroadcast(intent);
    }


    //remove row from list and display it accordingly
    private void removeItem(int currentPosition) {
        mPersonOrderList.remove(currentPosition);
        notifyItemRemoved(currentPosition);
        notifyItemRangeChanged(currentPosition, getItemCount());
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mPersonOrderList!=null ? mPersonOrderList.size():0;
    }

    // Return PersonOrder list
    public ArrayList<PersonOrder> getList() {
        return this.mPersonOrderList;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
