package com.example.spade.thebigone;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class PriceChangeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_change);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }
    public void priceChange(View view){
        /*this method takes in a ship code like the view content
        but instead changes the price of the ship ticket
        boring some code from cruise view

         */
        CruiseList cruiseLst = CruiseList.getInstance();
        EditText codeIn = findViewById(R.id.codeIn);
        EditText priceIn = findViewById(R.id.priceGrab);
        String lookup = codeIn.getText().toString();
        TextView tv = findViewById(R.id.priceView);
        boolean flag = false;
        String output = "Price updated.";
        String output2 = "Ship not found";

        for(int i = 0; i<cruiseLst.size();i++) {
            Cruise currShip = cruiseLst.get(i);
            if (currShip.getCruiseCode().equals(lookup)) {
                //doing a comparison of the codes of desired ship and each ship we have.
                currShip.setPrice(Double.valueOf(priceIn.getText().toString()));
                cruiseLst.set(i, currShip);
                flag = true;
                if(flag)
                    tv.setText(output);
                break;//don't want to keep iterating after we have modified our data.
            }
        }
        if(!flag)//for bad inputs, so user knows if their actions were processed
                tv.setText(output2);



    }
}
