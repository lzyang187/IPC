package com.iflytek.contentprovider.bindservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.iflytek.contentprovider.R;

public class BindActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "BindActivity";
    private View mBindBtn;
    private View mUnBindBtn;

    private MyBindService.MyDownloadBinder mDownloadBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind);
        mBindBtn = findViewById(R.id.bind_btn);
        mUnBindBtn = findViewById(R.id.unbind_btn);
        mBindBtn.setOnClickListener(this);
        mUnBindBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mBindBtn) {
            bind();
        } else if (v == mUnBindBtn) {
            unBind();
        }
    }

    private void unBind() {
        unbindService(mConnection);
    }

    private void bind() {
        Intent intent = new Intent(this, MyBindService.class);
        bindService(intent, mConnection, BIND_AUTO_CREATE);
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e(TAG, "onServiceConnected:");
            mDownloadBinder = (MyBindService.MyDownloadBinder) service;
            mDownloadBinder.startDownload();
            mDownloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(TAG, "onServiceDisconnected:");
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mConnection);
    }
}
