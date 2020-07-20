package com.exadel.placebook;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EntityScan(basePackages = {"com.exadel.placebook"})
//@ComponentScan(basePackages = {"com.exadel.placebook"})
public class SpringApplicationStarter {
    public static void main(String[] args) {
        SpringApplication.run(SpringApplicationStarter.class, args);
    }

    @Bean
    public ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }
}
