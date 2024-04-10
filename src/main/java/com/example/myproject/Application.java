package com.example.myproject;

//import com.example.myproject.controller.AccountController;

import com.example.myproject.repository.AccountRepository;
import com.example.myproject.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableMongoRepositories
//@EnableScheduling
public class Application {

        // @Autowired
        // private static AccountController accountController;
        @Autowired
        protected AccountRepository accountRepository;

        @Autowired
        protected BlogRepository blogRepository;
//        @Autowired
//        protected SessionRepository sessionRepository;

        public static void main(String[] args) {

                SpringApplication.run(Application.class, args);

        }

//        @PostConstruct
//        private void testdata() throws Exception{
////                Blog testBlog = new Blog();
////                testBlog.setTitle("Das ist ein Testblog, erstellt bei der Initialiisierung");
////                this.blogRepository.save(testBlog);
////                System.out.println("\nTestblog erstellt - ID: " + testBlog.getId() + " Titel: " + testBlog.getTitle() + "\n");
//                Blog testDBBlog = blogRepository.findById("660c2bbdccec7149d2c40ee7").orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//                System.out.println("\nTestblog von DB abgerufen - ID: " + testDBBlog.getId() + "\n");
//        }


//        @PostConstruct
//        public void testdata() throws Exception {
//                Account account = new Account();
//                account.setCompany("Google");
//                account.setEmail("admin@admin.com");
//                account.setFirstName("Cesar");
//                account.setLastName("Jaqu");
//                account.setPassword("admin@admin.com");
//                account.setPhoneNumber("123");
//                this.accountRepository.save(account);
//                System.out.println("\nTestaccount erstellt - ID: " + account.getID() + "\n");
//
//        }

}
