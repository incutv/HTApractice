package com.example.performancecache.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ScheduleTask {

     private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(ScheduleTask.class);


    @Scheduled(cron = "0 * * * * ?")
    public void runTask() throws InterruptedException{
        System.out.println("task1 : "+ LocalDateTime.now());
    }

    @Scheduled(cron = "0 * * * * ?")
    @SchedulerLock(name = "ScheduledTask_run")
    public void runTask2() throws InterruptedException{
        System.out.println("task2 : " + LocalDateTime.now());
    }


}
