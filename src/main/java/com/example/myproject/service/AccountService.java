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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * Service for managing user accounts, including registration, login, and logout functionalities,
 * as well as retrieving account information.
 */
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final TokenUtil tokenUtil;
    private static final Logger logger = LogManager.getLogger(AccountService.class);
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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
            Account account = new Account();
            account.setEmail(email);
            account.setPassword(password); //TODO: passwordEncoder.encode(password)
            account.setFirstName(firstName);
            account.setLastName(lastName);
            account.setCompany(company);
            account.setPhoneNumber(phoneNumber);

            this.accountRepository.save(account); //TODO: Necessary?

            String accountID = this.accountRepository.findByEmail(account.getEmail()).getID();

            logger.info(account.getEmail() + " created / " + "ID = " + accountID);

            String responseMessage = account.getEmail() + " registered successfully";

            logger.info("Registration response: {}", responseMessage);

            return ResponseEntity.ok().body("{\"status\":\"success\",\"message\":\"Account registered successfully\"}");
        } else {
            String responseMessage = email + " registered already";

            logger.info("Registration response: {}", responseMessage);

            return ResponseEntity.status(HttpStatus.CONFLICT).body("Account already exists");
        }
    }

    public ResponseEntity<Map<String, Object>> logoutUser(String token) {
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
            if (account.getPassword().equals(password)) { //TODO: use a secure method to verify hashed passwords
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

            logger.warn("Login response: Incorrect credentials for email={}", email);

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect login credentials");
        }
    }

    public ResponseEntity<Account> getAccountInfoByToken(Authentication authentication) {
        String email = authentication.getName();
        Account account = accountRepository.findByEmail(email);
        if (account == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else{
            return ResponseEntity.ok(account);
        }
}

    public List<Account> getAllAccounts() {
        int count = 0;
        List<Account> accounts = accountRepository.findAll();
        for (Account account : accounts) {
            logger.info("Retrieved Account from DB:" + "\t" + account.getEmail());
            count += 1;
        }
        logger.info(count + " Account(s) successfully retrieved from Database");
        return accounts;
    }
}
