package com.example.perpusonline.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    String name, author, synopsis, cover;

    public Book(String name, String author, String cover, String synopsis ) {
        this.name = name;
        this.author = author;
        this.synopsis = synopsis;
        this.cover = cover;
    }

    protected Book(Parcel in) {
        name = in.readString();
        author = in.readString();
        synopsis = in.readString();
        cover = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(author);
        parcel.writeString(synopsis);
        parcel.writeString(cover);
    }
}
