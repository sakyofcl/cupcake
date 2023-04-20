package com.sakeen.DAL.Repository;

import android.database.Cursor;

import com.sakeen.DAL.DBManager;
import com.sakeen.DAL.Model.Category;

import java.util.ArrayList;

public class CategoryRepository {
    DBManager DB;
    String tableName = "category";

    public CategoryRepository(DBManager DB) {
        this.DB = DB;
    }

    public ArrayList<Category>  getAllCategories(){
        Cursor data = this.DB.Read("SELECT * FROM "+tableName);
        ArrayList<Category> categories= new ArrayList<Category>();

        do{
            categories.add(new Category(data.getInt(data.getColumnIndex("categoryId")), data.getString(data.getColumnIndex("name"))));
        }while(data.moveToNext());

        return categories;
    }

    public ArrayList<String> getAllCategoryNames(){
        ArrayList<String> category = new ArrayList<String>();
        ArrayList<Category> data = this.getAllCategories();

        for (int i=0; i < data.size() ;i++){
            category.add(data.get(i).name);
        }

        return category;
    }

    public int getCategoryId(String name){
        Cursor cursor = DB.Read(String.format("SELECT * FROM category WHERE name='%s'; ",name));
        if(cursor != null){
            return cursor.getInt(cursor.getColumnIndex("categoryId"));
        }
        return 0;
    }

    public String getCategoryName(int categoryId){
        Cursor cursor = DB.Read(String.format("SELECT * FROM category WHERE categoryId=%d; ",categoryId));
        if(cursor != null){
            return cursor.getString(cursor.getColumnIndex("name"));
        }
        return "";
    }
}
