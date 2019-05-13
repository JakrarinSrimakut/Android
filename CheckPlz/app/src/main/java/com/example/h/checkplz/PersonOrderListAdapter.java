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
    private OnEditTextChanged onEditTextChanged;
    public static int viewID; //Use to keep tab of EditText being changed so PersonInpuTBillActivity can set the correct value to it's respective array.
    final String PERSON_ORDER_LIST = "person-order-list";
    final String ORDER = "order";
//    final String DELETE_ORDER = "delete-order";
//    final String UPDATE_ORDER_NAME = "update-order-name";
//    final String UPDATE_ORDER_COST = "update-order-cost";
//    final String UPDATE_ORDER_AMOUNT = "update-order-amount";

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
        public ViewHolder(View itemView){
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
                        currentPersonOrder.setmOrderAmount(0);
                    }else{
                        currentPersonOrder.setmOrderCost(Double.parseDouble(s.toString()));
                    }
                    mPersonOrderList.set(getAdapterPosition(), currentPersonOrder);
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });
            personOrderMultipleAmountEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d("characterS", s.toString());
                    PersonOrder currentPersonOrder = mPersonOrderList.get(getAdapterPosition());
                    if(s.toString().isEmpty()){
                        currentPersonOrder.setmOrderAmount(0);
                    }else{
                        currentPersonOrder.setmOrderAmount(Integer.parseInt(s.toString()));
                    }
                    mPersonOrderList.set(getAdapterPosition(), currentPersonOrder);
                }

                @Override
                public void afterTextChanged(Editable s) {
                    Log.d("OrderAmount", String.valueOf(mPersonOrderList.get(getAdapterPosition()).getmOrderAmount()));
                }
            });
        }
    }


    // Pass in the contact array into the constructor
    public PersonOrderListAdapter(ArrayList<PersonOrder> personOrderList, OnEditTextChanged onEditTextChanged){
        mPersonOrderList=personOrderList;
        this.onEditTextChanged = onEditTextChanged;
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

        Log.d("onBind", mPersonOrderList.get(x).getmOrderName());

        if(mPersonOrderList.get(x).getmOrderName().length() > 0) {
            viewHolder.personOrderNameEditText.setText(mPersonOrderList.get(x).getmOrderName());
        }
        //TODO:If condition for getOrderCost
        if(mPersonOrderList.get(x).getmOrderCost() > 0) {
            viewHolder.personOrderCostEditText.setText(String.valueOf(mPersonOrderList.get(x).getmOrderCost()));
        }
        //TODO:If condtion for getorderAmount
        if(mPersonOrderList.get(x).getmOrderAmount() > 0) {
            viewHolder.personOrderMultipleAmountEditText.setText(String.valueOf(mPersonOrderList.get(x).getmOrderAmount()));
        }
        else{
            viewHolder.personOrderNameEditText.setText(null);
            //viewHolder.personOrderNameEditText.setHint(hint);
            viewHolder.personOrderNameEditText.requestFocus();
            viewHolder.personOrderCostEditText.setText(null);
            //viewHolder.personOrderNameEditText.setHint(hint);
            viewHolder.personOrderCostEditText.requestFocus();
            viewHolder.personOrderMultipleAmountEditText.setText(null);
            //viewHolder.personOrderNameEditText.setHint(hint);
            viewHolder.personOrderMultipleAmountEditText.requestFocus();
        }
        /*ImageView imageViewDeleteOrder = viewHolder.personOrderDelete;

        // Set item views based on your views and data model
        final EditText editTextOrderName = viewHolder.personOrderNameEditText;
        final EditText editTextOrderCost = viewHolder.personOrderCostEditText;
        final EditText editTextOrderMultipleAmount = viewHolder.personOrderMultipleAmountEditText;

        final Intent intent = new Intent(ORDER);

//        final Intent intentDelete = new Intent(DELETE_ORDER);
//        final Intent intentName = new Intent(UPDATE_ORDER_NAME);
//        final Intent intentOrderCost = new Intent(UPDATE_ORDER_COST);
//        final Intent intentOrderAmount = new Intent(UPDATE_ORDER_AMOUNT);

        //TODO: Change listener for PersonOrderList name

        //Set text changelistenor for personOrderCostEditText
        viewHolder.personOrderNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //viewID = editTextOrderCost.getId();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //onEditTextChanged.onTextChanged(position, charSequence.toString());
                //Toast.makeText(viewHolder.personOrderNameEditText.getContext(), "Ordername editing onTextChanged", Toast.LENGTH_SHORT ).show();

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //set text to person order and update persorOrderlist
//                personOrder.setmOrderName(onEditTextChanged.toString());
//                mPersonOrderList.set(position, personOrder);
//                //TODO:send intent to person Input Bill Activity
//                //TODO:create method for intent being used so all listeners can call it
//                PersonOrder personOrder = new PersonOrder();
//                personOrder.setmOrderName(editable.toString());
//                mPersonOrderList.set(viewHolder.getAdapterPosition(), personOrder);
//                intent.putParcelableArrayListExtra(PERSON_ORDER_LIST, mPersonOrderList);
//                LocalBroadcastManager.getInstance(viewHolder.personOrderNameEditText.getContext()).sendBroadcast(intentName);
//                Toast.makeText(viewHolder.personOrderNameEditText.getContext(), "Ordername edititing", Toast.LENGTH_SHORT ).show();


            }
        });

        //Set text changelistenor for personOrderCostEditText
        viewHolder.personOrderCostEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                viewID = editTextOrderCost.getId();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                onEditTextChanged.onTextChanged(position, charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //set text to person order and update persorOrderlist
//                personOrder.setmOrderCost(Integer.parseInt(onEditTextChanged.toString()));
//                mPersonOrderList.set(position, personOrder);
            }
        });

        //Set text changelistenor for personOrderMultipleAmountEditText
        viewHolder.personOrderMultipleAmountEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                viewID = editTextOrderMultipleAmount.getId();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                onEditTextChanged.onTextChanged(position, charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //TODO: update personOrderList position amount

            }
        });


        //Delete order
        imageViewDeleteOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentPosition = viewHolder.getAdapterPosition();//gets updated position
                //update text view of order name, cost and amount for deleted view to be blank
                editTextOrderName.setText("");
                editTextOrderCost.setText("");
                editTextOrderMultipleAmount.setText("");

                //delete item and update person order list
                removeItem(currentPosition);

                //broadcast updated mPersonOrderlist
                intent.putExtra("position", currentPosition);
                //new broadcast
                intent.putParcelableArrayListExtra(PERSON_ORDER_LIST, mPersonOrderList);

                LocalBroadcastManager.getInstance(view.getContext()).sendBroadcast(intent);
                Log.d("delete-order-sent", "position " + currentPosition +" sent");


            }

        });
        */

    }

    private void removeItem(int currentPosition) {

        mPersonOrderList.remove(currentPosition);
        notifyItemRemoved(currentPosition);
        notifyItemRangeChanged(currentPosition, getItemCount());
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mPersonOrderList.size();
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
