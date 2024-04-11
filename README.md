## Introduction

This repository contains the backend implementation for my personal website. The backend serves as the foundation for my site, where I introduce myself and explain the technical underpinnings of the website. This project showcases my skills in backend development, particularly in creating RESTful services, managing data with MongoDB, and ensuring secure and asynchronous operations.

## Technologies and Frameworks

The backend is built with the following technologies and frameworks:

- **Spring Boot**: Simplifies the development of new Spring applications through convention over configuration.
- **Spring Data MongoDB**: Provides seamless integration with MongoDB, enabling easy database interactions.
- **Spring Security**: Ensures the security of the application through authentication and authorization mechanisms.
- **JWT (JSON Web Tokens)**: Facilitates secure transfer of information as JSON objects.
- **Spring Web MVC**: Framework for building web applications using the Model-View-Controller design pattern.
- **Log4j2**: Provides logging capabilities for monitoring and debugging the application.

## Features

- **User Authentication**: Secure login and registration processes using JWT for session management.
- **Blog Management**: Users can create, read, update, and delete blog posts.
- **PDF Download**: Facilitates downloading of PDF files, allowing users to access offline content.
- **Asynchronous Processing**: Enhances performance by executing tasks asynchronously.
- **Token Blacklisting**: Ensures security by invalidating tokens upon logout.

## Project Structure

The backend is organized into several packages, each serving a specific purpose:

- **`config`**: Contains configuration classes for security, MongoDB, and other application settings.
- **`controller`**: Houses controllers that handle HTTP requests and responses.
- **`model`**: Defines entity classes representing the data managed by the application.
- **`repository`**: Contains interfaces for data access operations on MongoDB.
- **`service`**: Implements business logic and interactions with the database.
- **`util`**: Provides utility classes, such as token management helpers.

## Setup and Running

### Prerequisites

- Java JDK 11 or later
- Maven 3.6.0 or later
- MongoDB 4.4 or later

### Configuration

1. **Application Properties**: Configure the `src/main/resources/application.properties` file with your MongoDB URI and other application-specific settings.

    ```
    spring.data.mongodb.uri=mongodb://localhost:27017/mydatabase
    ```

2. **Security**: Ensure your JWT secret key is securely managed and not hard-coded in the application.

### Building the Application

Run the following command in the root directory of the project to build the application:


### Running the Application

Execute the following command to start the application:

java -jar target/myapp_backend-0.0.1-SNAPSHOT.jar


