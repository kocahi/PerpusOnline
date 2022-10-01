package com.example.perpusonline.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.perpusonline.Model.Book;
import com.example.perpusonline.R;
import com.example.perpusonline.allRequestForm;
import com.example.perpusonline.bookDetail;
import com.example.perpusonline.formMain;

import org.w3c.dom.Text;

import java.util.Vector;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private Context context;
    private Vector<Book> books;

    public ItemAdapter(Context context) {
        this.context = context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setBooks(Vector<Book> books) {
        this.books = books;
    }

    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_books, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, int position) {
        holder.BooksTitle.setText(books.get(position).getName());
        holder.BooksAuthor.setText(books.get(position).getAuthor());
        holder.BooksSynopsis.setText(books.get(position).getSynopsis());
        Glide.with(context)
                .load(books.get(position).getCover())
                .into(holder.BooksCover);
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), bookDetail.class);
            intent.putExtra("book", books.get(position));
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return books.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView BooksTitle, BooksAuthor, BooksSynopsis;
        ImageView BooksCover;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            BooksAuthor = itemView.findViewById(R.id.listBooksAuthor);
            BooksTitle = itemView.findViewById(R.id.listBooksTitle);
            BooksSynopsis = itemView.findViewById(R.id.listBooksSynopsis);
            BooksCover = itemView.findViewById(R.id.imageListBook);
        }
    }

}
