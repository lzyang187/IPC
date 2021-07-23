// INewBookAddListener.aidl
package com.iflytek.contentprovider;

// Declare any non-default types here with import statements
import com.iflytek.contentprovider.Book;
interface INewBookAddListener {
    void onNewBookAdd(in Book book);
}
