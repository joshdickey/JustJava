package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

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
     * @return the price
     */
    private int calculatePrice() {
        return quantity * 5;
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = calculatePrice();
        displayMessage(createOrderSummary(price));
    }

    /**
     * Order Summary
     *
     * @return the summary of the order including price
     * @param priceOfOrder is the price
     */
    private String createOrderSummary(int priceOfOrder){
        String orderSummary = "Name: Kaptain Kunal\n" +
                "Quantity: " + quantity + "\n" +
                "Total: $" + priceOfOrder + "\n" +
                "Thank You!";
        return orderSummary;
    }


    /**
     * This method increments the quantity
     */
    public void increment(View view){
        quantity++;
        displayQuantity(quantity);
    }

    /**
     * This method decrements the quantity
     */
    public void decrement(View view){

        if (quantity == 0){
            return;
        }
        quantity--;
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
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

}