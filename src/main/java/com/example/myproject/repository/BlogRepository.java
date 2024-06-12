package com.example.myproject.repository;

import com.example.myproject.model.Blogpost;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repository interface for {@link Blogpost} instances. Provides basic CRUD operations due to the extension of {@link MongoRepository}.
 * Includes custom methods for finding a blog ID.
 */
@Repository
public interface BlogRepository extends MongoRepository<Blogpost, String> {
    Optional<Blogpost> findById(String id);
    void deleteById(String id);
}