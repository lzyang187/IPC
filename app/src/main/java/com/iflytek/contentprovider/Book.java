package com.iflytek.contentprovider;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author: cyli8
 * @date: 2018/1/29 20:44
 */

public class Book implements Parcelable {

    public String bookName;
    public int bookId;

    public Book() {

    }

    public Book(int id, String name) {
        this.bookId = id;
        this.bookName = name;
    }

    protected Book(Parcel in) {
        bookName = in.readString();
        bookId = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(bookName);
        dest.writeInt(bookId);
    }

    @Override
    public int describeContents() {
        return 0;
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
}
