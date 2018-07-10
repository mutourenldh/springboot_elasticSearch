package com.haoge.elasticSearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync//允许开启基于注解的异步任务
@EnableScheduling//允许开启基于注解的定时任务
public class SpringbootElasticSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootElasticSearchApplication.class, args);
	}
}
