package com.will.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerTask {

	private int count = 0;
	
	@Scheduled(cron="*/6 * * * * ?")
	private void process() {
		System.out.println("this is a scheduler task is running..." + (count++));
	}
	
	@Component
	public class schedulerTask2{
		
		private final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		
		@Scheduled(fixedDelay = 6000)
		public void reportCurrentTime() {
			System.out.println("now: " + sdf.format(new Date()));
		}
	}
	
}


