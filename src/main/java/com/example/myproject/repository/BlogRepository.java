package com.example.myproject.repository;

import com.example.myproject.model.Account;
import com.example.myproject.model.Blog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repository interface for {@link Blog} instances. Provides basic CRUD operations due to the extension of {@link MongoRepository}.
 * Includes custom methods for finding a blog ID.
 */
@Repository
public interface BlogRepository extends MongoRepository<Blog, String> {
    Optional<Blog> findById(String id);
    void deleteById(String id);
}