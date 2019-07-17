package com.example.h.checkplz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Constraints;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import Utilities.Calculation;

public class PeopleBillListActivity extends AppCompatActivity implements PeopleBillListAdapter.OnBillListener{
    final String PERSON_ORDER_BILL = "person-order-bill";
    final String PERSON_ORDER_BILL_EDIT = "person-order-bill-edit";
    final String PERSON_ORDER_BILL_POSITION = "person-order-bill-position";
    public static final String MY_BILL_LIST = "MyBillList";
    ArrayList<PersonBill> mPeopleBills;
    private RecyclerView rvPeopleBillList;
    private PeopleBillListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static SharedPreferences mPrefs;
    Button showPopupBtn, deletePopUpBtn;
    PopupWindow popUpWindow;
    ConstraintLayout peopleBillLayout;
    TextView textViewPartySubtotalAmount;
    TextView textViewPartyGratuityAmount;
    TextView textViewPartyTotalAmount;
    EditText editTextPartyTaxInput;
    EditText editTextPartyTotalInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_bill_list);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("PeopleBillListActivity");

        textViewPartySubtotalAmount = (TextView)findViewById(R.id.party_subtotal_amount);
        textViewPartyGratuityAmount = (TextView)findViewById(R.id.party_gratuity_amount);
        textViewPartyTotalAmount = (TextView)findViewById(R.id.party_total_amount);
        editTextPartyTaxInput = (EditText)findViewById(R.id.tax_edit_text_input);
        editTextPartyTotalInput = (EditText)findViewById(R.id.total_edit_text_input);

        editTextPartyTaxInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        editTextPartyTotalInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        
        loadData();
        buildRecyclerView();
        updateData();

    }



    //Save the state of bill list
    @Override
    protected void onPause() {
        super.onPause();
        saveData();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mPeopleBills);
        editor.putString(MY_BILL_LIST, json);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(MY_BILL_LIST, null);
        Type type = new TypeToken<ArrayList<PersonBill>>() {}.getType();
        mPeopleBills = gson.fromJson(json, type);

        if (mPeopleBills == null) {
            mPeopleBills = new ArrayList<>();
        }
    }

    private void updateData() {
        Bundle extras = getIntent().getExtras();
        if(extras != null){//TODO: Organize: create method for if statement
            int personOrderBillPos = extras.getInt(PERSON_ORDER_BILL_POSITION);
            PersonBill mPersonBill = extras.getParcelable(PERSON_ORDER_BILL);
            if(personOrderBillPos>=0){//check if return person bill had pos if so edit else add
                mPeopleBills.set(personOrderBillPos, mPersonBill);
            }
            else{
                mPeopleBills.add(mPersonBill);
            }
            //TODO: Create a method update party's bill that calls methods in Calculation Class that return subtotal,gratuity and total to be set in party's bill
            if(mPersonBill != null){
                updatePartyBill();
            }
        }
    }

    private void updatePartyBill() {
        textViewPartySubtotalAmount.setText(String.valueOf(Calculation.calculatePartySubtotal(mPeopleBills)));
        textViewPartyGratuityAmount.setText(String.valueOf(Calculation.calculatePartyGratuity(mPeopleBills)));
        textViewPartyTotalAmount.setText(String.valueOf(Calculation.calculatePartyTotal(mPeopleBills)));
    }

    private void buildRecyclerView() {
        rvPeopleBillList = findViewById(R.id.rv_people_bill_list);
        rvPeopleBillList.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new PeopleBillListAdapter(mPeopleBills, this);//this can be used due to implements PeopleBillListAdapter.OnBillListener. Pass this OnBillListener to adapter

        rvPeopleBillList.setLayoutManager(mLayoutManager);
        rvPeopleBillList.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.enter_person_input_bill_activity,menu);
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

    @Override
    public void onBillClick(int position) {
        Intent intent = new Intent(this, PersonInputBillActivity.class);
        intent.putExtra(PERSON_ORDER_BILL_EDIT, mPeopleBills.get(position));//Send personbill
        intent.putExtra(PERSON_ORDER_BILL_POSITION, position);//send the position of person bill to be able to edit the correct bill
        startActivity(intent);
    }

    @Override
    public void onBillLongClick(int position) {
        generatePopUpWindow(position);
    }

    public void generatePopUpWindow(final int position){
        LayoutInflater layoutInflater = (LayoutInflater) PeopleBillListActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customView = layoutInflater.inflate(R.layout.popup,null);

        peopleBillLayout = (ConstraintLayout) findViewById(R.id.people_bill_layout);

        deletePopUpBtn = (Button) customView.findViewById(R.id.person_bill_delete);

        popUpWindow = new PopupWindow(customView, ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);

        popUpWindow.showAtLocation(peopleBillLayout, Gravity.CENTER, 0,0);

        deletePopUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPeopleBills.remove(position);
                mAdapter.notifyItemRemoved(position);
                //
                popUpWindow.dismiss();
            }
        });
    }
}
