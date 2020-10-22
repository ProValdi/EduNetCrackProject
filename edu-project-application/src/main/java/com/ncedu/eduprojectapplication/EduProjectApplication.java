package com.ncedu.eduprojectapplication;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Slf4j
// entry point for spring boot app
@SpringBootApplication
@ComponentScan(basePackages = {"com.ncedu.eduprojectapplication", "com.ncedu.eduprojectweb"})
public class EduProjectApplication {

    private static final Logger logger = LoggerFactory.getLogger(EduProjectApplication.class.getName());

    public static void main(String[] args) {
        //SpringApplication.run(EduProjectApplication.class, args);

        useF(value ->{
            return "kd";
        });
        log.info("dhdhhd");

    }

    private static void useF(Function<Boolean, String> f) {
        System.out.println(f.apply(true));
    }

}

