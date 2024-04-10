package com.example.myproject.repository;

//import org.springframework.data.jpa.repository.JpaRepository;
import com.example.myproject.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {

	Account findByEmail(String email);

	Account findByID(String id); //TODO: ID klein schreiben

	void deleteById(String id);  //TODO: delete Account methode machen
}
