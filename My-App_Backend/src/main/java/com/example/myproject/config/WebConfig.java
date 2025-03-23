package com.example.myproject.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configures cross-origin request handling for the application.
 * This configuration allows for controlled access to resources across different origins,
 * specifically for the API paths, enabling frontend applications from specified origins
 * to interact with the backend services.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private static final Logger logger = LogManager.getLogger(WebMvcConfigurer.class);

    /**
     * Configures CORS (Cross-Origin Resource Sharing) settings for API endpoints.
     * This setup allows the frontend application running on 'http://localhost:3000' to make requests
     * to the backend across different origins. It specifies which HTTP methods are allowed when accessing
     * the resources, and enables sending credentials with cross-origin requests.
     *
     * @param registry The CORS registry to which mapping configurations are added.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins(
                        "https://cesarjaquiery.ch",
                        "https://demowebsite.cesarjaquiery.ch",
                        "http://localhost:3000",
                        "http://localhost:5173"
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
        logger.info("CORS mappings added for /api/** to allow origins: http://frontend:80 + http://localhost:3000");
    }
}
