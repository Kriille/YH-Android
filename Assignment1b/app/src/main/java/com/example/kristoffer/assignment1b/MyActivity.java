package com.example.kristoffer.assignment1b;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;


public class MyActivity extends Activity {

    //ChronoMeter
    Chronometer iChrono;
    Button startButton, pauseButton, resetButton;
    long lastPause = 0, waited = 30;

    //RadioButtons
    RadioButton badRadioButton, okRadioButton, goodRadioButton;

    //Checkbox
    CheckBox friendlyCheckbox, specialsCheckbox, opinionCheckbox;

    //EditText
    EditText editTextTip, editTextBill, editTextFinalBill;

    //Spinner
    Spinner solvingSpinner;

    //Seekbar
    SeekBar tipSeekbar;


    double tip, bill, finalBill;
    private final static int OK = 0;
    private final static int BAD = -1;
    private final static int GOOD = 1;
    private int[] checklistProcent = new int [12];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        tip = 0.15;
        bill = 0.0;
        finalBill = 0.0;


        //RadioButtons findViewById
        badRadioButton = (RadioButton) findViewById(R.id.badRadio);
        okRadioButton = (RadioButton) findViewById(R.id.okRadio);
        goodRadioButton = (RadioButton) findViewById(R.id.goodRadio);


        //Checkbox findViewById
        friendlyCheckbox = (CheckBox) findViewById(R.id.friendlyCheck);
        specialsCheckbox = (CheckBox) findViewById(R.id.specialsCheck);
        opinionCheckbox = (CheckBox) findViewById(R.id.opinionCheck);

        //EditText findViewById
        editTextTip = (EditText) findViewById(R.id.tipEditText);
        editTextBill = (EditText) findViewById(R.id.billEditText);
        editTextFinalBill = (EditText) findViewById(R.id.finalEditText);

        //Spinner findViewById
        solvingSpinner = (Spinner) findViewById(R.id.spinner);

        //Seekbar findViewById
        tipSeekbar = (SeekBar) findViewById(R.id.seekBar);

        //Listners for Seekbar and bill
        tipSeekbar.setOnSeekBarChangeListener(tipSeekbarListner);
        editTextBill.addTextChangedListener(billListner);

        //Chronometer
        startButton = (Button) findViewById(R.id.startBtn);
        pauseButton = (Button) findViewById(R.id.pauseBtn);
        resetButton = (Button) findViewById(R.id.resetBtn);
        iChrono = (Chronometer) findViewById(R.id.chronometer);
        TextView WatingTime;

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iChrono.setBase(SystemClock.elapsedRealtime() + lastPause);
                iChrono.start();
            }
        });
        pauseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                lastPause = iChrono.getBase() - SystemClock.elapsedRealtime();
                iChrono.stop();
            }
        });
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iChrono.setBase(SystemClock.elapsedRealtime());
                lastPause = 0;
            }
        });
    }




    private TextWatcher billListner = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

        }

        @Override
        public void onTextChanged(CharSequence i, int i1, int i2, int i3) {

            try{
                bill = Double.parseDouble(i.toString());
            }
            catch (NumberFormatException e){
                bill = 0.0;
            }
            finalBillAndTipUpdate();

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private SeekBar.OnSeekBarChangeListener tipSeekbarListner = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            tip = (tipSeekbar.getProgress()) * 0.01;

            editTextTip.setText(String.format("%.02f", tip));

            finalBillAndTipUpdate();

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private void finalBillAndTipUpdate(){
        double tip = Double.parseDouble(editTextTip.getText().toString());
        double finalbill = bill + (bill * finalBill);

        editTextFinalBill.setText(String.format("%.02f", finalBill));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
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
