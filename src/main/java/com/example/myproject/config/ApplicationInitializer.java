package com.example.myproject.config;

import com.example.myproject.service.AccountService;
import com.example.myproject.service.BlogService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

/**
 * Initializes the application by preloading necessary data.
 * Ensures that blog and account services are ready for use once the application starts.
 */
@Component
public class ApplicationInitializer implements CommandLineRunner {

    private final BlogService blogService;
    private final AccountService accountService;
    private static final Logger logger = LogManager.getLogger(ApplicationInitializer.class);
    private final MongoTemplate mongoTemplate;

    @Autowired
    public ApplicationInitializer(AccountService accountService, BlogService blogService, MongoTemplate mongoTemplate) {
        this.blogService = blogService;
        this.accountService = accountService;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Initializing application data...");
        try {
            Document commandResult = mongoTemplate.executeCommand("{ ping: 1 }");
            logger.info("MongoDB Ping Response: " + commandResult.toString());
        } catch (Exception e) {
            logger.error("Failed to connect to MongoDB", e);
        }
        this.blogService.getAllBlogs();
        this.accountService.getAllAccounts();
        logger.info("Application data initialized successfully.");
    }
}
