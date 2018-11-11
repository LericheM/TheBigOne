package com.example.spade.thebigone;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class RemoveShip extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_ship);
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
    }
    public void removeShip(View view){
        /*Simple method, loops through entire ship arraylist,
        and when the matching ship code is found the ship is deleted and a
        success message will be printed to ths screen.
         */
        CruiseList cruiseLst = CruiseList.getInstance();
        EditText et = findViewById(R.id.CruiseCodeIn);
        TextView tv = findViewById(R.id.option5ET);
        String lookup = et.getText().toString();
        boolean flag = false;
        String output = "Successful deletion!";
        String output2 = "Ship not found.";

        for(int i = 0; i<cruiseLst.size();i++) {
            Cruise currShip = cruiseLst.get(i);
            if (currShip.getCruiseCode().equals(lookup)) {
                //doing a comparison of the codes of desired ship and each ship we have.
                Cruise empty = new Cruise();
                cruiseLst.set(i,empty);
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
