package com.example.myproject.config;

import com.example.myproject.model.Account;
import com.example.myproject.repository.AccountRepository;
import com.example.myproject.service.AccountService;
import com.example.myproject.service.BlogService;
import com.example.myproject.service.MongoDbService;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationInitializer implements CommandLineRunner {

    private final AccountRepository accountRepository;
    private final MongoDbService mongoDbService;
    private final BlogService blogService;
    private final AccountService accountSertvice;
    private static final Logger logger = LogManager.getLogger(ApplicationInitializer.class);

    @Value("${spring.data.mongodb.uri}")
    private String mongoConnection;
    private final String ACCOUNTS_COLLECTION = "account";

    @Autowired
    public ApplicationInitializer(AccountService accountSertvice, BlogService blogService, AccountRepository accountRepository, MongoDbService mongoDbService) {
        this.accountRepository = accountRepository;
        this.mongoDbService = mongoDbService;
        this.blogService = blogService;
        this.accountSertvice = accountSertvice;
    }

//    @PostConstruct
//    public void init() {
////        retrieveAllAccountsAndSaveToRepository();
////        List<Blog> blogs = blogService.getAllBlogs(); //Todo:
////        System.out.println("Autom. geladene Blogs:");
////        for (Blog blog : blogs) {
////            System.out.println("\t" + blog.getTitle());
////        }
//
//    }

    @Override
    public void run(String... args) throws Exception {
        this.blogService.getAllBlogs();
        this.accountSertvice.getAllAccounts();
    }

    public void retrieveAllAccountsAndSaveToRepository() { //TODO: Löschen? wird mit gettAllAccounts ersetzt
        int count = 0;
        // Verbindung zur MongoDB-Datenbank herstellen
        MongoClient mongoClient = mongoDbService.getMongoClient(mongoConnection);
        MongoDatabase database = mongoClient.getDatabase("mywebapp");
        MongoCollection<Document> collection = database.getCollection(ACCOUNTS_COLLECTION);

        // Alle Dokumente in der Collection abrufen
        FindIterable<Document> documents = collection.find();

        // Durch alle Dokumente iterieren und Accounts erstellen und auf dem
        // AccountRepository speichern
        for (Document doc : documents) {
            String accountId = doc.get("_id").toString();
            String email = doc.getString("email");
            String password = doc.getString("password");
            String firstName = doc.getString("firstName");
            String lastName = doc.getString("lastName");
            String phoneNumber = doc.getString("phoneNumber");

            Account account = new Account();
            account.setID(accountId);
            account.setEmail(email);
            account.setPassword(password);
            account.setFirstName(firstName);
            account.setLastName(lastName);
            account.setPhoneNumber(phoneNumber);

//            accountRepository.save(account);
            logger.info("Account " + account.getEmail() + " retrieved from Database");
            count += 1;
        }
        logger.info(count + " Account(s) successfully retrieved from Database");

        // MongoDB-Verbindung schließen
        mongoClient.close();
    }
}
