package com.example.spade.thebigone;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AddShipActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ship2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    public void addActivity(View view){
        EditText lineET = findViewById(R.id.shipLine);
        EditText nameET = findViewById(R.id.shipName);
        EditText codeEt = findViewById(R.id.shipCode);
        EditText regionET = findViewById(R.id.shipRegion);
        EditText lengthET = findViewById(R.id.shipLength);
        EditText priceET = findViewById(R.id.shipPrice);
        EditText imageET = findViewById(R.id.shipImage);
        EditText gratET = findViewById(R.id.shipGrat);

        Log.d("press","after initializing");

        String line = lineET.getText().toString();
        String name = nameET.getText().toString();
        String code = codeEt.getText().toString();
        String region = regionET.getText().toString();
        int length = Integer.valueOf(lengthET.getText().toString());
        double price = Double.valueOf(priceET.getText().toString());
        String urlString = imageET.getText().toString();
        double gratuity = Double.valueOf(gratET.getText().toString());

        TextView tv = findViewById(R.id.bottomView);

        Cruise newShip = new Cruise(line,name,code,region,length,price,gratuity,urlString);
        try {
            CruiseList cruiseL = CruiseList.getInstance();
            cruiseL.add(newShip);
        }catch(Exception e){
            tv.append(e.toString());
        }
        tv.append("Success");

    }

}
