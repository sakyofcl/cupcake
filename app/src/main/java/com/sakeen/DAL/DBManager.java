package com.sakeen.DAL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private Context context;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase DB;

    public DBManager(Context context) {
        this.context = context;
        this.databaseHelper = new DatabaseHelper(context);
        this.DB = databaseHelper.getWritableDatabase();
    }

    public void Insert(String TableName, ContentValues contentValues){
        DB.insert(TableName, null, contentValues);
    }

    public void Update(String TableName, String whereClause, ContentValues contentValues){
        DB.update(TableName, contentValues, whereClause, null);
    }
    public void Delete(String TableName, String whereClause){
        DB.delete(TableName, whereClause, null);
    }
    public Cursor Read(String q){
        Cursor cursor = DB.rawQuery(q, null);

        if(cursor != null && cursor.moveToFirst()){
            return cursor;
        }
        return null;
    }
}
