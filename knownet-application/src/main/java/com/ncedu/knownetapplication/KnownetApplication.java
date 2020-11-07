package com.ncedu.knownetapplication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Slf4j
@SpringBootApplication
@EnableJpaRepositories(basePackages="com.ncedu.knownetimpl.repository")
@ComponentScan(basePackages = {"com.ncedu.knownetimpl.repository", "com.ncedu.knownetimpl", "com.ncedu.knownetweb", "com.ncedu.knownetapplication"})
public class KnownetApplication {

    public static void main(String[] args) {
        SpringApplication.run(KnownetApplication.class, args);
    }

}
