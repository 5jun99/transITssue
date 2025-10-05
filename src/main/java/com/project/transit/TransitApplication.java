package com.project.transit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TransitApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransitApplication.class, args);
    }
}
