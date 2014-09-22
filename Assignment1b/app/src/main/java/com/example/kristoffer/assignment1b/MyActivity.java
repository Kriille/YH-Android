package com.example.kristoffer.assignment1b;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;


public class MyActivity extends Activity implements View.OnClickListener, Chronometer.OnChronometerTickListener {

    int tick = 0;
    long lastPause = 0;
    int tickTipLoss = 0;
    int totalInitialValue = 0, seekBarInt;


    //set EditText variable
    EditText editTextTip, editTextBill, editTextFinalBill;

    //set EditText variable
    SeekBar tipSeekbar;

    //set Checkbox variable
    CheckBox friendlyCheckbox, specialsCheckbox, opinionCheckbox;

    RadioGroup radioGroup;

    //set Spinner variable
    Spinner solvingSpinner;

    //set Spinner variable
    Chronometer timer;

    //set Button variable
    Button startButton, pauseButton, resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        //set all veriables to an XML object
        //EditText
        editTextTip = (EditText) findViewById(R.id.tipEditText);
        editTextBill = (EditText) findViewById(R.id.billEditText);
        editTextFinalBill = (EditText) findViewById(R.id.finalEditText);

        //SeekBar
        tipSeekbar = (SeekBar) findViewById(R.id.seekBar);

        //CheckBox
        friendlyCheckbox = (CheckBox) findViewById(R.id.friendlyCheck);
        specialsCheckbox = (CheckBox) findViewById(R.id.specialsCheck);
        opinionCheckbox = (CheckBox) findViewById(R.id.opinionCheck);

        //chronometer
        timer = (Chronometer) findViewById(R.id.chronometer);

        //buttons
        startButton = (Button) findViewById(R.id.startBtn);
        pauseButton = (Button) findViewById(R.id.pauseBtn);
        resetButton = (Button) findViewById(R.id.resetBtn);

        //RadioGroup
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        //Spinner
        solvingSpinner = (Spinner) findViewById(R.id.spinner);

        //Listener
        timer.setOnChronometerTickListener(this);


        //Create a spinner and fill it with array items
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinnerArr, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        solvingSpinner.setAdapter(adapter);

        //listener when select item in spinner
        solvingSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        tipSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                editTextTip.setText(i + "%");
                calcTip();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    public void calcTip() {

        //placeholders
        int checkboxBonusFriendly;
        int checkboxBonusSpecial;
        int checkboxBonusOpinion;

        int radioValue;
        int spinnerValue;


        //Checkbox
        if (friendlyCheckbox.isChecked())
            checkboxBonusFriendly = 1;
        else
            checkboxBonusFriendly = 0;

        if (specialsCheckbox.isChecked())
            checkboxBonusSpecial = 1;
        else
            checkboxBonusSpecial = 0;

        if (opinionCheckbox.isChecked())
            checkboxBonusOpinion = 1;
        else
            checkboxBonusOpinion = 0;

        //-------------------END--------------

        //Spinner
        //Bad
        if (solvingSpinner.getSelectedItemPosition() == 0)
            spinnerValue = -1;

            //Good
        else if (solvingSpinner.getSelectedItemPosition() == 2)
            spinnerValue = 1;

            //Ok
        else
            spinnerValue = 0;

        //-------------------END--------------

        //radioGroup
        if (radioGroup.findViewById(radioGroup.getCheckedRadioButtonId()) == findViewById(R.id.badRadio))
            radioValue = -1;
        else if (radioGroup.findViewById(radioGroup.getCheckedRadioButtonId()) == findViewById(R.id.goodRadio))
            radioValue = 1;
        else
            radioValue = 0;


        Float billPrice = Float.parseFloat(editTextBill.getText().toString());
        Float value = billPrice * (Float.parseFloat(tipSeekbar.getProgress() + "") / 100);

        //Calculates the percent
        value = value + (billPrice * (checkboxBonusFriendly + checkboxBonusSpecial + checkboxBonusOpinion) / 100);
        value = value + (billPrice * radioValue / 100);
        value = value + (billPrice * spinnerValue / 100);
        value = value + billPrice;
        value = value + (billPrice * tickTipLoss / 100);

        editTextFinalBill.setText(String.format("%.2f", value));
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

    @Override
    public void onClick(View view) {

    }

    public void checkOnclick(View view) {
        calcTip();
    }

    @Override
    public void onChronometerTick(Chronometer chronometer) {

        tick++;

        if ((tick % 30) == 0 && tick > 1) {
            tickTipLoss--;
            calcTip();

        }
    }

    public void btnClick(View view) {

        switch (view.getId()) {
            //When Start Button is pressed
            case R.id.startBtn:
                timer.start();
                timer.setBase(SystemClock.elapsedRealtime() + lastPause);
                break;
            //When pause Button is pressed
            case R.id.pauseBtn:
                timer.stop();
                lastPause = timer.getBase() - SystemClock.elapsedRealtime();
                break;
            //When reset Button is pressed
            case R.id.resetBtn:
                timer.setBase(SystemClock.elapsedRealtime());
                lastPause = 0;
                break;
        }

    }
}
