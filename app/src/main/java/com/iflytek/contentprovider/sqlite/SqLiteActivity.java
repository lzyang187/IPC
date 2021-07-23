package com.iflytek.contentprovider.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.iflytek.contentprovider.R;

import java.util.ArrayList;
import java.util.List;

public class SqLiteActivity extends AppCompatActivity implements View.OnClickListener {


    private View mCreateBtn;
    private MySqLiteHelper mSqliteHelper;
    private SQLiteDatabase mDb;
    private View mInsertBtn;
    private View mDeleteBtn;
    private View mUpdateBtn;
    private View mSelectBtn;
    private ListView mLv;
    private ArrayAdapter<String> mAdapter;
    private List<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sq_lite);
        mSqliteHelper = new MySqLiteHelper(this);
        //创建数据库
        mDb = mSqliteHelper.getWritableDatabase();
        mCreateBtn = findViewById(R.id.create_btn);
        mCreateBtn.setOnClickListener(this);
        mInsertBtn = findViewById(R.id.insert_btn);
        mInsertBtn.setOnClickListener(this);
        mDeleteBtn = findViewById(R.id.delete_btn);
        mDeleteBtn.setOnClickListener(this);
        mUpdateBtn = findViewById(R.id.uptade_btn);
        mUpdateBtn.setOnClickListener(this);
        mSelectBtn = findViewById(R.id.select_btn);
        mSelectBtn.setOnClickListener(this);
        mLv = findViewById(R.id.lv);
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, mList);
        mLv.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        if (v == mCreateBtn) {
            createTable();
        } else if (v == mInsertBtn) {
            insert();
        } else if (v == mDeleteBtn) {
            delete();
        } else if (v == mUpdateBtn) {
            update();
        } else if (v == mSelectBtn) {
            select();
        }
    }

    private void select() {
        String sql = "select * from person";
        Cursor cursor = SqLiteUtil.rawQuery(mDb, sql, null);
        if (cursor != null) {
            mList.clear();
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex("_id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                int age = cursor.getInt(cursor.getColumnIndex("age"));
                String result = "_id=" + id + "  name=" + name + "  age=" + age;
                mList.add(result);
            }
            mAdapter.notifyDataSetChanged();
        }
    }

    private void update() {
        String sql = "update person set name='john',age=20 where _id=1";
        SqLiteUtil.execSql(mDb, sql);
        select();
    }

    private void delete() {
        String sql = "delete from person where _id='1'";
        SqLiteUtil.execSql(mDb, sql);
        select();
    }

    private void insert() {
        //重复插入主键相同的数据会报错
        String sql = "insert into person values(1,'tom',10)";
        SqLiteUtil.execSql(mDb, sql);
        sql = "insert into person(_id,name) values(2,'jack')";
        SqLiteUtil.execSql(mDb, sql);
        select();
    }

    private void createTable() {
        SQLiteDatabase db = mSqliteHelper.getWritableDatabase();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDb.close();
    }
}
