package com.example.myproject.service;

import com.example.myproject.model.Blog;
import com.example.myproject.repository.BlogRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    private final BlogRepository blogRepository;
    private static final Logger logger = LogManager.getLogger(BlogService.class);

    @Autowired // TODO: in den konstruktor verpacken
    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public List<Blog> getAllBlogs() { //Ladet alle blogs
        int count = 0;
        List<Blog> blogs = blogRepository.findAll();
        for (Blog blog : blogs) {
            logger.info("Retrieved Blog from DB:"+"\t" + blog.getId());
            count += 1;
        }
        logger.info(count + " Blog(s) successfully retrieved from Database");
        return blogs;
    }

}


