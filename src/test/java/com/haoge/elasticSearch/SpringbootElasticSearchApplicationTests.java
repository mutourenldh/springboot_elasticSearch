package com.haoge.elasticSearch;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import com.haoge.elasticSearch.bean.Article;
import com.haoge.elasticSearch.bean.Book;
import com.haoge.elasticSearch.repository.BookRepository;

import io.searchbox.client.JestClient;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootElasticSearchApplicationTests {
	//
	@Autowired
	JestClient jestClient;
	@Autowired
	BookRepository bookRepository;
	@Autowired
	ElasticsearchTemplate elasticsearchTemplate;

	@Test
	public void contextLoads() {
		Article article = new Article();
		article.setAuthor("lidonghao");
		article.setContent("hello world");
		article.setId(1);
		article.setTitle("first");
		//构建一个索引功能（即向ES中索引一个文档），将article放在索引haoge,类型news之下
		Index build = new Index.Builder(article).index("haoge").type("news").build();
		try {
			DocumentResult execute = jestClient.execute(build);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//执行完成之后，我们就向ES中索引了一个文档，测试地址http://47.105.103.45:9200/haoge/news/1
		//执行之后，我们可以看到我们刚才索引的文档数据
	}
	//测试搜索
	@Test
	public void testSearch() {
		String json="{\n" + 
				"    \"query\" : {\n" + 
				"        \"match\" : {\n" + 
				"            \"content\" : \"hello\"\n" + 
				"        }\n" + 
				"    }\n" + 
				"}";
		//在haoge索引下，查询类型为news，字段中content有hello的文档
		Search build = new Search.Builder(json).addIndex("haoge").addType("news").build();
		try {
			SearchResult execute = jestClient.execute(build);
			System.out.println(execute.getJsonString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//测试搜索
	@Test
	public void testRepository() {
//		Book book = new Book();
//		book.setId(1);
//		book.setBookName("西游记");
//		book.setAuthor("李东浩");
//		bookRepository.index(book);
		
		List<Book> findByBookNameLike = bookRepository.findByBookNameLike("游");
		for (Book book : findByBookNameLike) {
			System.out.println(book);
		}
	}
	
	//测试搜索
	@Test
	public void testTemplate() {
		Book book = new Book();
		book.setId(2);
		book.setBookName("东游记");
		book.setAuthor("李西浩");
		
		IndexQuery indexQuery = new IndexQueryBuilder().withId(book.getId().toString()).withObject(book).build();
	    elasticsearchTemplate.index(indexQuery);
	}
}
