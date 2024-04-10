//package com.example.myproject.service;
//
//import com.example.myproject.repository.SessionRepository;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//
//@Service
//public class SessionCleanupService { //*implements Runnable**/ {
//
//    private final SessionRepository sessionRepository;
//    private static final Logger logger = LogManager.getLogger(SessionCleanupService.class);
//
//    @Autowired
//    public SessionCleanupService(SessionRepository sessionRepository) {
//        this.sessionRepository = sessionRepository;
//    }
//
////    @Scheduled(fixedRate = 60000) // Each hour 3600000
////    public void cleanupExpiredSessions() {
////        // Implementiere die Logik, um abgelaufene Sessions zu finden und zu löschen
////        // Beispiel:
////        logger.info("run cleanupExpiredSessions()");
////        sessionRepository.deleteByExpiryTimeBefore(LocalDateTime.now());
////    }
////    @Override
////    public void run() {
////        for (int i = 0; i < 86400; i++) {
////            try {
////                Thread.sleep(1000);
////            } catch (InterruptedException e) {
////                // TODO Auto-generated catch block
////                e.printStackTrace();
////            }
////        }
////        this.sessionRepository.deleteById(this.id);
////    }
//}
