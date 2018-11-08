package com.example.spade.thebigone;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.facebook.drawee.view.SimpleDraweeView;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CruiseViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cruise_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void cruiseView(View view){
        /*In this method I'm validating that the ship exists,
         after validating that the ship is in my list I'll call a method that
          has no returns, takes the ship url as a parameter, then allows my
          drawee object to display the ship image*/
        CruiseList cruiseLst = CruiseList.getInstance();
        EditText codeIn = findViewById(R.id.codeInput);
        String lookup = codeIn.getText().toString();
        String imageLink;
        boolean flag = false;
        TextView tv = findViewById(R.id.SuccessStatus);
        String badInput = "Code not valid.";
        String output;

        for(int i = 0; i<cruiseLst.size()-1;i++){
            Cruise currShip = cruiseLst.get(i);
            Log.d("cdd",cruiseLst.get(i).getShipName());
            if (currShip.getCruiseCode().equals(lookup)){
                imageLink = currShip.getImageURL();
                flag = true;
                if(flag) {
                    tv.setText("");
                    shipLoad(imageLink);
                    // getting a few values for a shorter line later
                    Double tGratuity = currShip.getGratuity()*currShip.getCruiseLength();
                    Double tCost = currShip.getPrice()+tGratuity;
                    output = currShip.getCruiseLine()+" "+
                            currShip.getShipName()+" "+
                            currShip.getRegion()+" "
                            +"Length: "
                            +String.valueOf(currShip.getCruiseLength())
                            +" days "+"Price: $"+
                            currShip.getPrice()+
                            " "+tGratuity+
                            " "+"Total cost: $"+tCost;
                    tv.setText(output);
                }
                break;
            }
        }
        if (!flag)
            tv.setText(badInput);
    }//end of cruiseView method
    public void shipLoad(String url){
        Uri uri = Uri.parse(url);
        SimpleDraweeView draweeView = findViewById(R.id.my_image_view);
        draweeView.setImageURI(uri);


    }
}
