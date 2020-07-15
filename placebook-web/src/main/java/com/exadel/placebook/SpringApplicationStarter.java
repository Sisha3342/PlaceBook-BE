package com.exadel.placebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.exadel.placebook"})
//@ComponentScan(basePackages = {"com.exadel.placebook"})
public class SpringApplicationStarter {
    public static void main(String[] args) {
        SpringApplication.run(SpringApplicationStarter.class, args);
    }
}
