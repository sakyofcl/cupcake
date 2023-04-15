package com.sakeen;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.DateFormatSymbols;

public class cakes extends Fragment {
    ListView cakeList;
    String[] data;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.cakes, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cakeList = view.findViewById(R.id.cakeList);
        data = new DateFormatSymbols().getMonths();
        CakeListAdapter cakeListAdapter = new CakeListAdapter(view.getContext(), new String[]{"Pound Cake", "Angel Food Cake", "Baked Flourless Cake", "Chiffon Cake", "Biscuit Cake"});
        cakeList.setAdapter(cakeListAdapter);
    }
}