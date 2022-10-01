package com.example.perpusonline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.perpusonline.Adapter.ItemAdapterRequestForm;
import com.example.perpusonline.Model.Book;
import com.example.perpusonline.Model.Request;
import com.example.perpusonline.database.databaseHelper;

import java.util.Arrays;
import java.util.Vector;

public class allRequestForm extends AppCompatActivity {
    private static Integer bookId;
    RecyclerView rvBooks1;
    Vector<Request> requests;
    databaseHelper db;
    Vector<Book> books;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_request_form);
        rvBooks1 = findViewById(R.id.rv_requestForm);
        requests = new Vector<>();
        books = new Vector<>();
        db = new databaseHelper(this);
        storeReq();
        storeAllReq();
        ItemAdapterRequestForm itemAdapter = new ItemAdapterRequestForm (this);
        itemAdapter.setRequests(requests);
        itemAdapter.setBooks(books);
        rvBooks1.setAdapter(itemAdapter);
        rvBooks1.setLayoutManager(new LinearLayoutManager(allRequestForm.this));
    }
    public void storeReq(){
        Cursor cursor = db.readRequesterData();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "Kosong", Toast.LENGTH_SHORT).show();
        }
        else while(cursor.moveToNext()){
            Request req = new Request(cursor.getInt(1), cursor.getInt(2),cursor.getInt(3),cursor.getFloat(4), cursor.getFloat(5));
            requests.add(req);
            Log.d("id", String.valueOf(cursor.getInt(0)));
            Log.d("buku", String.valueOf(cursor.getInt(1)));
            Log.d("rec", String.valueOf(cursor.getInt(2)));
            Log.d("req", String.valueOf(cursor.getInt(3)));
            Log.d("lat", String.valueOf(cursor.getInt(4)));
            Log.d("long", String.valueOf(cursor.getInt(5)));
        }
    }
    public void storeAllReq(){
        Cursor cursor = db.readBookData();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "Kosong", Toast.LENGTH_SHORT).show();
        }
        else while(cursor.moveToNext()){
            Book b1 = new Book(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
            books.add(b1);
        }
    }
}