package com.iflytek.contentprovider.bindservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyBindService extends Service {
    private static final String TAG = "MyBindService";

    private MyDownloadBinder myDownloadBinder = new MyDownloadBinder();

    class MyDownloadBinder extends Binder {
        public void startDownload() {
            Log.e(TAG, "startDownload:");
        }

        public int getProgress() {
            Log.e(TAG, "getProgress:");
            return 0;
        }
    }

    public MyBindService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate:");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand:");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind:");
        return myDownloadBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "onUnbind:");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy:");
        super.onDestroy();
    }
}
