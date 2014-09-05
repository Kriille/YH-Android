package com.example.kristoffer.assignment1a;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Random extends Activity {


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        final Button buttonG = (Button)findViewById(R.id.button);
        final TextView TextG = (TextView)findViewById(R.id.textView);

        buttonG.setOnClickListener(new View.OnClickListener() {
            public void onClick(View View) {
                final double myRandom = Math.random() * 100;
                double rnd = myRandom;
                TextG.setText( String.format("%.2f", rnd));
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.random, menu);
        getMenuInflater().inflate(R.menu.colors, menu);
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_contact) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
