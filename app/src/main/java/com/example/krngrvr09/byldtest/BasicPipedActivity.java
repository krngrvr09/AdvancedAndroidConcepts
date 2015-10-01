package com.example.krngrvr09.byldtest;

import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;


public class BasicPipedActivity extends ActionBarActivity {
    PipedReader r;
    PipedWriter w;
    private EditText editText;

    private Thread workerThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        r = new PipedReader(32);
        w = new PipedWriter();
        try {
            w.connect(r);
        } catch (IOException e) {
            e.printStackTrace();
        }
        editText = (EditText) findViewById(R.id.edit_text);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Only handle addition of characters
//                Log.d("textchanged","charseq: "+charSequence+" start: "+start+" before: "+before+" count: "+count+" output: "+charSequence.subSequence(start, start+1).toString());
                if(count > before) {
                    // Write the last entered character to the pipe
                    try {
                        w.write(charSequence.subSequence(start,start+1).toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        workerThread = new Thread(new TextHandlerTask(r));
        workerThread.start();



    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            r.close();
            w.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private class TextHandlerTask implements Runnable {

        private final PipedReader reader;

        public TextHandlerTask(PipedReader reader){
            this.reader = reader;
        }

        @Override
        public void run() {
            int i;
            while(true){
                // Block background thread and wait for data to process.
                try {
                    while((i = reader.read()) != -1){
                        char c = (char) i;
                        // Add text processing logic here
                        Log.d("value", String.valueOf(c));
                        longRunningOperation();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void longRunningOperation(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
