package com.sakeen.DAL.Repository;

import android.content.ContentValues;
import android.database.Cursor;

import com.sakeen.DAL.DBManager;
import com.sakeen.DAL.Model.Cake;
import com.sakeen.DAL.Model.Category;

import java.util.ArrayList;

public class CakeRepository {
    DBManager DB;
    CategoryRepository categoryRepository;

    public CakeRepository(DBManager DB, CategoryRepository categoryRepository) {
        this.DB = DB;
        this.categoryRepository = categoryRepository;
    }

    public void addCake(Cake cake){
        ContentValues data = new ContentValues();

        data.put("name", cake.name);
        data.put("price",cake.price);
        data.put("image",cake.image);
        data.put("categoryId",cake.categoryId);

        DB.Insert("cake", data);
    }

    public void updateCake(Cake cake){
        ContentValues data = new ContentValues();

        data.put("name", cake.name);
        data.put("price",cake.price);
        data.put("image",cake.image);
        data.put("categoryId",cake.categoryId);

        DB.Update("cake","cakeId="+cake.cakeId, data);
    }


    public ArrayList<Cake> getAllCakes(){
        Cursor data = this.DB.Read("SELECT * FROM cake");
        ArrayList<Cake> cakes= new ArrayList<Cake>();

        if(data != null){
            do{
                Cake d =new Cake();

                d.name = data.getString(data.getColumnIndex("name"));
                d.price = data.getDouble(data.getColumnIndex("price"));
                d.image = data.getString(data.getColumnIndex("image"));
                d.cakeId = data.getInt(data.getColumnIndex("cakeId"));
                d.categoryId = data.getInt(data.getColumnIndex("categoryId"));
                d.categoryName = this.categoryRepository.getCategoryName(d.categoryId);

                cakes.add(d);
            }while(data.moveToNext());
        }
        return cakes;
    }

    public Cake getCake(int cakeId){
        ArrayList<Cake> cakes = getAllCakes();
        for (int i=0; i < cakes.size(); i++){
            if(cakes.get(i).cakeId==cakeId){
                return cakes.get(i);
            }
        }
        return null;
    }

}
