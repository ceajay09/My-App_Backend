package com.example.myproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * The entry point of the MyProject Spring Boot application.
 * Configures Spring Boot application with MongoDB repositories and enables asynchronous operations.
 */
@SpringBootApplication
@EnableAsync
@EnableMongoRepositories
@EnableScheduling
public class Application {

        public static void main(String[] args) {
                SpringApplication.run(Application.class, args);
        }
}
