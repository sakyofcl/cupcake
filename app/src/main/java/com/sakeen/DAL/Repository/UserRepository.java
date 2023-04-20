package com.sakeen.DAL.Repository;

import android.content.ContentValues;
import android.database.Cursor;

import com.sakeen.DAL.DBManager;
import com.sakeen.DAL.Model.User;

import java.util.ArrayList;

public class UserRepository {
    DBManager DB;

    public UserRepository(DBManager DB) {
        this.DB = DB;
    }

    public void AddUser(User user){
        ContentValues data = new ContentValues();

        data.put("name", user.name);
        data.put("password",user.password);
        data.put("address",user.address);
        data.put("userType",user.userType);
        data.put("phone",user.phone);
        //data.putNull("userId");

        DB.Insert("user", data);
    }

    public User login(User user){
        Cursor cursor = DB.Read(String.format("SELECT * FROM user WHERE name='%s' AND password='%s'; ",user.name, user.password));

        if(cursor != null){
            User data = new User();
            data.name = cursor.getString(cursor.getColumnIndex("name"));
            data.password = cursor.getString(cursor.getColumnIndex("password"));
            data.address = cursor.getString(cursor.getColumnIndex("address"));
            data.userType = cursor.getInt(cursor.getColumnIndex("userType"));
            data.phone = cursor.getInt(cursor.getColumnIndex("phone"));
            data.userId = cursor.getInt(cursor.getColumnIndex("userId"));
            return data;
        }
        return null;
    }
}
