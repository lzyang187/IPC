package com.iflytek.contentprovider.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

import com.iflytek.contentprovider.Book;

/**
 * @author: cyli8
 * @date: 2018/1/29 20:43
 */

public class User implements Parcelable {
    public int id;
    public String name;
    public boolean isMale;
    public Book book;

    public User() {
    }

    protected User(Parcel in) {
        id = in.readInt();
        name = in.readString();
        isMale = in.readByte() != 0;
        book = in.readParcelable(Thread.currentThread().getContextClassLoader());
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeByte((byte) (isMale ? 1 : 0));
        dest.writeParcelable(book, 0);
    }
}
