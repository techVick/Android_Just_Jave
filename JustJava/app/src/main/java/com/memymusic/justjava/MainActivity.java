/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava; 
 */
package com.memymusic.justjava;
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

import com.memymusic.justjava.R;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateBox.isChecked();

        EditText text = (EditText) findViewById(R.id.name_field);
        String value = text.getText().toString();

      int price = calculatePrice(hasWhippedCream, hasChocolate);
      /**  String priceMessage=" Total price is INR " + price + "\n Thank You,\n Please visit us again !";*/
        /**displayMessage(priceMessage);*/
        String textfinal = createOrderSummary(price, quantity, hasWhippedCream, hasChocolate, value);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:ira.sharma03@gmail.com")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "just java order for " + value);
        intent.putExtra(Intent.EXTRA_TEXT, textfinal);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        displayMessage(textfinal);
    }
    public void increment(View view) {
        if (quantity==100){
            Toast.makeText(this,"you cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity=quantity+1;
        display(quantity);
    }
    public void decrement(View view) {
        if (quantity==1){
            Toast.makeText(this,"you cannot have lass than 1 coffees", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity=quantity-1;
        display(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /**
     * This method displays the given price on the screen.
     */
    /**private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
    /**
     * This method displays the given text on the screen.
     */

    private String createOrderSummary(int price, int quantity, boolean addWhippedCream, boolean addChocolate, String nameField){
        return  "Name: "+ nameField + "\n Quantity: " + quantity + "\n Add Whipped Cream ?"+ addWhippedCream + "\n Add Chocolate ?"+ addChocolate + "\n Total price is INR " + price + "\n Thank You,\n Please visit us again !";
    }

    private int calculatePrice(boolean addWhippedCream, boolean addChocolate){
        int basePrice = 5;
        if (addWhippedCream){
            basePrice=basePrice+1;
        }
        if (addChocolate){
            basePrice=basePrice+2;
        }
        return basePrice*quantity;
    }

    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}