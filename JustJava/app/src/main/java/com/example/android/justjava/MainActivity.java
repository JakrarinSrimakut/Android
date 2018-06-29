package com.example.android.justjava;

/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 */

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        boolean hasWhippedCream = false;
        boolean hasChocolate = false;

        //findViewById return generic View cast it to checkbox
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        EditText nameInputEditText = (EditText) findViewById(R.id.name_input);

        //determine if checkbox has been checked
        hasWhippedCream = whippedCreamCheckBox.isChecked();
        hasChocolate = chocolateCheckBox.isChecked();

        //gets content of edit text
        String hasName = nameInputEditText.getText().toString();

        //Log.v("MainActivity", "Check box checked?" + isChecked);
        int price = calculatePrice(hasWhippedCream, hasChocolate);
        //displayMessage(createOrderSummary(price, hasWhippedCream, hasChocolate, hasName));

        String priceMessage = createOrderSummary(price, hasWhippedCream, hasChocolate, hasName);
        String subject = getString(R.string.order_summary_email_subject, hasName);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email app should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, subject );
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);


        //check that there is an app that can handle this intent
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     *
     * @param hasWhippedCream is whether or not user wants whipped cream
     * @param hasChocolate is wheteher or not user wants chocolate
     * Calculate price of order and return total price.
     */
    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate){
        //price of 1 cup of coffee
        int basePrice = 5;

        //add $1 for whippedcream
        if(hasWhippedCream){
            basePrice += 1;
        }
        //add $2 for chocolate
        if(hasChocolate){
            basePrice += 2;
        }
        //calculate total order price by multiplying to quantity
        return quantity * basePrice;
    }

    /**
     * Create summary of the order.
     *
     * @param hasName of the order
     * @param addWhippedCream is whether or not the user wants whipped cream topping
     * @param addChocolate is whether or not the user wants chocolate topping
     * @param price of the order
     * @return text summary
     */
    private String createOrderSummary(int price, boolean addWhippedCream, boolean addChocolate, String hasName){


        String priceMessage = getString(R.string.order_summary_name, hasName);//"Name" + is removed allowing translator to
                                                                     //to rearange the string depending on the the
                                                                     // language
        priceMessage += "\n" + getString(R.string.order_summary_whipped_cream,addWhippedCream);
        priceMessage += "\n" + getString(R.string.order_summary_chocolate,addChocolate);
        priceMessage += "\n" + getString(R.string.order_summary_quantity, quantity);
        priceMessage += "\n" + getString(R.string.order_summary_price, NumberFormat.getCurrencyInstance().format(price));
        priceMessage += "\n" +getString(R.string.thank_you);
        return priceMessage;
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        //restrict increment up to 100
        if(quantity == 100){
            //Show an error message as a toast
            Toast.makeText(this, getString(R.string.too_many_coffees), Toast.LENGTH_SHORT).show();
            //Exit this method early because there's nothing left to do
            return;
        }
            quantity = quantity +1;
            displayQuantity(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        //restrict decrement down to 1.
        if(quantity == 1) {
            //Show an error message as a toast
            Toast.makeText(this, getString(R.string.too_few_coffees), Toast.LENGTH_SHORT).show();
            //Exit this method early because there's nothing left to do
            return;
        }
            quantity = quantity - 1;
            displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

//    /**
//     * This method displays the given text on the screen.
//     */
//    private void displayMessage(String message) {
//        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        orderSummaryTextView.setText(message);
//    }

}