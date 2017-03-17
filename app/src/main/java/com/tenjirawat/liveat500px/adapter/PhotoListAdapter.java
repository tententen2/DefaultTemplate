package com.tenjirawat.liveat500px.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tenjirawat.liveat500px.view.PhotoListItem;

/**
 * Created by ten30 on 3/17/2017.
 */

public class PhotoListAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        return 10000;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        return new PhotoListItem(viewGroup.getContext());
    }
}
