package com.sakeen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class OrderListAdapter extends BaseAdapter {
    String[] data;
    Context context;
    LayoutInflater inflater;
    public OrderListAdapter(Context context, String[] data) {
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
        view = inflater.inflate(R.layout.activity_order_list_view, null);
        ListView orderItem = (ListView) view.findViewById(R.id.orderItem);
        ArrayAdapter<String> dataAdapter =new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, new String[] {"Sponge Cake x 5 = Rs 150.01 ", "Chiffon Cake x 2 = Rs 250.02", "Baked Flourless Cake x 1 = Rs 300.50"});
        orderItem.setAdapter(dataAdapter);
        return view;
    }
}
