package com.example.myproject.service;

import com.example.myproject.model.Blogpost;
import com.example.myproject.repository.BlogRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Service class for managing blog-related operations, including retrieving blog entries from the database.
 */
@Service
public class BlogService {

    private final BlogRepository blogRepository;
    private static final Logger logger = LogManager.getLogger(BlogService.class);
    private Queue<Blogpost> blogpostsQueue;

    @Autowired
    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
        this.blogpostsQueue = new LinkedList<>();
    }

    @Scheduled(fixedRate = 30000)
    public void refreshBlogposts(){
        getAllBlogposts(false);
    }

    public void getAllBlogposts(boolean initialized) {
        if (initialized){
            logger.info("Attempting to retrieve all blogposts from the database.");
        }
        List<Blogpost> blogposts;
        int count = 0;

        try {
            blogposts = blogRepository.findAll();
            if (blogposts.isEmpty()) {
                logger.warn("No blogposts found in the database.");
            } else {
                Collections.reverse(blogposts);
                this.blogpostsQueue.clear();
                this.blogpostsQueue.addAll(blogposts);
                for (Blogpost blogpost : blogposts) {
                    if (initialized){
                        logger.info("Retrieved Blogpost from DB:" + "\t" + blogpost.getId());
                    }
                    count++;
                }
                if (initialized){
                    logger.info(count + " Blogpost(s) successfully retrieved from Database");
                }
            }
        } catch (Exception e) {
            logger.error("Error retrieving blogposts from database", e);
        }
    }

    public Queue<Blogpost> getBlogposts(HttpServletRequest request) {
        String clientIP = request.getHeader("X-Forwarded-For");
        if (clientIP != null && !clientIP.isEmpty()) {
            clientIP = clientIP.split(",")[0].trim();
        } else {
            clientIP = request.getRemoteAddr();
        }
        logger.info("Request received from IP: " + clientIP);

        return this.blogpostsQueue;
    }
}


