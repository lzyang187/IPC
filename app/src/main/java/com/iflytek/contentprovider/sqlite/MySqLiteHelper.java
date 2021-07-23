package com.iflytek.contentprovider.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * sq语句：
 * 1、创建表的语句：
 * create table 表名(字段名称 数据类型 约束， 字段名称 数据类型 约束 ....)
 * create table person(_id Integer primary key, name varchar(10), age Integer not null)
 * 2、删除表的语句：
 * drop table 表名
 * drop table person
 * 3、插入数据
 * insert into 表名(字段,字段) values(值1, 值2,...)
 * insert into person (_id,age) values(1,20)
 * insert into person values(1, "tom", 20)
 * 4、修改数据
 * update 表名 set 字段=新值 where 修改的条件
 * update person set name="john",age=10 where _id=1
 * 5、删除数据
 * delete from 表名 where 删除的条件
 * delete from person where _id=1
 * 6、查询语句
 * selete 字段名 from 表名 where 查询的条件 group by 分组的字段名 having 筛选的条件 order by 排序字段
 * selete * from person where _id=1
 * selete * from person where _id=1 and age>10
 * selete * from person where name like "%小%" 查询person表中名字中间含有小的数据
 * selete * from person where age between 10 and 20
 *
 * @author: cyli8
 * @date: 2018/1/21 20:18
 */

public class MySqLiteHelper extends SQLiteOpenHelper {
    private static final String TAG = "MySqLiteHelper";
    public static final String DB_NAME = "info.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "person";


    public MySqLiteHelper(Context context) {
        this(context, DB_NAME, null, DB_VERSION);
    }

    /**
     * @param name    创建数据库的名称
     * @param factory 游标工厂
     * @param version 创建数据库的版本 >=1
     */
    public MySqLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * 数据库被创建时回调
     *
     * @param db 数据库对象
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e(TAG, "onCreate");
        //在数据库被创建时创建表
        String sql = "create table person(_id Integer primary key,name varchar(10),age Integer)";
        db.execSQL(sql);
    }

    /**
     * 数据库被更新时回调
     *
     * @param db         数据库对象
     * @param oldVersion 旧版本号
     * @param newVersion 新版本号
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.e(TAG, "onUpgrade");
    }

    /**
     * 当数据库被打开时回调
     *
     * @param db 数据库对象
     */
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
}
