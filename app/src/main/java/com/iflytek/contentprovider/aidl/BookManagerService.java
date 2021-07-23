package com.iflytek.contentprovider.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;

import com.iflytek.contentprovider.Book;
import com.iflytek.contentprovider.IBookManager;
import com.iflytek.contentprovider.INewBookAddListener;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class BookManagerService extends Service {
    public BookManagerService() {
    }

    CopyOnWriteArrayList<Book> mList = new CopyOnWriteArrayList<>();
    //    CopyOnWriteArrayList<INewBookAddListener> mListenerList = new CopyOnWriteArrayList<>();
    //注意下面这行
    RemoteCallbackList<INewBookAddListener> mListenerList = new RemoteCallbackList<>();

    private Binder mBinder = new IBookManager.Stub() {
        @Override
        public List<Book> getBookList() throws RemoteException {
            return mList;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            mList.add(book);
            int size = mListenerList.beginBroadcast();
            for (int i = 0; i < size; i++) {
                INewBookAddListener broadcastItem = mListenerList.getBroadcastItem(i);
                if (broadcastItem != null) {
                    broadcastItem.onNewBookAdd(book);
                }
            }
            mListenerList.finishBroadcast();
//            int size = mListenerList.size();
//            for (int i = 0; i < size; i++) {
//                mListenerList.get(i).onNewBookAdd(book);
//            }
        }

        @Override
        public void registerListener(INewBookAddListener listener) throws RemoteException {
//            if (!mListenerList.contains(listener)) {
//                mListenerList.add(listener);
//            }
            mListenerList.register(listener);
        }

        @Override
        public void unRegisterListener(INewBookAddListener listener) throws RemoteException {
//            if (mListenerList.contains(listener)) {
//                mListenerList.remove(listener);
//            }
            mListenerList.unregister(listener);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        mList.add(new Book(1, "Android"));
        mList.add(new Book(2, "ios"));
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
