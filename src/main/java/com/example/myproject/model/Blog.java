package com.example.myproject.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represents a blog post in the application, containing the post's unique identifier and description.
 */
@Document
public class Blog {
    //TODO: Annotations (@Email, @NotNull, @Data, @NoArgsConstructor, @AllArgsConstructor, @NotBlank)

    @Id
    private String id;
    private String description;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}