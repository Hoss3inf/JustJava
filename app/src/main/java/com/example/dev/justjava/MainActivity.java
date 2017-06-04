package com.example.dev.justjava;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
// TODO: 06/01/2017 This library works with all api's. this is a substitution to android.icu.text that works for api 24 and more.
import java.text.NumberFormat;


import com.example.dev.justjava.R;

import static android.R.attr.name;
import static android.R.attr.order;
import static android.R.id.checkbox;
import static android.R.id.content;
import static android.R.id.message;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {


    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView orderText = (TextView) findViewById(R.id.order_summary_text_view);
        orderText.setHint("Summary will be shown here");
    }


    /**
     * This method is called when the order button is clicked.
     */
// TODO: 06/03/2017 method called when ORDER button is clicked
    public void submitOrder(View view) {
        /*
          get the checkbox status whether it has been checked or not
         */
        CheckBox whippedCreamCheckbox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhipCream = whippedCreamCheckbox.isChecked();
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();
        /*
          Get the name of the customer and cast it to string
         */
        EditText nameEditText = (EditText) findViewById(R.id.name_edit_text);
        String name = nameEditText.getText().toString();
        displayMessage(createOrderSummary(name, quantity, hasWhipCream, hasChocolate));

    }

    /**
     * @param cupNumbers      number of chosen cups
     * @param addWhippedCream whether ordered whipcream or not
     * @return text summary
     */
    public String createOrderSummary(String name, int cupNumbers, boolean addWhippedCream, boolean hasChocolate) {

        int fee = 5;
        String priceMessage = "Name:" + name;
        priceMessage = priceMessage + "\nQuantity: " + cupNumbers;
        if (hasChocolate) {
            priceMessage = priceMessage + "\nYou have ordered Chocolate";
            fee = fee + 1;
        } else priceMessage = priceMessage + "\nYou didn't order the chocolate topping";
        if (addWhippedCream) {
            priceMessage = priceMessage + "\nYou have ordered whippedcream";
            fee = fee + 2;
        } else priceMessage = priceMessage + "\nYou didn't order the topping";
        priceMessage = priceMessage + "\nTotal: $" + (cupNumbers * fee);
        priceMessage = priceMessage + "\nThank you!";


        return priceMessage;

    }

    // TODO: 06/03/2017 method to show string in price_text_view field
    public void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(message);

    }

    //// TODO: 06/01/2017  method to call when increment is clicked
//    This method is called when the + button is clicked.
    public void increment(View view) {
        quantity++;
        display(quantity);

    }

    // // TODO: 06/01/2017 method called when decrement button is clicked
    //This method is called when - button is clicked.
    public void decrement(View view) {
        quantity--;
        display(quantity);


    }
    // TODO: 06/01/2017 methods to display quantity and price in textboxes

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText(number);
    }

    //This method displays the price value in desired currency in Textbox.
    private void displayPrice(int number) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        // TODO: 06/01/2017 numberformat cannot used by itself. "java.text" should be added to it to work. 
        orderSummaryTextView.setText(java.text.NumberFormat.getCurrencyInstance().format(number));

    }
}