package com.example.myproject.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a blog post in the application, containing the post's unique identifier and description in english and german.
 */
@Document
public class Blogpost {
    //TODO: Annotations (@Email, @NotNull, @Data, @NoArgsConstructor, @AllArgsConstructor, @NotBlank)

    @Id
    private String id;
    private Map<String, String> description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, String> getDescription() {
        return description;
    }

    public void setDescription(Map<String, String> description) {
        this.description = description;
    }
}