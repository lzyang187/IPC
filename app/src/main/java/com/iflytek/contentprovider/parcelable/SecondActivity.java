package com.iflytek.contentprovider.parcelable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.iflytek.contentprovider.R;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        User user = (User) getIntent().getParcelableExtra("aaa");
        Log.e("cyli8", user.id + " " + user.name + " " + user.isMale + " " + user.book.bookName);
    }
}
