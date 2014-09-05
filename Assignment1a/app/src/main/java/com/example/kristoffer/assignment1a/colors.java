package com.example.kristoffer.assignment1a;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class colors extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);

        final Button b = (Button) findViewById(R.id.button);
        final Button c = (Button) findViewById(R.id.button1);
        final Button d = (Button) findViewById(R.id.button2);
        final RelativeLayout a = (RelativeLayout) findViewById(R.id.Screen);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a.setBackgroundColor(Color.RED);

            }
        });

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a.setBackgroundColor(Color.YELLOW);

            }
        });

        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a.setBackgroundColor(Color.BLUE);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.colors, menu);
        getMenuInflater().inflate(R.menu.my, menu);
        getMenuInflater().inflate(R.menu.random, menu);
        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_color) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
