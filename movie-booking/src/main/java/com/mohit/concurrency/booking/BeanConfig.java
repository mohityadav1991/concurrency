package com.mohit.concurrency.booking;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.concurrent.*;

/**
 * @author mohit@interviewbit.com on 04/09/20
 **/
@ComponentScan("com.mohit.concurrency.booking")
public class BeanConfig {

    @Bean
    public ExecutorService getExecutorService() {
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();
        ExecutorService service = new ThreadPoolExecutor(4,
                10,
                10,
                TimeUnit.SECONDS,
                queue);
        return service;
    }
}
