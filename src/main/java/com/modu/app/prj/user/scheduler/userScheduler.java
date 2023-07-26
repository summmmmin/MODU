package com.modu.app.prj.user.scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class userScheduler {
    public static void main(String[] args) {
        SpringApplication.run(userScheduler.class, args);
    }
    
    
}
