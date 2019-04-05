package com.example.h.checkplz;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
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

    private OnEditTextChanged onEditTextChanged;

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder{
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public EditText personOrderNameEditText;
        public EditText personOrderCostEditText;
        public TextView personOrderMultipleSymbolTextView;
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
            personOrderMultipleSymbolTextView = (TextView) itemView.findViewById(R.id.person_order_multiple_symbol);
            personOrderMultipleAmountEditText = (EditText) itemView.findViewById(R.id.person_order_multiple_amount);
            personOrderDelete = (ImageView) itemView.findViewById(R.id.person_order_delete);
        }
    }

    // Store a member variable for the contacts
    private ArrayList<PersonOrder> mPersonOrderList;

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
        // Get the data model based on position
        PersonOrder personOrder = mPersonOrderList.get(position);
        //

        ImageView imageViewDeleteOrder = viewHolder.personOrderDelete;
        // Set item views based on your views and data model
        EditText editTextOrderName = viewHolder.personOrderNameEditText;
        EditText editTextOrderCost = viewHolder.personOrderCostEditText;
        EditText editTextOrderMultipleAmount = viewHolder.personOrderMultipleAmountEditText;

        viewHolder.personOrderCostEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                onEditTextChanged.onTextChanged(position, charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });


        //Delete order
        imageViewDeleteOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(view.getContext(), "View " + position +" delete image clicked", Toast.LENGTH_SHORT).show();
                mPersonOrderList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, mPersonOrderList.size());
            }
        });

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
}
