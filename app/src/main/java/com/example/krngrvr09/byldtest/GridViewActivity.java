package com.example.krngrvr09.byldtest;

import android.app.ActionBar;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;


public class GridViewActivity extends ActionBarActivity {
    private ImageAdapter mAdapter;
    // Will crash if images not downscaled.
    public final static Integer[] imageResIds = new Integer[] {
            R.drawable.image, R.drawable.image2, R.drawable.image3,
            R.drawable.image4, R.drawable.image5, R.drawable.image6,
            R.drawable.image7, R.drawable.image8, R.drawable.image9};

    // Will not crash even if the images are not downscaled.
//    public final static Integer[] imageResIds = new Integer[] {
//            R.drawable.tick, R.drawable.tick, R.drawable.tick,
//            R.drawable.tick, R.drawable.tick, R.drawable.tick,
//            R.drawable.tick, R.drawable.tick, R.drawable.tick};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);
        mAdapter = new ImageAdapter(this);
        GridView gv = (GridView) findViewById(R.id.grid_view);
        gv.setAdapter(mAdapter);
    }


    private class ImageAdapter extends BaseAdapter {
        private final Context mContext;

        public ImageAdapter(Context context) {
            super();
            mContext = context;
        }

        @Override
        public int getCount() {
            return imageResIds.length;
        }

        @Override
        public Object getItem(int position) {
            return imageResIds[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
        class ViewHolder {
            ImageView img;

            ViewHolder(View v) {
                img = (ImageView) v.findViewById(R.id.single_image);
            }
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            ImageView imageView;
            View row = convertView;
            ViewHolder holder = null;
            if (row == null) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.single_view_image, container, false);
                holder = new ViewHolder(row);
                row.setTag(holder);
            } else {
                holder = (ViewHolder) row.getTag();
            }

            holder.img.setImageResource(imageResIds[position]); // Load image into ImageView
            return row;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_grid_view, menu);
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
