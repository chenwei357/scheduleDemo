package com.will.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

public class TaskMap {

    private static Map<String, ScheduledFuture> taskMap = new ConcurrentHashMap<>();

    public static Map<String, ScheduledFuture> getTaskMap() {
        return taskMap;
    }

}
