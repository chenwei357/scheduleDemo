package com.will.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ScheduledFuture;

import com.will.service.ScheduledTaskConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class SchedulerTask {

	private static final Logger logger = LoggerFactory.getLogger(SchedulerTask.class);
	
	@Scheduled(cron="*/6 * * * * ?")
	private void process() {
		ScheduledFuture scheduledFuture = TaskMap.getTaskMap().get("taskExecutor-1");
		if (!ObjectUtils.isEmpty(scheduledFuture) && ScheduledTaskConfig.getCount() > 5) {
			logger.info("cancel task, name={}", "taskExecutor-1");
			scheduledFuture.cancel(true);
			TaskMap.getTaskMap().remove("taskExecutor-1");
		}
	}

}


