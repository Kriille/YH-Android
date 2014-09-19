package com.example.kristoffer.uppgifter;


import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;



public class customGridView extends ListActivity implements AdapterView.OnItemLongClickListener {

    ArrayList<String> names;
    ArrayAdapter<String> adapter;
    EditText addText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_grid_view);

        addText = (EditText) findViewById(R.id.editText3);

        names = new ArrayList<String>();
        names.add("Kristoffer");
        names.add("Björn");
        names.add("Svensson");

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);

        setListAdapter(adapter);
        getListView().setOnItemLongClickListener(this);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.custom_list_view, menu);
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

    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        String name = adapter.getItem(position);

        names.remove(name);
        adapter.notifyDataSetChanged();

        return false;
    }

    protected void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);

        Toast.makeText(this, adapter.getItem(position), Toast.LENGTH_SHORT).show();
    }


    public void onClickAdd(View view) {
        String name = addText.getText().toString();
        names.add(name);
        adapter.notifyDataSetChanged();
    }
}
