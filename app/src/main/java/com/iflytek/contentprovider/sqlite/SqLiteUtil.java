package com.iflytek.contentprovider.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

/**
 * @author: cyli8
 * @date: 2018/1/21 20:50
 */

public class SqLiteUtil {
    public static void execSql(SQLiteDatabase db, String sql) {
        if (db != null && !TextUtils.isEmpty(sql)) {
            db.execSQL(sql);
        }
    }

    public static Cursor rawQuery(SQLiteDatabase db, String sql, String[] selectionArgs) {
        Cursor cursor = null;
        if (db != null && !TextUtils.isEmpty(sql)) {
            cursor = db.rawQuery(sql, selectionArgs);
        }
        return cursor;
    }

}
