# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Common Commands

### Build and Development
- `./mvnw clean compile` - Compile the project
- `./mvnw spring-boot:run` - Run the application locally
- `./mvnw clean package` - Build the JAR file
- `./mvnw clean test` - Run all tests
- `./mvnw test -Dtest=ClassName` - Run a specific test class

### Testing
- `./mvnw test` - Run all unit tests
- `./mvnw test -Dtest=SimpleRestApiApplicationTests` - Run specific test class

## Architecture Overview

This is a Spring Boot REST API application with the following structure:

### Technology Stack
- **Spring Boot 3.5.3** with Java 17
- **Spring Data JPA** for database operations
- **MySQL** as the database (configured via mysql-connector-j)
- **Maven** as the build tool

### Package Structure
- `org.example.simplerestapi` - Root package
  - `model/` - JPA entities (User entity with id, name, email, age)
  - `repository/` - Spring Data JPA repositories (UserRepository with email queries)
  - `SimpleRestApiApplication.java` - Main Spring Boot application class

### Database Configuration
- Uses MySQL database
- JPA entities are configured with standard annotations (@Entity, @Table, @Id, @GeneratedValue)
- User entity maps to "users" table with unique email constraint

### Key Implementation Notes
- User entity has auto-generated ID, required name/email fields, and age
- UserRepository provides findByEmail() and existsByEmail() methods
- There's a package import issue in UserRepository.java (imports com.example instead of org.example)