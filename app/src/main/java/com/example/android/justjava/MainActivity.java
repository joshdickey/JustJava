package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
    }

    /**
     * Calculates the price of the order based on the current quantity.
     *
     * @param addChocolate if the user wants chocolate topping
     * @param addWhip if the user wants whipped cream topping
     * @return the price
     */
    private int calculatePrice(boolean addWhip, boolean addChocolate) {

        int basePrice = 5;
        if (addWhip){
            basePrice += 1;
        }
        if (addChocolate){
            basePrice += 2;
        }
        return quantity * basePrice;
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price;
        String emailTo = "josh.dickey1@gmail.com";
        String emailSubject = "Java Order Summary for ";

        CheckBox addWhip =(CheckBox) findViewById(R.id.whipped_cream_checkbox);
        CheckBox addChocolate = (CheckBox) findViewById(R.id.chocolate_checkbox);
        EditText name = (EditText) findViewById(R.id.name_edittext);

        price = calculatePrice(addWhip.isChecked(), addChocolate.isChecked());

        //displayMessage(createOrderSummary(price, addWhip.isChecked(), addChocolate.isChecked(), name.getText().toString()));

        //send the order summary email
        sendOrderEmail(emailTo, emailSubject, createOrderSummary(price, addWhip.isChecked(), addChocolate.isChecked(), name.getText().toString()), name.getText().toString());
   }


    /**
     * Send order as email
     *
     * @param emailTo
     * @param emailSubject
     * @param emailBody
     */

    private void sendOrderEmail(String emailTo, String emailSubject, String emailBody, String name){

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("*/*");
        intent.setData(Uri.parse("mailto:")); //only use and email app
        intent.putExtra(intent.EXTRA_EMAIL, emailTo);
        intent.putExtra(intent.EXTRA_SUBJECT, emailSubject + name);
        intent.putExtra(intent.EXTRA_TEXT, emailBody);

        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
    }


    /**
     * Order Summary
     *
     * @return the summary of the order including price
     * @param priceOfOrder is the price
     * @param name is the users name
     * @param topping1 is whipped cream
     * @param topping2 is chocolate
     */
    private String createOrderSummary(int priceOfOrder, boolean topping1, boolean topping2, String name){
        String orderSummary = "Name: " + name + "\n" +
                "Add whipped cream? " + topping1 + "\n" +
                "Add chocolate? " + topping2 + "\n" +
                "Quantity: " + quantity + "\n" +
                "Total: $" + priceOfOrder + "\n" +
                "Thank You!";
        return orderSummary;
    }


    /**
     * This method increments the quantity
     */
    public void increment(View view){

        if(quantity == 100){
            //show error message as a toast
            Toast.makeText(this, "You cannot have more than 100 cups of Coffee", Toast.LENGTH_SHORT).show();
            return;
        }else {
            quantity++;
        }
        displayQuantity(quantity);
    }

    /**
     * This method decrements the quantity
     */
    public void decrement(View view){

        if (quantity == 0){
//show error message as a toast
            Toast.makeText(this, "You cannot have less than 0 cups of Coffee", Toast.LENGTH_SHORT).show();
            return;
        }else {
            quantity--;
        }
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
   /* private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }*/

}