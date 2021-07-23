package com.iflytek.contentprovider.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.iflytek.contentprovider.Book;
import com.iflytek.contentprovider.IBookManager;
import com.iflytek.contentprovider.INewBookAddListener;
import com.iflytek.contentprovider.R;

import java.util.List;

public class BookManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_manager);
        Intent intent = new Intent(this, BookManagerService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mManager != null) {
                    try {
                        mManager.addBook(new Book(4, "艺术探索"));
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private IBookManager mManager;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //注意下面这行代码
            mManager = IBookManager.Stub.asInterface(service);
            try {
                mManager.registerListener(mListener);
                List<Book> bookList = mManager.getBookList();
                Log.e("cyli8", bookList.get(1).bookName);
                Log.e("cyli8", bookList.getClass().getCanonicalName());
                mManager.addBook(new Book(3, "windowphone"));
                bookList = mManager.getBookList();
                Log.e("cyli8", bookList.get(2).bookName);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private INewBookAddListener mListener = new INewBookAddListener.Stub() {
        @Override
        public void onNewBookAdd(Book book) throws RemoteException {
            Log.e("cyli8", "新书到了：" + book.bookName);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mManager != null) {
            try {
                mManager.unRegisterListener(mListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        unbindService(mConnection);
    }
}
