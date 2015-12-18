package com.aks.serchablelist;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity implements TextWatcher{

    EditText searchText;
    CustomAdapter listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchText = (EditText) findViewById(R.id.searchView);
        searchText.addTextChangedListener(this);
        ArrayList<String> stringList = new ArrayList<String>();
        
        for (int i = 0; i < Cheeses.sCheeseStrings.length; i++) {
            stringList.add(Cheeses.sCheeseStrings[i]);
        }
        
        ListView listView = (ListView) findViewById(R.id.listadapter);
        listAdapter = new CustomAdapter(this, stringList);
        listView.setAdapter(listAdapter);
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
    public void beforeTextChanged(CharSequence s, int start, int count,
            int after) {
        
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        listAdapter.getFilter().filter(searchText.getText().toString());
    }

    @Override
    public void afterTextChanged(Editable s) {
        
    }
}
