package com.example.krngrvr09.byldtest;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class HandlerThreadActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_thread);

        MyHandlerThread mht = new MyHandlerThread("lol");
        mht.start();
//        mht.getLooper();
//        if(mht.getLooper()==null){
//            Log.d("looper","is null");
//        }
        MyHandler h = new MyHandler(mht.getLooper());
        Message msg = Message.obtain();
        msg.obj = "hello";
        h.sendMessage(msg);
    }

    class MyHandlerThread extends HandlerThread{

        public MyHandlerThread(String name) {
            super(name);
        }
        @Override
        public void run(){
            super.run();

        }
    }
    class MyHandler extends Handler {
        public MyHandler(Looper myLooper) {
            super(myLooper);
        }
        public void handleMessage(Message msg) {
            Log.d("message received", String.valueOf(msg.obj));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_handler_thread, menu);
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
