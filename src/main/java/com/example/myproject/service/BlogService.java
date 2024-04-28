package com.example.myproject.service;

import com.example.myproject.model.Blog;
import com.example.myproject.repository.BlogRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for managing blog-related operations, including retrieving blog entries from the database.
 */
@Service
public class BlogService {

    private final BlogRepository blogRepository;
    private static final Logger logger = LogManager.getLogger(BlogService.class);

    @Autowired
    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public List<Blog> getAllBlogs() { //Ladet alle blogs
        logger.info("Attempting to retrieve all blogs from the database.");
        List<Blog> blogs = new ArrayList<>();
        int count = 0;

        try {
            blogs = blogRepository.findAll();
            if (blogs.isEmpty()) {
                logger.info("No blogs found in the database.");
            } else {
                for (Blog blog : blogs) {
                    logger.info("Retrieved Blog from DB:"+"\t" + blog.getId());
                    count++;
                }
                logger.info(count + " Blog(s) successfully retrieved from Database");
            }
        } catch (Exception e) {
            logger.error("Error retrieving blogs from database", e);
        }

        return blogs;
    }

    public List<Blog> getBlogs(HttpServletRequest request) {
        String clientIP = request.getHeader("X-Forwarded-For");
        if (clientIP == null) {
            clientIP = request.getRemoteAddr(); // Fallback to remote address if header is not set
        }
        logger.info("Request received from IP: " + clientIP);
        return blogRepository.findAll();
    }
}


