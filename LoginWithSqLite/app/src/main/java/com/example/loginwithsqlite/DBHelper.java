package com.example.loginwithsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "user.db";
    public static final String USER_TABLE_NAME = "userInfo";
    public static final String USER_COLUMN_ID = "id";
    public static final String USER_COLUMN_USERNAME = "name";
    public static final String USER_COLUMN_EMAIL = "email";
    public static final String USER_COLUMN_PASSWORD = "password";
    public static final String USER_COLUMN_PHONE = "phone";
    public static final String USER_COLUMN_CITY = "city";


    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table userInfo" +
                "(id integer primary key AUTOINCREMENT, name text, email text, password text, phone text, city text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS userInfo");
        onCreate(db);
    }

    public boolean insertUserInfo(String Name, String Email, String Password, String Phone, String City) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", Name);
        contentValues.put("email", Email);
        contentValues.put("password", Password);
        contentValues.put("phone", Phone);
        contentValues.put("city", City);
        ///// check empty field ///////////////////

        if (Name.isEmpty() || Email.isEmpty() || Password.isEmpty() || Phone.isEmpty() || City.isEmpty()) {
            //    System.out.println("Empty Data");
            return false;
        }

        Cursor cursor = db.query(USER_TABLE_NAME, new String[]{"email"},
                " email=?", new String[]{Email}, null, null, null);
        int count = cursor.getCount();
        cursor.close();

        if (count <= 0) {
            db.insert("userInfo", null, contentValues);
            //    System.out.println(contentValues);
            close();
            return true;
        } else {
            System.out.println("failed");
            return false;
        }


    }

    public Cursor getUserInfo(String Email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("select * from userInfo where email=?" + Email, null);
        result.moveToFirst();
        return result;
    }

    public boolean updateUserInfo(String Name, String Email, String Password, String Phone, String City) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", Name);
        contentValues.put("email", Email);
        contentValues.put("password", Password);
        contentValues.put("phone", Phone);
        contentValues.put("city", City);
        db.update("userInfo", contentValues, "email = ?", new String[]{Email});
        return true;
    }

    public Integer deleteUserInfo(String Email) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("userInfo",
                "email = ? ",
                new String[]{Email});
    }

    public boolean login(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
//        System.out.println(email);
//        System.out.println(password);
        if (email.isEmpty() && password.isEmpty()) {
            return false;
        } else {
            Cursor cursor = db.query(USER_TABLE_NAME, new String[]{"email", "password"},
                    " email=? and password=?", new String[]{email, password}, null, null, null);
            int count = cursor.getCount();
            cursor.close();
            close();
            if (count > 0) {
                return true;
            } else {
                return false;
            }
        }
    }
}
//    public ArrayList<String> getAllUserInfo() {
//        ArrayList<String> array_list = new ArrayList<String>();
//
//        //hp = new HashMap();
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res =  db.rawQuery( "select * from userInfo", null );
//        res.moveToFirst();
//
//        while(res.isAfterLast() == false){
//            array_list.add(res.getString(res.getColumnIndex(USER_COLUMN_USERNAME)));
//            res.moveToNext();
//        }
//        return array_list;
//    }
//}
