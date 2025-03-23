# Personal Website

## Introduction

This project is a personal website designed to showcase my skills and projects as a software developer. The website is built using React and Material-UI, incorporating custom modifications to fit personal preferences and style. It is designed to be fully functional and scalable, integrated with CI/CD pipelines using GitHub Actions for automated testing and deployment.

## Features

- **Home Page**: Introduces visitors to the site with a featured blog post and quick access to other sections.
- **About Me**: A detailed section about my background, skills, and interests.
- **Projects**: Highlights some of the key projects and the technologies used.
- **Blog**: A section for latest blog posts about technology and personal experiences.
- **Contact Information**: Provides ways to connect with me via social media or email.

## Technical Details

### Structure

The website is structured into multiple components, each responsible for a part of the website:

- `Header`: Contains the navigation bar and links to other parts of the website.
- `Footer`: Displays copyright and social media links.
- `MainFeaturedPost`: Shows a highlighted post or project with significant details.
- `FeaturedPost`: Smaller cards used for showing various projects or posts.
- `Main`: A component for displaying the latest blog posts in detail.
- `Sidebar`: Used for showing additional information like contacts or small snippets.
- `AboutMe`, `AboutThisWebsite`, `ContactMe`: Specific pages dedicated to detailed information.

### Technologies Used

- **React**: The core framework used for building the website.
- **Material-UI**: A popular React UI framework used for designing a modern and responsive layout.
- **CSS**: Used for additional styling and responsive design adjustments.
- **React Router**: Manages routing for the website, enabling navigation between different sections.

### Customization of Material UI Template

The Material UI template provided a robust starting point for the design of the website. Significant customizations include:

- Adaptation of the grid layout to fit the content and visual style desired.
- Integration of personal aesthetics into the Material UI components through theme customization.

## Features

- **React Application**: Frontend built with React, providing a responsive user interface.
- **Docker Integration**: Containerization of the web application using Docker for consistent development, testing, and production environments.
- **Continuous Integration and Deployment**: Automated pipelines using GitHub Actions to build, test, and deploy the application to AWS.

## CI/CD Pipeline

### Workflow Description

The CI/CD pipeline is defined in `.github/workflows/` and includes the following jobs:

#### Build React

- **Setup**: Initializes the environment using Node.js and installs dependencies.
- **Build**: Compiles the React application.
- **Archive**: Stores the built artifacts for subsequent steps or jobs.

#### Push to AWS

- **Setup**: Configures AWS credentials and logs into Amazon ECR.
- **Build and Push**: Builds the Docker image, tags it, and pushes it to an AWS ECR repository.

### Docker Configuration

The application is containerized using the following configuration:

- **Base Image:** We use nginx:stable-alpine as the base image. This is a minimal Docker image based on Alpine Linux with Nginx web server installed.
- **Content Copy:** The built React application files are copied into the Nginx server's html directory to be served statically.
- **Configuration:** We also copy a custom Nginx configuration file into the container to ensure correct web server configuration.
- **Port Exposure:** The container exposes port 80 to allow web traffic to reach the Nginx server.
- **Command:** The default command runs Nginx in the foreground so that the Docker container does not exit automatically.