package com.haoge.elasticSearch.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.haoge.elasticSearch.bean.Book;

public interface BookRepository extends ElasticsearchRepository<Book, Integer>{

	public List<Book> findByBookNameLike(String bookName);
}
