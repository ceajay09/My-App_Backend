## Introduction

This repository contains the backend implementation for my personal website, serving as the foundational layer where I introduce myself and outline the technical structure of the site. This platform reflects my expertise in backend development, showcasing my ability to create RESTful services and manage data using MongoDB.

## Technologies and Frameworks

The backend employs various technologies and frameworks to ensure robust and secure application development:

- **Spring Boot**: Simplifies the process of building new Spring applications through convention over configuration.
- **Spring Data MongoDB**: Facilitates seamless database interactions by integrating directly with MongoDB.
- **Spring Security**: Provides essential authentication and authorization mechanisms to protect the application.
- **Spring Web MVC**: A framework that uses the Model-View-Controller architecture to develop web applications.
- **Log4j2**: Enhances the backend's capability for monitoring and debugging through advanced logging.

## Features

- **User Authentication**: Features secure login and registration processes.
- **Blog Management**: Allows to create, read, update, and delete blogpost posts.
- **PDF Download**: Offers functionality for downloading PDF files for offline viewing.

## Project Structure

The backend's architecture is designed for clarity and efficiency, organized into specific packages:

- **`config`**: Configuration settings for security, MongoDB, and more.
- **`controller`**: Controllers that manage HTTP requests and responses.
- **`model`**: Entity classes that represent the data used within the application.
- **`repository`**: Interfaces that facilitate data access operations on MongoDB.
- **`service`**: Business logic and database interaction are handled here.

## Technologies and Tools Used

- **Version Control and CI/CD**: Development leverages Git with GitHub for version control, integrated with a CI/CD pipeline that automatically updates Docker images and pushes them to AWS on new commits to the main branch.
- **AWS Infrastructure**: Utilizes two EC2 instances with load balancing for optimized performance and reliability. An HTTPS certificate ensures encrypted data transfer.
- **Docker and Containerization**: Docker Compose orchestrates the backend and other services, ensuring consistent environments and streamlined deployment processes.

## Continuous Monitoring and Load Balancing

The application uses AWS for load balancing and SSL/TLS configurations, with Docker containers monitored via AWS CloudWatch. This setup helps in efficiently managing traffic and improving site reliability.

## Repository and Code Structure

This well-organized codebase enhances maintainability and functionality understanding:

- **`Config`**: Includes security and operational settings.
- **`Controllers`**: Manages HTTP interactions.
- **`Services`**: Contains logic for user account management and blogpost features.
- **`Repositories`**: Engages with MongoDB for data operations.
- **`Models`**: Defines the data structure for user and blogpost information.

## Docker Configuration

### Dockerfile

The `Dockerfile` sets up the environment for running the backend service. It is based on Amazon Corretto 17, includes essential tools like `curl`, and prepares the environment variables necessary for our application to run. It ensures that our application's port is exposed and defines the entry point for running our Java application.

### Docker Compose

`docker-compose.yml` manages the orchestration of our backend and frontend services along with Traefik, which acts as a reverse proxy. This setup helps in:
- Defining and running multi-container Docker applications.
- Handling network traffic efficiently, directing it to the correct service.
- Automating deployment processes, making it easier to scale and maintain.

### Quick Overview

- **Consistency and Isolation**: Each part of the application runs in its own container, ensuring that our environments are consistent and isolated across development and production.
- **Simplified Management**: Docker Compose allows for easy management of services and their interactions, simplifying network configurations and service dependencies.

## GitHub Actions Workflow: CI for Backend

### Overview

Our GitHub Actions workflow, defined in `build-maven.yml`, automates the process of building, testing, and deploying the backend component of our application whenever changes are made. It is triggered by pull requests being closed on the `main` branch.

### Workflow Steps

1. **Build Jar**: This job compiles the backend code into a JAR file using Maven on an Ubuntu runner. It includes setting up JDK 17 with Corretto distribution and caching dependencies for faster builds.

2. **Archive and Dependency Updates**: After building, the JAR file is archived and the dependency graph is updated to keep track of used libraries.

3. **Push to AWS**: If the build job succeeds, this job proceeds to download the JAR from the previous step and uses it to build a Docker image. This image is then pushed to Amazon ECR using configured AWS credentials, ensuring that our application is updated on AWS with the latest changes.

### Key Features

- **Automated Builds**: Automatically compiles and tests code changes to ensure stability.
- **AWS Integration**: Seamlessly updates the backend service on AWS by pushing new Docker images after successful builds.
- **Security and Efficiency**: Uses secure AWS credentials and efficient caching mechanisms to speed up the build and deployment processes.


## Summary

My personal website is crafted to demonstrate sophisticated software development skills using state-of-the-art technologies and best practices. It aims to provide a secure, robust, and responsive user experience, showcasing my capabilities as a software developer ready to tackle challenges in the tech industry.
