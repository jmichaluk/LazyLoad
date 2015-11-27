package com.mobiledev.jordyn.parseimagetogrid;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jordyn on 2015-11-27.
 */
public class GridViewAdapter extends BaseAdapter{

Context context;
    LayoutInflater inflater;
    ImageLoader imageLoader;
    private List<CatList> catarraylist = null;
    private ArrayList<CatList> arraylist;

    public GridViewAdapter(Context context, List<CatList> catarraylist) {
        this.context = context;
        this.catarraylist = catarraylist;
        inflater = LayoutInflater.from(context);
        this.arraylist = new ArrayList<CatList>();
        this.arraylist.addAll(catarraylist);
        imageLoader = new ImageLoader(context);
    }

    public class ViewHolder {
        ImageView cat;
    }

    @Override
    public int getCount() {
        return catarraylist.size();
    }

    @Override
    public Object getItem(int position) {
        return catarraylist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.gridview_item, null);
            holder.cat = (ImageView) view.findViewById(R.id.cat);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        imageLoader.DisplayImage(catarraylist.get(position).getCat(), holder.cat);
        return view;
    }
}
