package com.cc.library.domain;

import java.io.Serializable;
import java.util.Set;

/**
 * 图书类型类
 * @author c
 *
 */
public class BookType implements Serializable{

	private Integer typeId; //图书类型编号
	private String typeName;	//图书类型名称
	private Set<Book> books;	//该类书的集合
	
	
	
	public Set<Book> getBooks() {
		return books;
	}
	public void setBooks(Set<Book> books) {
		this.books = books;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	
	public BookType() {
	
	}
	public BookType(Integer typeId, String typeName, Set<Book> books) {
		super();
		this.typeId = typeId;
		this.typeName = typeName;
		this.books = books;
	}


	
	
	
	
	
	
	
}
