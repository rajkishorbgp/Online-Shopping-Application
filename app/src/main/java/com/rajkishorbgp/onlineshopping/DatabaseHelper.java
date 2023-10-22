package com.rajkishorbgp.onlineshopping;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.rajkishorbgp.onlineshopping.myclass.userDetailes;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, "onlineShopping", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_QUERY = "CREATE TABLE register(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT, password TEXT, mobileNumber TEXT, userAddress TEXT, dateOfBirthday TEXT)";
        db.execSQL(CREATE_TABLE_QUERY);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS register");
        onCreate(db);
    }
    public boolean registrationUser(String name, String email, String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("email",email);
        contentValues.put("password",password);
        long l= sqLiteDatabase.insert("register",null ,contentValues);
        sqLiteDatabase.close();
        return l != 0;
    }
    public boolean loginUser(String email, String password) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM register WHERE email=? AND password=?", new String[]{email, password});
        boolean result = cursor.moveToFirst();
        cursor.close();
        return result;
    }
    public boolean updateDetailes(String email, String name, String mobile, String address, String birthday){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("mobileNumber", mobile);
        contentValues.put("userAddress", address);
        contentValues.put("dateOfBirthday", birthday);
        String[] whereArgs = {email};
        int rowsAffected = sqLiteDatabase.update("register", contentValues, "email=?", whereArgs);
        sqLiteDatabase.close();
        return rowsAffected > 0;
    }

    public userDetailes getUser(String email1) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM register WHERE email=?", new String[]{email1});
        if (cursor.moveToFirst()) {
            String name = cursor.getString(1);
            String email = cursor.getString(2);
            String mobileNumber = cursor.getString(4);
            String userAddress = cursor.getString(5);
            String dateOfBirthday = cursor.getString(6);
            cursor.close();
            return new userDetailes(name, email, mobileNumber, userAddress, dateOfBirthday);
        }
        return new userDetailes("r", "", "", "", "");
    }
}