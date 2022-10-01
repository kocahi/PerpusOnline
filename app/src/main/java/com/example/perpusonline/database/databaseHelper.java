package com.example.perpusonline.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class databaseHelper extends SQLiteOpenHelper {

    Context context;
    public static String DATABASE_NAME = "Library.db";
    public static int DATABASE_VERSION = 1;

    public static String TABLE_BOOKS = "books";
    public static String BOOKS_ID ="id";
    public static String BOOKS_NAME = "name";
    public static String BOOKS_AUTHOR ="author";
    public static String BOOKS_COVER="cover";
    public static String BOOKS_SYNOPSIS="sypnopsis";

    public static String TABLE_REQ = "requests";
    public static String REQ_ID1 ="id";
    public static String REQ_BK1 ="book_id";
    public static String REQ_REQ1 = "requester_id";
    public static String REQ_REC1 ="receiver_id";
    public static String REQ_LAT1 ="latitude";
    public static String REQ_LONG1 ="longtitude";

    public static String TABLE_USERS = "users";
    public static String USERS_ID = "id";
    public static String USERS_EMAIL = "email";
    public static String USERS_PASSWORD = "password";
    public static String USERS_PHONE = "phone_number";
    public static String USERS_DOB = "date_of_birth";
    public databaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String queryBooks=" CREATE TABLE "+TABLE_BOOKS
                +" ( "+BOOKS_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + BOOKS_NAME + " VARCHAR(255), "
                + BOOKS_AUTHOR + " VARCHAR(255), "
                + BOOKS_COVER + " VARCHAR(255), "
                + BOOKS_SYNOPSIS + " TEXT);";

        String queryUsers=" CREATE TABLE "+TABLE_USERS
                +" ( "+USERS_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USERS_EMAIL + " VARCHAR(255), "
                + USERS_PASSWORD + " VARCHAR(255), "
                + USERS_PHONE + " VARCHAR(50), "
                + USERS_DOB + " VARCHAR(50));";

        String queryReq=" CREATE TABLE "+TABLE_REQ
                +" ( "+REQ_ID1+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + REQ_BK1 + " INTEGER REFERENCES books(id), "
                + REQ_REQ1 + " INTEGER REFERENCES users(id), "
                + REQ_REC1 + " INTEGER, "
                + REQ_LAT1 + " FLOAT, "
                + REQ_LONG1 + " FLOAT);";
        sqLiteDatabase.execSQL(queryUsers);
        sqLiteDatabase.execSQL(queryBooks);
        sqLiteDatabase.execSQL(queryReq);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS users");
    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS books");
    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS requests");
    onCreate(sqLiteDatabase);
    }


    //USER DATABASE FUNCTIONS
    public void insertUserData(String email, String password, String phone_number, String date_of_birth){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USERS_EMAIL, email);
        cv.put(USERS_PASSWORD, password);
        cv.put(USERS_DOB, date_of_birth);
        cv.put(USERS_PHONE, phone_number);
        long result = sqLiteDatabase.insert(TABLE_USERS, null, cv);
        if (result == -1){
            Toast.makeText(context,"failed", Toast.LENGTH_SHORT).show();
        }
    }

    public Boolean checkUser (String username, String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_USERS+" WHERE "+USERS_EMAIL+" = ? AND "+USERS_PASSWORD+" = ?", new String[] {username,password});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Cursor readBookUsers(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_USERS;
        Cursor cursor = null;
        if (db != null){
            cursor=db.rawQuery(query,null);
        }
        return cursor;
    }

    //BOOKS DATABASE FUNCTIONS
    public void insertBookData(String name, String author, String cover, String synopsis){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(BOOKS_NAME,name);
        cv.put(BOOKS_AUTHOR,author);
        cv.put(BOOKS_COVER,cover);
        cv.put(BOOKS_SYNOPSIS,synopsis);
        long result = sqLiteDatabase.insert(TABLE_BOOKS,null,cv);
        if (result == -1){
            Toast.makeText(context,"failed", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readBookData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_BOOKS;
        Cursor cursor = null;
        if (db != null){
            cursor=db.rawQuery(query,null);
        }
        return cursor;
    }

    public Cursor readBookId(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT id FROM "+TABLE_BOOKS+" WHERE name = '"+name+"'";
        Cursor cursor = null;
        if (db != null){
            cursor=db.rawQuery(query,null);
        }
        return cursor;
    }


    public Cursor readBookAllBasedOnId(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_BOOKS+" WHERE name = '"+name+"'";
        Log.d("query", name);
        Cursor cursor = null;
        if (db != null){
            cursor=db.rawQuery(query,null);
        }
        return cursor;
    }

    //REQUESTER DATABASE FUNCTIONS
    public void insertRequestData(Integer book_id, Integer requester_id, Integer receiver_id, float latitude, float longitude){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(REQ_BK1, book_id);
        cv.put(REQ_REQ1, requester_id);
        cv.put(REQ_REC1, receiver_id);
        cv.put(REQ_LAT1, latitude);
        cv.put(REQ_LONG1, longitude);
        long result = sqLiteDatabase.insert(TABLE_REQ,null,cv);
        if (result == -1){
            Toast.makeText(context,"failed", Toast.LENGTH_SHORT).show();
        }
    }


    public Cursor readRequesterData(){
        SQLiteDatabase db = this.getReadableDatabase();
        /*
        String query = "SELECT * FROM "  + TABLE_BOOKS + " JOIN " + TABLE_REQ +
                " ON " + TABLE_BOOKS + " . " + BOOKS_ID + " = " + TABLE_REQ + " . "
                + REQ_BK1;

         */
        String query = "SELECT * FROM "+TABLE_REQ;
                Cursor cursor = null;
        if (db != null){
            cursor=db.rawQuery(query,null);
        }
        return cursor;
    }



}
