package com.huasheng.dingding.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class ThreadPoolExecutorConfig {

    public static final int CORE_POOL_SIZE = 5;
    public static final int MAX_POOL_SIZE = 10 ;
    public static final int QUEUE_SIZE = 200;
    public static final int KEEP_ALIVE = 60;



    @Bean(name = "taskExecutor")
    public TaskExecutor taskExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(CORE_POOL_SIZE);
        taskExecutor.setMaxPoolSize(MAX_POOL_SIZE);
        taskExecutor.setQueueCapacity(QUEUE_SIZE);
        taskExecutor.setKeepAliveSeconds(KEEP_ALIVE);
        taskExecutor.setThreadNamePrefix("huaSheng-");
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        taskExecutor.setAwaitTerminationSeconds(KEEP_ALIVE);
        return taskExecutor;
    }

}
