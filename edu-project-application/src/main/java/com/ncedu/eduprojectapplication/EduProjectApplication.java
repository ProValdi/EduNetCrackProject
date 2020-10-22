package com.ncedu.eduprojectapplication;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = {"com.ncedu.eduprojectapplication", "com.ncedu.eduprojectweb"})
public class EduProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduProjectApplication.class, args);
    }

}
