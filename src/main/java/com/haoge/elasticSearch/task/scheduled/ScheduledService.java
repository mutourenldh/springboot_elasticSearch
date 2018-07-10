package com.haoge.elasticSearch.task.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledService {
	/**
	 * second(秒), minute(分), hour(时), day of month(日), month(月),day of week(周)
	 */
	@Scheduled(cron="0 * * * * MON-FRI")//周一到周五的每秒都执行
	public void hello() {
		System.out.println("hello...");
	}
}
