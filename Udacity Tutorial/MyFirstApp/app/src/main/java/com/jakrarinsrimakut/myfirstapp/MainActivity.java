package com.jakrarinsrimakut.myfirstapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log; //log messages to your console
import android.widget.AnalogClock;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DigitalClock;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    /* public static final String MY_TAG = "the_custom_message";

    private EditText pass_word;
    private Button button_sbm;


    private CheckBox check1, check2, check3;
    private Button button_sel;


    private static RadioGroup radio_g;
    private static RadioButton radio_b;// this is so you can only select one radio button
    private static Button button_sbm;


    private static Button button_sbm;
    private static TextView text_v;
    private static RatingBar rating_b;

    private static Button button_sbm;

    private static Button button_sbm;
*/
    private static Button buttonSbm;
    private static DigitalClock digital;
    private static AnalogClock analog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        /*
        Log.i(MY_TAG, "onCreate");

        addListener();

        addListenerToCheckBox();
        addListenerOnButton();

        listenerForRaingBar();
        onButtoClickListener();

        onButtonClickListener();

        OnClickButtonListener();
        */
        onButtonClickListener();
    }


    /*
     @Override
            protected void onStart() {
                super.onStart();
                Log.i(MY_TAG, "onStart");
            }


            @Override
            protected void onResume() {
                super.onResume();
                Log.i(MY_TAG, "onResume");
            }


            @Override
            protected void onPause() {
                super.onPause();
                Log.i(MY_TAG, "onPause");
            }


            @Override
            protected void onStop() {
                super.onStop();
                Log.i(MY_TAG, "onStop");
            }


            @Override
            protected void onRestart() {
                super.onRestart();
                Log.i(MY_TAG, "onRestart");
            }


            @Override
            protected void onDestroy() {
                super.onDestroy();
                Log.i(MY_TAG, "onDestroy");
            }

        public void onButtonClick(View v) {
            EditText e1 = (EditText)findViewById(R.id.editText);
            EditText e2 = (EditText)findViewById(R.id.editText2);
            TextView t1 = (TextView)findViewById(R.id.textView);
            int num1 = Integer.parseInt(e1.getText().toString());
            int num2 = Integer.parseInt(e2.getText().toString());
            int sum = num1 + num2;
            t1.setText(Integer.toString(sum));
        }
        public void addListener() {
            pass_word = (EditText) findViewById(R.id.editText);
            button_sbm = (Button) findViewById(R.id.button);
            button_sbm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(
                        MainActivity.this, pass_word.getText(),
                        Toast.LENGTH_SHORT
                    ).show();
                }
            });
        }

    public void addListenerToCheckBox(){
        check1 = (CheckBox)findViewById(R.id.checkBox_dog);
        check1.setOnClickListener(
                new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        if(((CheckBox)v).isChecked()){
                            Toast.makeText(MainActivity.this,
                                    "Dog is selected",Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }
    public void addListenerOnButton() {
        check1 = (CheckBox) findViewById(R.id.checkBox_dog);
        check2 = (CheckBox) findViewById(R.id.checkBox_cat);
        check3 = (CheckBox) findViewById(R.id.checkBox_cow);
        button_sel = (Button) findViewById(R.id.button);

        button_sel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                StringBuffer result = new StringBuffer();
                result.append("Dog : " ).append(check1.isChecked());
                result.append("\nCat : " ).append(check2.isChecked());
                result.append("\nCow : " ).append(check3.isChecked());

                Toast.makeText(MainActivity.this,result.toString(),
                    Toast.LENGTH_LONG).show();
            }
        })

        ;

    }


    public void onClickListenerButton(){
        radio_g = (RadioGroup)findViewById(R.id.rg_animals);
        button_sbm = (Button)findViewById(R.id.button);

        button_sbm.setOnClickListener(
                new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        int selected_id = radio_g.getCheckedRadioButtonId();
                        radio_b = (RadioButton)findViewById(selected_id);
                        Toast.makeText(MainActivity.this,radio_b.getText().toString(),Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    public void listenerForRaingBar() {
        rating_b = (RatingBar) findViewById(R.id.ratingBar);
        text_v = (TextView)findViewById(R.id.textView);

        rating_b.setOnRatingBarChangeListener(
                new RatingBar.OnRatingBarChangeListener() {

                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        text_v.setText(String.valueOf(rating));
                    }
                }
        );
    }

    public void onButtoClickListener() {
        rating_b = (RatingBar) findViewById(R.id.ratingBar);
        button_sbm = (Button) findViewById(R.id.button);

        button_sbm.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, String.valueOf(rating_b.getRating()),Toast.LENGTH_SHORT).show();//Change value with String.valueOf
                    }
                }
        );
    }

    public void onButtonClickListener() {
    button_sbm = (Button)findViewById(R.id.button);
        button_sbm.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);
                        a_builder.setMessage("Do you want to Close this App!!!")
                                .setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog alert = a_builder.create();
                        alert.setTitle("Alert !!!");
                        alert.show();
                    }


                }
        );
    }

    public void OnClickButtonListener() {
        button_sbm = (Button)findViewById(R.id.button);
        button_sbm.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.jakrarinsrimakut.myfirstapp.SecondActivity");
                        startActivity(intent);
                    }
                }
        );
    }
*/
    public void onButtonClickListener(){
        buttonSbm = (Button)findViewById(R.id.button);
        digital = (DigitalClock)findViewById((R.id.digitalClock));
        analog = (AnalogClock)findViewById(R.id.analogClock);

        buttonSbm.setOnClickListener(
                new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        if(digital.getVisibility() == DigitalClock.GONE){
                            digital.setVisibility(DigitalClock.VISIBLE);
                            analog.setVisibility(AnalogClock.GONE);
                        }else{
                            digital.setVisibility(DigitalClock.GONE);
                            analog.setVisibility(AnalogClock.VISIBLE);
                        }
                    }
                }
        );
    }
    @Override

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
