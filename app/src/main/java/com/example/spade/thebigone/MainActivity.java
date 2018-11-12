package com.example.spade.thebigone;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    public TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        CruiseList cl;
        //from string list lab, allows to set without having to retrieve in each method


        //initializing the cruise list to be used
        cl = CruiseList.getInstance();

        if(cl.isEmpty()){
            cl.add(cl.size(),new Cruise());
            cl.add(cl.size(),new Cruise());
            cl.add(cl.size(),new Cruise());
            cl.add(cl.size(),new Cruise());
            cl.add(cl.size(),new Cruise());
            cl.add(cl.size(),new Cruise());
            cl.add(cl.size(),new Cruise());
            cl.add(cl.size(),new Cruise());
        }

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
    public void onOption1(MenuItem i){
        //Simple method to view updated list and write it to main TV
        int a;

        TextView tv = findViewById(R.id.MainScreenText);
        CruiseList cl;
        cl = CruiseList.getInstance();
        tv.setText("");
        for(a = 0; a <cl.size();a++){


            if(cl.get(a).getCruiseLine()!=null){
                tv.append(cl.get(a).getCruiseLine()+", ");
                tv.append(cl.get(a).getShipName()+", ");
                tv.append(cl.get(a).getCruiseCode()+", ");
                tv.append(String.valueOf(cl.get(a).getPrice())+"\n");
            }

        }



    }
    public void onOption2(MenuItem i){

        startActivity(new Intent(this,AddShipActivity.class));
    }
    public void onOption3(MenuItem i){

        startActivity(new Intent(this,CruiseViewActivity.class));
    }
    public void onOption4(MenuItem i){
        startActivity(new Intent(this,PriceChangeActivity.class));
    }
    public void onOption5(MenuItem i){
        startActivity(new Intent(this,RemoveShip.class));
    }
    public void onOption6(MenuItem i){
        //displays most expensive cruise, doesn't need new activity
        int g;
        CruiseList cl = CruiseList.getInstance();
        Cruise thisShip;
        //the next two variables aren't necessary but it makes it easier for me to read
        Double highestPrice = 0.0;
        Double currentPrice;
        Cruise pricey = new Cruise();
        TextView tv = findViewById(R.id.MainScreenText);
        String outputString;
        String pattern = "####.00";
        DecimalFormat numOut = new DecimalFormat(pattern);

        int shipLength = 0;
        for(g = 0; g <cl.size();g++){
            //this method is the loop that gets our highest price from the cruise list
            thisShip = cl.get(g);
            currentPrice = thisShip.getPrice();
            if(currentPrice>highestPrice) {
                highestPrice = currentPrice;
                shipLength = cl.get(g).getCruiseLength();
                //last we save the ship since it is the most expensive for our text output later
                pricey = cl.get(g);
            }
        }
        if(pricey!=null){
            double totalCost = highestPrice/shipLength;
            //format the number before adding it to the string
            String format = numOut.format(totalCost);

            outputString = pricey.getCruiseLine()+", "+pricey.getShipName()+", "+
                    pricey.getCruiseCode()+", "+format+" per night.";
            tv.setText(outputString);
        }



    }
    public void onOption7(MenuItem i){
        //shows average cruise length
        CruiseList cl = CruiseList.getInstance();
        Double sum = 0.0;
        int m;
        int divisor = 0;
        TextView textView = findViewById(R.id.MainScreenText);

        for (m = 0; m < cl.size();m++){
            /*i have to manually add the divisor because the
            cruise list can have empty ship items which i've been ignoring
             */
            if(cl.get(m).getCruiseLine()!=null){
                //use .getCruiseLine to check if our ship exists
                sum+= cl.get(m).getCruiseLength();
                divisor++;
            }
        }
        Double average = sum/divisor;
        String pattrn = "####.00";
        DecimalFormat decimalFormat = new DecimalFormat(pattrn);
        String avgStr = decimalFormat.format(average);
        if(average>0.0) {
            //the conditional exists as a fail safe if this action is done without a list
            String out = "The average length of all cruises is: " + avgStr + " days.";
            textView.setText(out);
        }
        else{
            String output = "Please add more ships to compare.";
            textView.setText(output);
        }

    }
    public void onOption8(MenuItem i ){
        /* compare the ratio of ship name length to price, and the
         ship with the highest amount of name length per dollar
         is the most luxurious, because the money's all in the name.
         */
        Cruise luxShip = new Cruise();
        CruiseList cl = CruiseList.getInstance();
        int iterator;
        double luxMeter = 0;
        double tempLux;
        int shipNameLength;
        TextView tv = findViewById(R.id.MainScreenText);

        for(iterator = 0; iterator <cl.size();iterator++){
            if(luxShip.getCruiseLine() == null) {
                /*this conditional is solely for the first loop of the for loop
                since i initialized the luxurious ship to be an empty ship object
                also we don't need to make any comparisons because we dont have any
                data to compare to until the second loop
                 */
                luxShip = cl.get(iterator);
                shipNameLength = luxShip.getShipName().length();
                luxMeter = shipNameLength/luxShip.getPrice();
                //first loo
                continue;
            }
            /*here is where the ratio is actually calculated, we then do the comparison
            and will replace the ship so we can access it for output later.
             */
            shipNameLength = cl.get(iterator).getCruiseLength();
            tempLux = shipNameLength/cl.get(iterator).getPrice();

            if(tempLux>luxMeter){
                luxShip = cl.get(iterator);
            }

        }
        String output;
        if(luxMeter!= 0){
            output = "The most luxurious ship is: "+
                    luxShip.getShipName()+ " from: "+
                    luxShip.getCruiseLine();
            tv.setText(output);
        }
        else{
            output = "No valid data!";
            tv.setText(output);
        }

    }
    public void onOption9(MenuItem i){
        //load list from online file
        startActivity(new Intent(this, FileLoader.class));
    }
    public void onOption10(MenuItem i ){
        //save list to file

    }
}
