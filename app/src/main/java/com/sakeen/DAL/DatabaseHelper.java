package com.sakeen.DAL;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context, "cupcake", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE cake ( cakeId INTEGER PRIMARY KEY AUTOINCREMENT, name varchar(250) NOT NULL, categoryId int(11) DEFAULT NULL, price decimal(10,0) NOT NULL, image text DEFAULT NULL)");
        sqLiteDatabase.execSQL("CREATE TABLE category ( categoryId INTEGER PRIMARY KEY AUTOINCREMENT, name varchar(250) NOT NULL)");
        sqLiteDatabase.execSQL("CREATE TABLE orderitem ( orderItemId INTEGER PRIMARY KEY AUTOINCREMENT, price decimal(10,0) NOT NULL, qty int(11) NOT NULL, orderId int(11) NOT NULL, itemName int(11) NOT NULL, cakeId int(11) NOT NULL)" );
        sqLiteDatabase.execSQL("CREATE TABLE orders ( orderId INTEGER PRIMARY KEY AUTOINCREMENT, totalPrice decimal(10,0) NOT NULL, userId int(11) NOT NULL, orderDate timestamp NOT NULL DEFAULT current_timestamp, status int(11) NOT NULL)");
        sqLiteDatabase.execSQL("CREATE TABLE user ( userId INTEGER PRIMARY KEY AUTOINCREMENT, name varchar(200) NOT NULL, password text NOT NULL, phone int(11) DEFAULT NULL, userType int(11) NOT NULL, address text DEFAULT NULL)");

        insertDefaultCategory(sqLiteDatabase);
        insertAdminData(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS cake");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS category");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS orderitem");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS orders");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS user");
        onCreate(sqLiteDatabase);
    }

    private void insertDefaultCategory(SQLiteDatabase db){
        String[] category = new String[]{"Fruit cakes", "Vanilla cakes", "Sponge cakes", "Cupcakes", "Chocolate cakes"};
        for (int i=0; i< category.length; i++){
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", category[i]);
            db.insert("category", null, contentValues);
        }
    }

    private void insertAdminData(SQLiteDatabase db){
        ContentValues data = new ContentValues();

        data.put("name", "admin");
        data.put("password","admin");
        data.put("address","404");
        data.put("userType",1);
        data.put("phone",770856672);

        db.insert("user", null, data);
    }
}
