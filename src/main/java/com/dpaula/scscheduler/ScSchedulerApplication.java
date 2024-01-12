package com.dpaula.scscheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ScSchedulerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScSchedulerApplication.class, args);
    }

}
