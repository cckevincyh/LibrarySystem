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

	
	
	
	
	
	
	
}
