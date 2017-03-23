package com.cc.library.dao;

import java.util.List;

import com.cc.library.domain.Book;
import com.cc.library.domain.BookType;
import com.cc.library.domain.PageBean;

public interface BookDao {

	public List<BookType> getAllBookTypes();

	public PageBean<Book> findBookByPage(int pageCode, int pageSize);

	public boolean addBook(Book book);

	public BookType getBookType(BookType bookType);

	public Book getBookById(Book book);

	public Book updateBookInfo(Book updateBook);


}
