package com.example.perpusonline.Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

public class Request implements Parcelable {
    private int book_id, requester_id, receiver_id;
    private float latitude, longitude;


    protected Request(Parcel in) {
        book_id = in.readInt();
        requester_id = in.readInt();
        receiver_id = in.readInt();
        latitude = in.readFloat();
        longitude = in.readFloat();
    }

    public static final Creator<Request> CREATOR = new Creator<Request>() {
        @Override
        public Request createFromParcel(Parcel in) {
            return new Request(in);
        }

        @Override
        public Request[] newArray(int size) {
            return new Request[size];
        }
    };

    public Integer getBook_id() {
        return book_id;
    }

    public Integer getRequester_id() {
        return requester_id;
    }

    public Integer getReceiver_id() {
        return receiver_id;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public void setRequester_id(int requester_id) {
        this.requester_id = requester_id;
    }

    public void setReceiver_id(int receiver_id) {
        this.receiver_id = receiver_id;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public Request(int book_id, int requester_id, int receiver_id, float latitude, float longitude) {
        this.book_id = book_id;
        this.requester_id = requester_id;
        this.receiver_id = receiver_id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(book_id);
        dest.writeInt(requester_id);
        dest.writeInt(receiver_id);
        dest.writeFloat(latitude);
        dest.writeFloat(longitude);
    }
}
