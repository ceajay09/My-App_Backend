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

@Service
public class MongoDbService {

    private static final Logger logger = LogManager.getLogger(MongoDbService.class);

    @Value("${spring.data.mongodb.uri}")
    private String mongoConnection;

    public MongoClient getMongoClient(String mongoConnection) {
        MongoClient mongoClient = null;
//        String connectionString = mongoConnection + "@mywebapp.pjxdzvf.mongodb.net/?retryWrites=true&w=majority";
        String connectionString = mongoConnection;

        // Create a new client and connect to the server
        try {
            ConnectionString connString = new ConnectionString(connectionString);
            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(connString)
                    .build();
            // Send a ping to confirm a successful connection
            mongoClient = MongoClients.create(settings);
            // MongoDatabase database = mongoClient.getDatabase("MongoDB");
            // database.runCommand(new Document("ping", 1));
            // System.out.println("Pinged your deployment. You successfully connected to
            // MongoDB!");
        } catch (MongoException e) {
            logger.warn("Connection to MongoDB Failed " + e);
            e.printStackTrace();
        }
        return mongoClient;
    }
}


