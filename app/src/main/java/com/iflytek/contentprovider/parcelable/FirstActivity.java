package com.iflytek.contentprovider.parcelable;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.iflytek.contentprovider.R;
import com.iflytek.contentprovider.Book;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        User user = new User();
        user.id = 1;
        user.isMale = true;
        user.name = "tom";
        Book book = new Book();
        book.bookName = "艺术探索";
        user.book = book;
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("aaa", user);
        startActivity(intent);
    }
}
