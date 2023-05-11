package com.example.imagepro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {


    public static final String DBNAME = "login.db";
    public DBHelper(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create Table users(username TEXT primary key,password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists users");
    }

    public Boolean insertData(String UserName,String PassWord) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("username", UserName);
        values.put("password", PassWord);

        Long result = sqLiteDatabase.insert("users", null, values);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public Boolean checkUsrename(String UserNames){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from users where username =?",new String[] {UserNames});
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }
    public Boolean checkUsernamesPasswords(String UsrName,String PassWords){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from users where username = ? and password = ?", new String[]{UsrName,PassWords});
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }
}
