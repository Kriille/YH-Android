package com.example.kristoffer.assignment1a1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class randomActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);


        //Finds views from the ids defined in the XML file
        final Button buttonG = (Button)findViewById(R.id.button);
        final TextView TextG = (TextView)findViewById(R.id.textView);

        buttonG.setOnClickListener(new View.OnClickListener() {
            public void onClick(View View) {
                /*Generates a random number*/
                final double myRandom = Math.random() * 100; // This makes the random number to go from 0-100
                double rnd = myRandom;
                TextG.setText( String.format("%.2f", rnd));//This round off to two decimals and print out
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.random, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
