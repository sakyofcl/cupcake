package com.sakeen;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.text.DateFormatSymbols;

public class orders extends Fragment {
    ListView orderList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.orders, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        orderList = view.findViewById(R.id.orderList);
        OrderListAdapter orderListAdapter = new OrderListAdapter(view.getContext(), new String[]{"Pound Cake", "Angel Food Cake", "Baked Flourless Cake", "Chiffon Cake", "Biscuit Cake"});
        orderList.setAdapter(orderListAdapter);
    }
}