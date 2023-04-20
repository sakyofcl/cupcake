package com.sakeen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.sakeen.DAL.Model.Cake;
import com.sakeen.DAL.Model.User;
import com.sakeen.DAL.UnitOfWork;

public class CakeFrom extends AppCompatActivity {
    Spinner categoryId;
    EditText price, name;
    Button saveCakeBtn;
    UnitOfWork uow;
    boolean isEdit = false;
    int cakeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cake_from);
        uow = new UnitOfWork(this);
        Bundle extras = getIntent().getExtras();


        categoryId = findViewById(R.id.categoryId);
        price = findViewById(R.id.cakePrice);
        name = findViewById(R.id.cakeName);
        saveCakeBtn = findViewById(R.id.saveCakeBtn);

        ArrayAdapter categoryList = new ArrayAdapter(CakeFrom.this, android.R.layout.simple_spinner_item, uow.category.getAllCategoryNames() );

        categoryList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        categoryId.setAdapter(categoryList);

        if (extras != null) {
            this.isEdit= true;
            this.cakeId = extras.getInt("cakeId");
            Cake cakeInfo = uow.cake.getCake(this.cakeId);
            price.setText(String.format("%.2f", cakeInfo.price));
            name.setText(cakeInfo.name);
            categoryId.setSelection(categoryList.getPosition(cakeInfo.categoryName));
        }

        saveCakeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cake newCake = new Cake();

                newCake.name = name.getText().toString();
                newCake.price =  Float.parseFloat(price.getText().toString());
                newCake.categoryId = uow.category.getCategoryId(categoryId.getSelectedItem().toString());
                newCake.image = "dummy.jpg";

                if(isEdit){
                    newCake.cakeId = cakeId;
                    uow.cake.updateCake(newCake);
                }
                else{
                    uow.cake.addCake(newCake);
                }


                Intent i = new Intent(CakeFrom.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
}