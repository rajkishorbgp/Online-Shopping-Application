package com.rajkishorbgp.onlineshopping;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;


import java.util.ArrayList;

public class HomeDatabase extends SQLiteOpenHelper {
    Context context;

    public HomeDatabase(@Nullable Context context) {
        super(context, "homeDatabase", null, 1);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_QUERY = "CREATE TABLE homeDatabase(id INTEGER PRIMARY KEY AUTOINCREMENT, itemName TEXT, itemDisc TEXT, prise INTEGER, image INTEGER, itemCartColor INTEGER, isCart INTEGER)";
        db.execSQL(CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS homeDatabase");
        onCreate(db);
    }

    public boolean addData(String itemName, String itemDisc, int prise, int image, int itemCartColor, int isCart) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("itemName", itemName);
        contentValues.put("itemDisc", itemDisc);
        contentValues.put("prise", prise);
        contentValues.put("image", image);
        contentValues.put("itemCartColor", itemCartColor);
        contentValues.put("isCart", isCart);
        long l = sqLiteDatabase.insert("homeDatabase", null, contentValues);
        sqLiteDatabase.close();
        return l != -1;
    }

    public ArrayList<ItemClass> getAllDataUser() {
        ArrayList<ItemClass> homeArrayList = new ArrayList<>();
        try {
            SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM homeDatabase", null);
            if (cursor.moveToFirst()) {
                do {
                    String itemName = cursor.getString(1);
                    String itemDisc = cursor.getString(2);
                    int prise = cursor.getInt(3);
                    int image = cursor.getInt(4);
                    int itemCartColor = cursor.getInt(5);
                    int isCart = cursor.getInt(6);
                    homeArrayList.add(new ItemClass(itemName, itemDisc, prise, image, itemCartColor,isCart));
                } while (cursor.moveToNext());
                cursor.close();
            }
        } catch (Exception e) {
            Toast.makeText(context, "Error " + e, Toast.LENGTH_SHORT).show();
        }
        return homeArrayList;
    }

    public boolean updateIsCart(String itemName, int isCart,int itemCartColor) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("isCart", isCart);
        contentValues.put("itemCartColor",itemCartColor);
        String[] whereArgs = {itemName};
        int rowsAffected = sqLiteDatabase.update("homeDatabase", contentValues, "itemName=?", whereArgs);
        sqLiteDatabase.close();
        return rowsAffected > 0;
    }

}