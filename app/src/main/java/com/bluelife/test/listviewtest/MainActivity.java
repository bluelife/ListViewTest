package com.bluelife.test.listviewtest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int UPDATE = 1;
    private ListAdapter adapter;
    private ArrayList<String> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        init();
    }
    private void init(){
        //test item:132 exception.
        items=new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            items.add("name:"+i);
        }
        ListView listView=(ListView) findViewById(R.id.listView);
        adapter=new ListAdapter(this,items);
        listView.setAdapter(adapter);
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                int count=0;
                while (count<200) {
                    count++;
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    adapter.addData("new"+count);

                        Message message = handler.obtainMessage();
                        message.what = UPDATE;
                    Bundle bundle=new Bundle();
                    bundle.putString("item","new:"+count);
                    message.setData(bundle);
                        handler.sendMessage(message);


                }
            }
        });
        thread.start();
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case UPDATE:
                    Log.i("act","update");
                    //String item=msg.getData().getString("item");
                    //adapter.addData(item);
                    adapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }
    };



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