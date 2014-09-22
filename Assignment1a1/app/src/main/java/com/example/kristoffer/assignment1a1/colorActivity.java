package com.example.kristoffer.assignment1a1;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;


public class colorActivity extends Activity {

    RelativeLayout a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);

        /* Finds views from the ids defined in the XML file */
        a = (RelativeLayout) findViewById(R.id.myScreen);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.color, menu);
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

    public void ButtonOnClick(View v) {
        switch (v.getId()) {

            case R.id.btnRed:
                a.setBackgroundColor(Color.RED); //Sets the background to Red when the button is clicked
                break;

            case R.id.btnYellow:
                a.setBackgroundColor(Color.YELLOW); //Sets the background to Yellow when the button is clicked
                break;

            case R.id.btnBlue:
                a.setBackgroundColor(Color.BLUE); //Sets the background to Blue when the button is clicked
                break;
        }
    }
}
