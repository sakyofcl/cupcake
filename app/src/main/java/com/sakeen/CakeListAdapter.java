package com.sakeen;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sakeen.DAL.Model.Cake;

import java.util.ArrayList;

public class CakeListAdapter extends BaseAdapter {
    ArrayList<Cake> data;
    Context context;
    LayoutInflater inflater;
    AppContext _app;
    public CakeListAdapter(Context context, ArrayList<Cake> data) {
        this.data = data;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this._app = new AppContext(this.context.getSharedPreferences(AppContext.name, 0));
    }

    @Override
    public int getCount() {
        return data.size();
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
        TextView cakeCategory = (TextView) view.findViewById(R.id.cakeCategory);
        TextView cakePrice = (TextView) view.findViewById(R.id.cakePrice);
        Button editBtn = (Button) view.findViewById(R.id.cake_edit_btn);
        Button removeBtn = (Button) view.findViewById(R.id.cake_remove_btn);

        if(!_app.isAdmin()){
            editBtn.setVisibility(View.INVISIBLE);
            removeBtn.setVisibility(View.INVISIBLE);
        }

        cakeName.setText(this.data.get(i).name);
        cakeCategory.setText(this.data.get(i).categoryName);
        cakePrice.setText("Rs "+String.format("%.2f", this.data.get(i).price));

        editBtn.setTag(this.data.get(i).cakeId);

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onEditeCake(view);
            }
        });

        return view;
    }

    private void onEditeCake(View view){
        Intent i = new Intent(view.getContext(), CakeFrom.class);
        i.putExtra("cakeId",(int)view.getTag());
        view.getContext().startActivity(i);
    }
}
