package com.example.perpusonline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.perpusonline.Model.Book;
import com.example.perpusonline.database.databaseHelper;

import java.util.Vector;

public class bookDetail extends AppCompatActivity {
    private TextView detailName, detailAuthor, detailSypnosis;
    private Button requestButton;
    private ImageView imageBookDetail;
    databaseHelper db;
    int idBook;
    String bookName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        detailName = findViewById(R.id.title_detail);
        detailAuthor = findViewById(R.id.author_detail);
        detailSypnosis = findViewById(R.id.sypnosis_detail);
        requestButton = findViewById(R.id.buttonRequestBookDetail);
        imageBookDetail = findViewById(R.id.imageFormDetail);
        //taro book dari FormMain
        Book book = getIntent().getParcelableExtra("book");
        //database
        db = new databaseHelper(this);
        //baca buku title
        bookName = book.getName();
        storeBooksId();
        //set text
        detailName.setText(book.getName());
        detailAuthor.setText(book.getAuthor());
        detailSypnosis.setText(book.getSynopsis());
        Glide.with(this)
                .load(book.getCover())
                .into(imageBookDetail);

        requestButton.setOnClickListener(view -> {
            db.insertRequestData(idBook,1,1,30,30);
            Intent intent = new Intent(bookDetail.this, formMain.class);
            startActivity(intent);
        });

    }
    public void storeBooksId(){
        Cursor cursor = db.readBookId(bookName);
        if (cursor.getCount() == 0){
            Toast.makeText(this, "Kosong", Toast.LENGTH_SHORT).show();
        }
        else while(cursor.moveToNext()){
            idBook = cursor.getInt(0);
        }
    }
}
