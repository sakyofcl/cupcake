package com.sakeen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CakeListAdapter extends BaseAdapter {
    String[] data;
    Context context;
    LayoutInflater inflater;
    public CakeListAdapter(Context context, String[] data) {
        this.data = data;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.length;
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
        view = inflater.inflate(R.layout.activity_cake_list_view, null);
        TextView cakeName = (TextView) view.findViewById(R.id.cakeName);
        cakeName.setText(this.data[i]);
        return view;
    }
}
