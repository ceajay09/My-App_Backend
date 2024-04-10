package com.example.myproject.controller;

import com.example.myproject.model.Account;
import com.example.myproject.model.Blog;
import com.example.myproject.repository.AccountRepository;
import com.example.myproject.repository.BlogRepository;
import com.example.myproject.service.AccountService;
import com.example.myproject.service.BlogService;
import com.example.myproject.service.PDFService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

// @RequestMapping("api/")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@PropertySource("classpath:application.properties")
public class RequestHandler {

    private static final Logger logger = LogManager.getLogger();
    private final AccountRepository accountRepository;
    private final AccountService accountService;
    private final BlogService blogService;
    private final BlogRepository blogRepository;
    private PDFService pdfService;

    public RequestHandler(AccountRepository accountRepository,
                          AccountService accountService,
                          BlogService blogService,
                          BlogRepository blogRepository,
                          PDFService pdfService) {
        this.accountRepository = accountRepository;
        this.accountService = accountService;
        this.blogService = blogService;
        this.blogRepository = blogRepository;
        this.pdfService = pdfService;
    }

    // Erstellen eines neuen Benutzers
    @PostMapping(path = "/api/register", produces = "application/json")
    public ResponseEntity<String> registerUser(@RequestBody Map<String, String> request) {
        return accountService.registerUser(request);
    }

    @PostMapping(path = "/api/login", produces = "application/json")
    public ResponseEntity<String> loginUser(@RequestBody Map<String, String> request) {
        return accountService.loginUser(request);
    }

    @PostMapping(path = "/api/logout", produces = "application/json")
    public ResponseEntity<?> logoutUser(@RequestHeader("Authorization") String token) {
        return accountService.logoutUser(token);
    }


    @GetMapping(path = "api/account/{id}", produces = "application/json") //TODO: in getaccount umbenennen
    public Account getAccountById(@PathVariable String id) { //TODO: Logger?
        return accountRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping(path = "/api/dashboard", produces = "application/json")
    public ResponseEntity<?> getAccountInfoByToken(Authentication authentication) {
        return accountService.getAccountInfoByToken(authentication);
    }

    @GetMapping(path = "api/blog/{id}", produces = "application/json") //TODO: Logger? //TODO: in getblog umbenennen
    public Blog getBlogById(@PathVariable String id) {
        return blogRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping(path = "api/getBlogs", produces = "application/json") //TODO: Logger? TODO: Errorhandling
    public List<Blog> getBlogs() {
        logger.info("getBlogs aufgerufen"); //TODO
        return blogRepository.findAll();
    }

    @GetMapping(path = "api/downloadPDF/{fileName}", produces = "application/json")
    public ResponseEntity<Resource> downloadPDF(@PathVariable String fileName) throws MalformedURLException {
        return pdfService.downloadPDF(fileName);
    }

}
