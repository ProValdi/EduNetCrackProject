package com.ncedu.knownetapplication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = {"com.ncedu"})
public class KnownetApplication {

    public static void main(String[] args) {
        SpringApplication.run(KnownetApplication.class, args);
    }

}
