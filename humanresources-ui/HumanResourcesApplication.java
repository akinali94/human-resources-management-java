package com.humanresources.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.humanresources")
@EntityScan(basePackages = "com.humanresources.model")
@EnableJpaRepositories(basePackages = "com.humanresources.repository")
public class HumanResourcesApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(HumanResourcesApplication.class, args);
    }
}