package com.example.myproject.service;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Optional;

/**
 * Service class for managing MongoDB connections.
 * Provides functionality to create and configure a MongoClient instance.
 */

@Service
public class MongoDbService {

    private static final Logger logger = LogManager.getLogger(MongoDbService.class);

    @Value("${spring.data.mongodb.uri}")
    private String mongoConnection;

    /**
     * Creates and returns a MongoClient instance using the configured MongoDB URI.
     *
     * @return A MongoClient connected to the specified MongoDB instance. Returns null if the connection fails.
     */
    public Optional<MongoClient> getMongoClient(String mongoConnection) {
        MongoClient mongoClient = null;

        // Create a new client and connect to the server
        try {
            ConnectionString connString = new ConnectionString(mongoConnection);
            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(connString)
                    .build();
            mongoClient = MongoClients.create(settings);
        } catch (MongoException e) {
            logger.warn("Connection to MongoDB Failed", e);
        }
        return Optional.ofNullable(mongoClient);
    }
}


