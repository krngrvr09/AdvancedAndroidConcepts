package com.example.krngrvr09.byldtest;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class RaceConditionActivity extends ActionBarActivity {
    private LooperThread mLooperThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_race_condition);
        mLooperThread = new LooperThread();
        mLooperThread.start();

        Message msg = Message.obtain();
        msg.obj = "hello";
        // The handler of the LooperThread may not be initialized
        // when yu are accessing it here. This is the Race Condition.
        mLooperThread.mHandler.sendMessage(msg);
    }

    class LooperThread extends Thread{
        public Handler mHandler;
        @Override
        public void run(){
            Looper.prepare();
            mHandler = new Handler(){
              @Override
                public void handleMessage(Message msg){
                  Log.d("message", "received");
              }
            };
            Looper.loop();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_race_condition, menu);
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
