package com.example.perpusonline.Adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.perpusonline.Model.Book;
import com.example.perpusonline.Model.Request;
import com.example.perpusonline.R;
import com.example.perpusonline.RequestDetailForm;
import com.example.perpusonline.allRequestForm;
import com.example.perpusonline.bookDetail;

import java.util.Vector;

public class ItemAdapterRequestForm extends RecyclerView.Adapter<ItemAdapterRequestForm.ViewHolder> {
    private Context context;
    private Vector<Request> Requests;
    private Vector<Book> books;


    public void setBooks(Vector<Book> books) {
        this.books = books;
    }

    public void setRequests(Vector<Request> requests) { Requests = requests;
    }

    public ItemAdapterRequestForm(Context context) {this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view1 = LayoutInflater.from(context).inflate(R.layout.activity_viewallrequest, parent, false);
        return new ViewHolder(view1);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.RTitle.setText(books.get(position).getName());
        holder.RAuthor.setText(books.get(position).getAuthor());
        Glide.with(context)
                .load(books.get(position).getCover())
                .into(holder.RCover);
        holder.REceiver.setText(Requests.get(position).getRequester_id().toString());
        holder.REmail.setText(Requests.get(position).getReceiver_id().toString());
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), RequestDetailForm.class);
            intent.putExtra("request", Requests.get(position));
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return Requests.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView RCover;
        TextView RTitle, RAuthor, REmail, REceiver;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            RCover = itemView.findViewById(R.id.imageListBookReq);
            RTitle = itemView.findViewById(R.id.listBooksTitle);
            RAuthor = itemView.findViewById(R.id.listBooksAuthor);
            REmail = itemView.findViewById(R.id.listBooksRequester);
            REceiver = itemView.findViewById(R.id.listBooksRec);
        }

    }
}





