package com.example.myproject.service;

import com.example.myproject.model.Account;
import com.example.myproject.repository.AccountRepository;
import com.example.myproject.util.TokenUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final TokenUtil tokenUtil;
    private static final Logger logger = LogManager.getLogger(AccountService.class);

    @Autowired
    public AccountService(AccountRepository accountRepository, TokenUtil tokenUtil) {
        this.accountRepository = accountRepository;
        this.tokenUtil = tokenUtil;
    }

    public ResponseEntity<String> registerUser(Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");
        String firstName = request.get("firstName");
        String lastName = request.get("lastName");
        String company = request.get("company");
        String phoneNumber = request.get("phoneNumber");

        logger.info("Received registration request: email={}, firstName={}, lastName={}, company={}, phoneNumber={}",
                email, firstName, lastName, company, phoneNumber);

        if (this.accountRepository.findByEmail(email) == null) {
            // Account noch nicht vorhanden..
            Account account = new Account();
            account.setEmail(email);
            account.setPassword(password);
            account.setFirstName(firstName);
            account.setLastName(lastName);
            account.setCompany(company);
            account.setPhoneNumber(phoneNumber);

            this.accountRepository.save(account); //TODO: Ist das nötig?

            String accountID = this.accountRepository.findByEmail(account.getEmail()).getID();

            logger.info(account.getEmail() + " created / " + "ID = " + accountID);

            String responseMessage = account.getEmail() + " registered successfully";
            logger.info("Registration response: {}", responseMessage);

            return ResponseEntity.ok().body("{\"status\":\"success\",\"message\":\"Account registered successfully\"}");

        } else {
            String responseMessage = email + " registered already";
            logger.info("Registration response: {}", responseMessage);

            // Hier wird der HTTP-Statuscode "409" und die Nachricht "User already exists"
            // zurückgegeben, dass der Benutzer bereits registriert ist und ein Konflikt
            // aufgetreten ist.
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Account already exists");
        }
    }

    public ResponseEntity<?> logoutUser(String token) {
        logger.info("Received logout request from email={}", tokenUtil.getAccountFromToken(token).getEmail());

        Map<String, Object> responseObject = new HashMap<>();
        responseObject.put("status", "success");
        responseObject.put("message", "Logout successful");
        return ResponseEntity.ok(responseObject);
    }

    public ResponseEntity<String> loginUser(Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");

        logger.info("Received login request: email={}",
                email);

        Account account = this.accountRepository.findByEmail(email);

        if (account != null) {
            if (account.getPassword().equals(password)) {
                // Passwort korrekt und Account vorhanden

                String responseMessage = account.getEmail() + " Correct login credentials";
                logger.info("Login response: {}", responseMessage);

                String token = TokenService.createToken(account.getEmail());

                Map<String, Object> responseObject = new HashMap<>();
                responseObject.put("status", "success");
                responseObject.put("message", "Login correct");
                responseObject.put("token", token);
                return ResponseEntity.ok().body(responseObject.toString());
            } else {
                String responseMessage = email + " Incorrect login credentials";
                logger.info("Login response: {}", responseMessage);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect login credentials");
            }
        } else {
            String responseMessage = email + " Incorrect login credentials";
            logger.info("Login response: {}", responseMessage); //TODO: warn?
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect login credentials");
        }
    }

//    public ResponseEntity<?> getAccountInfoFromToken(HttpServletRequest request) {
//        // Token aus dem Authorization-Header der Anfrage erhalten
//        String token = request.getHeader("Authorization");
//
//        // Token validieren (z.B. mit einer JWT-Bibliothek)
//        if (TokenService.isValidToken(token)) {
//            // TokenService ist gültig, Daten zurückgeben
//            Account account = tokenUtil.getAccountFromToken(token); // Annahme: Methode, um Account-Daten aus dem Token zu
//            // extrahieren
//            if (account != null) {
//                // Account-Daten erfolgreich abgerufen, als JSON-Response zurückgeben
//                JSONObject responseObject = new JSONObject();
//                responseObject.put("status", "success");
//                responseObject.put("company", account.getCompany());
//                responseObject.put("email", account.getEmail());
//                responseObject.put("firstName", account.getFirstName());
//                responseObject.put("ID", account.getID());
//                responseObject.put("lastName", account.getLastName());
//                responseObject.put("password", account.getPassword());
//                responseObject.put("phoneNumber", account.getPhoneNumber());
//                responseObject.put("account", account); // Account-Daten im Response-Objekt speichern
//                logger.info(account.getID() + " transfered to Frontend");
//                return ResponseEntity.ok().body(responseObject.toString());
//            }
//        } else {
//            logger.warn("getAccountFromToken: Token invalid:" + token);
//        }
//        // Token ist ungültig, Fehlermeldung zurückgeben
//        JSONObject responseObject = new JSONObject();
//        responseObject.put("status", "error");
//        responseObject.put("message", "Invalid token");
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseObject.toString());
//    }

    public ResponseEntity<?> getAccountInfoByToken(Authentication authentication) {
        String email = authentication.getName();
        Account account = accountRepository.findByEmail(email);
        if (account == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else{
            return ResponseEntity.ok(account);
        }
}

    public List<Account> getAllAccounts() { //Ladet alle blogs
        int count = 0;
        List<Account> accounts = accountRepository.findAll();
        for (Account account : accounts) {
            logger.info("Retrieved Account from DB:" + "\t" + account.getEmail());
            count += 1;
        }
        logger.info(count + " Account(s) successfully retrieved from Database");
        return accounts;
    }

    //TODO: Wird nicht gebraucht. Wird automatisch bei erstellung eines accounts gespeichert
//    public void saveAccountToDatabase(String accountID) {
//        Account account = accountRepository.findByID(accountID);
//
//        try {
//            MongoClient mongoClient = mongoDbService.getMongoClient(this.mongoConnection);
//            MongoDatabase database = mongoClient.getDatabase("MongoDB");
//            MongoCollection<Document> collection = database.getCollection(ACCOUNTS_COLLECTION);
//
//            // Account-Daten in ein MongoDB-Dokument umwandeln
//            Document accountDoc = new Document();
//            accountDoc.append("accountID", accountID)
//                    .append("email", account.getEmail())
//                    .append("password", account.getPassword())
//                    .append("firstName", account.getFirstName())
//                    .append("lastName", account.getLastName())
//                    .append("phoneNumber", account.getLastName());
//
//            // Dokument in der MongoDB speichern
//            collection.insertOne(accountDoc);
//            logger.info(account.getID() + " saved in Database");
//            mongoClient.close();
//        } catch (Exception e) {
//            logger.warn("Verbindung mit MongoDB fehlgeschlagen " + e);
//        }
//
//    }

}
