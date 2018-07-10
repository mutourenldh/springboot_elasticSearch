package com.haoge.elasticSearch.bean;

import org.springframework.data.elasticsearch.annotations.Document;
//标注这个文档存储的索引位置和类型
@Document(indexName="haoge",type="book")
public class Book {
	//标注这个字段为主键
	private Integer id;
	private String bookName;
	private String author;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", bookName=" + bookName + ", author=" + author + "]";
	}

}
