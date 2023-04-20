package com.sakeen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.sakeen.DAL.Model.Cake;
import com.sakeen.DAL.UnitOfWork;

import java.text.DateFormatSymbols;
import java.util.List;

public class cakes extends Fragment {
    ListView cakeList;
    String[] data;
    UnitOfWork uow;
    AppContext _app;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.cakes, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this._app = new AppContext(view.getContext().getSharedPreferences(AppContext.name,0));
        this.uow = new UnitOfWork(view.getContext());

        cakeList = view.findViewById(R.id.cakeList);
        Button cake_create_btn = view.findViewById(R.id.cake_create_btn);

        if(!_app.isAdmin()){
            cake_create_btn.setVisibility(View.INVISIBLE);
        }

        cake_create_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), CakeFrom.class));
            }
        });


        data = new DateFormatSymbols().getMonths();
        CakeListAdapter cakeListAdapter = new CakeListAdapter(view.getContext(), this.uow.cake.getAllCakes());
        cakeList.setAdapter(cakeListAdapter);
    }
}