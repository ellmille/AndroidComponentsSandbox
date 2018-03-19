package com.sandbox.androidcomponents;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.sandbox.androidcomponents.data.model.TestMessage;
import com.sandbox.androidcomponents.ui.MyItemRecyclerViewAdapter;
import com.sandbox.androidcomponents.viewmodel.ListViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    MyItemRecyclerViewAdapter adapter;
    ListViewModel model;
    int i = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.list);
        adapter = new MyItemRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);

        model = ViewModelProviders.of(this).get(ListViewModel.class);
        model.getMessageList().observe(this, new Observer<List<TestMessage>>() {
            @Override
            public void onChanged(@Nullable List<TestMessage> testMessages) {
                //update the list
                adapter.setList(testMessages);
            }
        });

        FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                model.addMessageToList(new TestMessage("Title" + i, "Message" + i));
                i++;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
