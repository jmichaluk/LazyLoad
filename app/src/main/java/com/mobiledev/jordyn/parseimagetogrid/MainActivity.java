package com.mobiledev.jordyn.parseimagetogrid;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import com.parse.ParseException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    GridView gridview;
    List<ParseObject> ob;
    ProgressDialog mProgressDialog;
    GridViewAdapter adapter;
    private List<CatList> catarraylist = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new RemoteDataTask().execute();
    }

    private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(MainActivity.this);
            mProgressDialog.setTitle("Cats");
            mProgressDialog.setMessage("Loading the cats.");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            catarraylist = new ArrayList<CatList>();
            try {
                ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Cats");
                query.orderByAscending("position");
                ob = query.find();
                for (ParseObject cats : ob) {
                    ParseFile image = (ParseFile) cats.get("images");
                    CatList map = new CatList();
                    map.setCat(image.getUrl());
                    catarraylist.add(map);
                }
            } catch (ParseException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            gridview = (GridView) findViewById(R.id.gridview);
            adapter = new GridViewAdapter(MainActivity.this, catarraylist);
            gridview.setAdapter(adapter);
            mProgressDialog.dismiss();
        }
    }
}