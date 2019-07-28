package com.will.service;

import com.will.utils.TaskMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
public class ScheduledTaskConfig implements CommandLineRunner {

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTaskConfig.class);

    private static AtomicInteger i = new AtomicInteger(0);

    public static int getCount() {
        return i.get();
    }

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(10);
        threadPoolTaskScheduler.setThreadNamePrefix("taskExecutor-");
        threadPoolTaskScheduler.setWaitForTasksToCompleteOnShutdown(true);
        threadPoolTaskScheduler.setAwaitTerminationSeconds(60);
        return threadPoolTaskScheduler;
    }

    @Override
    public void run(String... strings) throws Exception {
        logger.info("添加任务，将任务添加至TaskMap");
        ScheduledFuture scheduledFuture = threadPoolTaskScheduler.schedule(
                () -> {
                    logger.info("ThreadName = {}, task running... count={}", Thread.currentThread().getName(), i.incrementAndGet());
                },
                new CronTrigger("0/5 * * * * *"));
        TaskMap.getTaskMap().put("taskExecutor-1", scheduledFuture);
    }
}
