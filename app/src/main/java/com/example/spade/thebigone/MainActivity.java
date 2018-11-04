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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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
        //code for viewing list
        int a;

        TextView tv = findViewById(R.id.MainScreenText);
        CruiseList cl;
        cl = CruiseList.getInstance();
        tv.setText("");
        for(a = 0; a <cl.size();a++){

            Log.d("Main","loop"+String.valueOf(a));

            if(cl.get(a).getCruiseLine()!=null){
                Log.d("MainAct","before loop");
                tv.append(cl.get(a).getCruiseLine()+", ");
                tv.append(cl.get(a).getShipName()+", ");
                Log.d("MainAct","mid-loop");
                tv.append(cl.get(a).getCruiseCode()+", ");
                tv.append(String.valueOf(cl.get(a).getPrice())+"\n");
            }

        }



    }
    public void onOption2(MenuItem i){
        tv.setText("");//needs to open the add cruise activity screen, doesn't need to pass data
        startActivity(new Intent(this,AddShipActivity.class ));
    }
    public void onOption3(){
        //opens activity that gives option to see details of one specific cruise
    }
    public void onOption4(){
        //changes the price of a cruise.
    }
    public void onOption5(){
        //removes a cruise from the list
    }
    public void onOption6(){
        //displays most expensive cruise, doesn't need new activity
    }
    public void onOption7(){
        //shows average cruise length
    }
    public void onOption8(){
        //custom option, read email u sent to logan
    }
    public void onOption9(MenuItem i){
        //load list from file
        startActivity(new Intent(this, FileLoader.class));
    }
    public void onOption(){
        //save list to file
    }
}
