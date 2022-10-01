package com.example.perpusonline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.perpusonline.Adapter.ItemAdapter;
import com.example.perpusonline.Model.Book;
import com.example.perpusonline.Model.Request;
import com.example.perpusonline.database.databaseHelper;

import java.util.Vector;

public class formMain extends AppCompatActivity {
    RecyclerView rvBooks;
    Vector<Book> books1;
    databaseHelper databaseHelper;
    ItemAdapter itemAdapter;
    Vector<Request> requests2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_main);
        rvBooks = findViewById(R.id.rv_books);
        ItemAdapter itemAdapter = new ItemAdapter (this);
        //database
        databaseHelper = new databaseHelper(this);
        //JSONARRAY
        String link = "https://isys6203-perpus-online.herokuapp.com";
        RequestQueue RQ = Volley.newRequestQueue(this);
        JsonArrayRequest jar = new JsonArrayRequest(
                link,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++ ){
                            try {
                                JSONObject object = response.getJSONObject(i);
                                //show object
                                //Log.d("Object", object.toString());
                                String name = object.getString("name");
                                String author = object.getString("author");
                                String sypnop = object.getString("synopsis");
                                String cover = "https://isys6203-perpus-online.herokuapp.com/"+object.getString("cover");
                                //show message
                                /*
                                Log.d("name", name);
                                Log.d("author", author);
                                Log.d("synopsis", sypnop);
                                Log.d("cover", cover);
                                 */

                                databaseHelper.insertBookData(name,author,cover,sypnop);
                            }catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        itemAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error Volley", error.getMessage());
                    }
                }
        );
        RQ.add(jar);
        //recycler view
        books1 = new Vector<>();
        readBooks();
        itemAdapter.setBooks(books1);
        rvBooks.setAdapter(itemAdapter);
        rvBooks.setLayoutManager(new LinearLayoutManager(formMain.this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuformmain, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.ViewAllReqMenu){
            Intent intent1 = new Intent(formMain.this, allRequestForm.class);
            startActivity(intent1);
        }
        if (item.getItemId() == R.id.LogoutMenu){
            Intent intent = new Intent(formMain.this, MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


    public void readBooks(){
        Cursor cursor = databaseHelper.readBookData();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "Kosong, restart the app", Toast.LENGTH_SHORT).show();
        }
        else while(cursor.moveToNext()){
            Book b1 = new Book(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
            books1.add(b1);
        }

    }

}

