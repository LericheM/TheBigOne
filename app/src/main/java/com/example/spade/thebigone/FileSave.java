package com.example.spade.thebigone;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class FileSave extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_save);
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
    public void saveToFile(View view) {
        //taken from Halper notes, it's my preferred method of file writing
        EditText editText = findViewById(R.id.FileNameInput);
        TextView textView = findViewById(R.id.SuccessStat);
        String fileName = editText.getText().toString();
        CruiseList cl = CruiseList.getInstance();
        try {
            //using Halper's method to write data to files
            File outfile = new File(getExternalFilesDir(null), fileName);
            FileWriter fw = new FileWriter(outfile);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            for(int z = 0; z<cl.size();z++) {
                if (cl.get(z).getCruiseLine() != null) {
                    pw.write(cl.get(z).getCruiseLine() + "\n");
                    pw.write(cl.get(z).getShipName() + "\n");
                    pw.write(cl.get(z).getCruiseCode() + "\n");
                    pw.write(cl.get(z).getRegion() + "\n");
                    pw.write(String.valueOf(cl.get(z).getCruiseLength()) + "\n");
                    pw.write(String.valueOf(cl.get(z).getPrice()) + "\n");
                    pw.write(String.valueOf(cl.get(z).getGratuity()) + "\n");
                    pw.write(cl.get(z).getImageURL() + "\n");
                }

            }
            String output = "Success!";
            textView.setText(output);
            pw.close();
        }catch(Exception e){
            textView.setText(e.toString());
        }

    }
}
