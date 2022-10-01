package com.example.perpusonline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.perpusonline.Model.Book;
import com.example.perpusonline.Model.Request;
import com.example.perpusonline.database.databaseHelper;

import java.util.Vector;

public class RequestDetailForm extends AppCompatActivity {
    private ImageView imageReq;
    private TextView titleReq, authorReq, emailReq, receiverReq, sypReq, smsReq;
    private Button accReq;
    databaseHelper db;
    String bookId;
    private Vector<Book> books;
    //kurang peta
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_detail_form);
        imageReq = findViewById(R.id.image_request);
        titleReq = findViewById(R.id.title_request);
        authorReq = findViewById(R.id.author_request);
        emailReq = findViewById(R.id.requester_email);
        receiverReq = findViewById(R.id.receiver_email);
        sypReq = findViewById(R.id.sypnosis_detail);
        accReq = findViewById(R.id.accept_req);
        smsReq = findViewById(R.id.sms_req);
        db = new databaseHelper(this);
        books = new Vector<>();
        //set Text dari allRequestForm

        //taro requests dari allReqForm
        Request request = getIntent().getParcelableExtra("request");
        bookId = request.getBook_id().toString();
        getBookDetails();

        titleReq.setText(request.getBook_id().toString());



        accReq.setOnClickListener(v ->{
            Intent intent = new Intent(RequestDetailForm.this, allRequestForm.class);
            startActivity(intent);
        });
        smsReq.setOnClickListener(v -> {
            Intent intent = new Intent(RequestDetailForm.this, SMSform.class);
            startActivity(intent);
        });


    }

    void getBookDetails(){
        Cursor cursor = db.readBookAllBasedOnId(bookId);
        if (cursor.getCount() == 0){
            Toast.makeText(this, "Kosong", Toast.LENGTH_SHORT).show();
        }
        else while(cursor.moveToNext()){
            Book b1 = new Book(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
            books.add(b1);

        }
    }
}