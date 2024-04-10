//package com.example.myproject.repository;
//
////import org.springframework.data.jpa.repository.JpaRepository;
//import com.example.myproject.model.Session;
//import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.stereotype.Repository;
//
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//@Repository
//public interface SessionRepository extends MongoRepository<Session, String> {
//
//	Optional<Session> findById(String id);
//
//	public Session findByToken(String token);
//
//	public void deleteByExpiryTimeBefore(LocalDateTime dateTime);
//
//}
