package com.example.jakrarinsrimakut.powergenerator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button twoEqualsButton= (Button) findViewById(R.id.twoEqualsButton);
        final Button threeEqualsButton= (Button) findViewById(R.id.threeEqualsButton);
        final Button fourEqualsButton= (Button) findViewById(R.id.fourEqualsButton);
        final Button fiveEqualsButton= (Button) findViewById(R.id.fiveEqualsButton);

        /*when equal's button is clicked evaluate*/
        twoEqualsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView twoTextView = (TextView) findViewById(R.id.twoTextView);
                EditText twoExponentEditText = (EditText) findViewById(R.id.twoExponentEditText);
                TextView twoResultTextView = (TextView) findViewById(R.id.twoResultTextView);

                int twoNum1 = Integer.parseInt(twoTextView.getText().toString());
                int twoExponentNum2 = Integer.parseInt(twoExponentEditText.getText().toString());
                float twoResultFloat = (float )Math.pow(twoNum1, twoExponentNum2);
                String twoResult = String.format("%.2f", twoResultFloat);
                twoResultTextView.setText(twoResult + "");//convert result to string by concatenating with + ""
            }
        });

        threeEqualsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView threeTextView = (TextView) findViewById(R.id.threeTextView);
                EditText threeExponentEditText = (EditText) findViewById(R.id.threeExponentEditText);
                TextView threeResultTextView = (TextView) findViewById(R.id.threeResultTextView);

                int threeNum1 = Integer.parseInt(threeTextView.getText().toString());
                int threeExponentNum2 = Integer.parseInt(threeExponentEditText.getText().toString());
                float threeResultFloat = (float )Math.pow(threeNum1, threeExponentNum2);
                String threeResult = String.format("%.2f", threeResultFloat);
                threeResultTextView.setText(threeResult + "");//convert result to string by concatenating with + ""
            }
        });

        fourEqualsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView fourTextView = (TextView) findViewById(R.id.fourTextView);
                EditText fourExponentEditText = (EditText) findViewById(R.id.fourExponentEditText);
                TextView fourResultTextView = (TextView) findViewById(R.id.fourResultTextView);

                int fourNum1 = Integer.parseInt(fourTextView.getText().toString());
                int fourExponentNum2 = Integer.parseInt(fourExponentEditText.getText().toString());
                float fourResultFloat = (float )Math.pow(fourNum1, fourExponentNum2);
                String fourResult = String.format("%.2f", fourResultFloat);
                fourResultTextView.setText(fourResult + "");//convert result to string by concatenating with + ""
            }
        });

        fiveEqualsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView fiveTextView = (TextView) findViewById(R.id.fiveTextView);
                EditText fiveExponentEditText = (EditText) findViewById(R.id.fiveExponentEditText);
                TextView fiveResultTextView = (TextView) findViewById(R.id.fiveResultTextView);

                int fiveNum1 = Integer.parseInt(fiveTextView.getText().toString());
                int fiveExponentNum2 = Integer.parseInt(fiveExponentEditText.getText().toString());
                float fiveResultFloat = (float )Math.pow(fiveNum1, fiveExponentNum2);
                String fiveResult = String.format("%.2f", fiveResultFloat);
                fiveResultTextView.setText(fiveResult + "");//convert result to string by concatenating with + ""
            }
        });
    }
}
