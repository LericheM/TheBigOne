package com.example.spade.thebigone;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class FileLoader extends AppCompatActivity {
    //the activity used to actually load data from an online file.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_loader);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }
    public void fileCheck(View view){
        /*when button is pressed this method will check if the file exists and then fill the
        list with the ship data from the URL Page
         */
        EditText et = findViewById(R.id.FileInputET);
        String fileName = et.getText().toString();
        TextView tv2 = findViewById(R.id.fileStatusTV);
        Scanner fsc;
        CruiseList cl = CruiseList.getInstance();
        int i = 0;

        try{
            URL file_url = new URL(fileName);
            fsc = new Scanner(file_url.openStream());

            do{
                //using a do while because with my implementation of hasnext()

                //if( cl.get(i).getCruiseLine() == null){

                    //if the ship has a null cruise line, then we know we can replace it
                    cl.get(i).setCruiseLine(fsc.nextLine());
                    cl.get(i).setShipName(fsc.nextLine());
                    cl.get(i).setCruiseCode(fsc.nextLine());
                    cl.get(i).setRegion(fsc.nextLine());
                    cl.get(i).setCruiseLength(fsc.nextInt());
                    fsc.nextLine(); //to resolve problems with input scanning
                    cl.get(i).setPrice(fsc.nextDouble());
                    fsc.nextLine();
                    cl.get(i).setGratuity(fsc.nextDouble());
                    fsc.nextLine();
                    cl.get(i).setImageURL(fsc.nextLine());
                    i++;
                    //***remember to convert this to a URL object in processing***
                //}


            }
            while(fsc.hasNext());
            tv2.setText("");
            tv2.append((String)"Successful.");
            //use text view to tell successful list add
            fsc.close();




        }
        catch (IOException e){
            tv2.setText(e.toString());
            //use text view to tell error status

        }

    }
}
