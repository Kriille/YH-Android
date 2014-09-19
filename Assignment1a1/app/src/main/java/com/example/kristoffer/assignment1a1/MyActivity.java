package com.example.kristoffer.assignment1a1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
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

    public void addcontact(View view) {
        // Getting reference
        EditText firstName = (EditText)findViewById(R.id.firstName);
        EditText lastName = (EditText)findViewById(R.id.lastName);
        EditText phone = (EditText)findViewById(R.id.phone);
        EditText email = (EditText)findViewById(R.id.email);

        Intent insertIntent = new Intent(Intent.ACTION_INSERT);
        insertIntent.setType(ContactsContract.RawContacts.CONTENT_TYPE);

        insertIntent.putExtra(ContactsContract.Intents.Insert.NAME, firstName.getText() + " " + lastName.getText());
        insertIntent.putExtra(ContactsContract.Intents.Insert.EMAIL, email.getText());
        insertIntent.putExtra(ContactsContract.Intents.Insert.PHONE, phone.getText());

        startActivity(insertIntent);


    }
}
