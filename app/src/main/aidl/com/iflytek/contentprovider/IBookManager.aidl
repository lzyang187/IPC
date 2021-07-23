// IBookManager.aidl
package com.iflytek.contentprovider;
import com.iflytek.contentprovider.Book;
// Declare any non-default types here with import statements
import com.iflytek.contentprovider.INewBookAddListener;
interface IBookManager {
    List<Book> getBookList();
    void addBook(in Book book);
    void registerListener(in INewBookAddListener listener);
    void unRegisterListener(in INewBookAddListener listener);
}
