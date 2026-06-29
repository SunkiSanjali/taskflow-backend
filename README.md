# TaskFlow Backend

A secure and scalable Task Management REST API built using Spring Boot that enables users to manage tasks efficiently with JWT-based authentication and role-based authorization.

This project was developed as part of a 15-day backend engineering learning path, covering modern Java, Spring Boot, REST APIs, security, testing, Docker, logging, monitoring, and CI/CD practices.

## Project Overview

TaskFlow is a backend application that provides a complete REST API for managing tasks.

The application allows users to:

- Register new accounts
- Login securely using JWT authentication
- Create tasks
- View tasks
- Update tasks
- Delete tasks
- Secure endpoints based on user roles

The project follows a layered architecture using Spring Boot best practices and demonstrates production-ready backend development concepts including:

- JWT Authentication
- Spring Security
- Spring Data JPA
- PostgreSQL
- Docker
- Swagger/OpenAPI
- Unit Testing
- Logging & Monitoring
- GitHub Actions CI Pipeline

## Features

✔ User Registration

✔ User Login

✔ JWT Authentication

✔ Role-Based Authorization

✔ Task CRUD Operations

✔ RESTful API Design

✔ PostgreSQL Database

✔ Docker Support

✔ Swagger Documentation

✔ Unit Testing

✔ Integration Testing (configured using Testcontainers)

✔ GitHub Actions CI Pipeline

✔ Checkstyle & SpotBugs Code Quality Checks

✔ Logging using SLF4J

✔ Spring Boot Actuator Monitoring

##  Technology Stack

| Technology | Purpose |
|------------|---------|
| **Java 21** | Programming language used for backend development |
| **Spring Boot 3** | Framework for building RESTful APIs |
| **Spring Security** | Authentication and authorization |
| **JWT (JSON Web Token)** | Secure user authentication |
| **Spring Data JPA** | Database access and ORM |
| **Hibernate** | JPA implementation for database operations |
| **PostgreSQL** | Relational database |
| **Maven** | Dependency management and build automation |
| **Docker** | Containerization of the application |
| **Docker Compose** | Multi-container application setup |
| **JUnit 5** | Unit testing framework |
| **Mockito** | Mocking framework for unit tests |
| **Testcontainers** | Integration testing with containerized PostgreSQL |
| **Swagger / OpenAPI** | Interactive API documentation |
| **Git** | Version control system |
| **GitHub** | Source code hosting |
| **GitHub Actions** | Continuous Integration (CI) pipeline |
| **Checkstyle** | Code style analysis |
| **SpotBugs** | Static code analysis |
| **SLF4J + Logback** | Logging framework |
| **Spring Boot Actuator** | Application health and monitoring |
| **Postman** | API testing |
| **IntelliJ IDEA** | Integrated Development Environment (IDE) |

---

#  Project Architecture

TaskFlow follows a layered architecture based on the Controller → Service → Repository pattern. Each layer has a single responsibility, making the application modular, maintainable, and easy to test.

## Architecture Diagram

```text
                        Client
                           │
                           ▼
                  HTTP REST Requests
                           │
                           ▼
                 Spring Security Filter
                           │
                    JWT Authentication
                           │
                           ▼
                    REST Controllers
                           │
                           ▼
                     Service Layer
                  (Business Logic)
                           │
                           ▼
                  Repository Layer
                  (Spring Data JPA)
                           │
                           ▼
                     PostgreSQL Database
```

## Layer Responsibilities

### Controller Layer
- Exposes REST API endpoints.
- Validates incoming requests.
- Delegates business logic to the service layer.
- Returns appropriate HTTP responses.

### Service Layer
- Contains the application's business logic.
- Validates business rules.
- Coordinates communication between controllers and repositories.

### Repository Layer
- Handles database operations.
- Uses Spring Data JPA for CRUD functionality.
- Interacts directly with PostgreSQL.

### Database
- Stores user and task information.
- Managed through Hibernate ORM.

---

## JWT Authentication Flow

```text
User Login
     │
     ▼
Username & Password
     │
     ▼
Authentication Manager
     │
     ▼
Credentials Valid?
     │
 ┌───┴────┐
 │        │
Yes       No
 │         │
 ▼         ▼
Generate   Return 401
JWT Token  Unauthorized
 │
 ▼
Client Stores Token
 │
 ▼
Authorization: Bearer <JWT>
 │
 ▼
JWT Filter Validates Token
 │
 ▼
Authenticated Request
 │
 ▼
Protected REST API
```

This architecture ensures:
- Clear separation of concerns
- Secure authentication
- Easy maintenance
- Scalability
- Better testability

---

#  Project Structure

```text
taskflow-backend/
│
├── .github/
│   └── workflows/
│       └── ci.yml
│
├── src/
│   ├── main/
│   │   ├── java/com/taskflow/
│   │   │   ├── config/
│   │   │   ├── controller/
│   │   │   ├── dto/
│   │   │   ├── entity/
│   │   │   ├── exception/
│   │   │   ├── repository/
│   │   │   ├── security/
│   │   │   ├── service/
│   │   │   └── TaskflowApplication.java
│   │   │
│   │   └── resources/
│   │       ├── application.properties
│   │       └── logback-spring.xml
│   │
│   └── test/
│       └── java/com/taskflow/
│
├── Dockerfile
├── docker-compose.yml
├── pom.xml
└── README.md
```
---

#  Prerequisites

Before running the project, ensure you have the following installed:

- Java 21 or later
- Maven 3.9+
- PostgreSQL
- Docker Desktop
- Git
- Postman (optional for API testing)

---

#  Installation & Setup

## 1. Clone the Repository

```bash
git clone <repository-url>
cd taskflow-backend
```

## 2. Configure PostgreSQL

Create a PostgreSQL database.

Example:

```sql
CREATE DATABASE taskflow_db;
```

Update the database configuration in `application.properties`.

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/taskflow_db
spring.datasource.username=your_username
spring.datasource.password=your_password
```

## 3. Build the Project

```bash
mvn clean install
```

For Windows PowerShell:

```powershell
mvn clean install
```
---

#  Running the Application

Run the application using Maven.

Linux/macOS

```bash
mvn spring-boot:run
```

Windows

```powershell
mvn spring-boot:run
```

The application will start at:

```
http://localhost:8080
```
---

#  Docker Support

## Build Docker Image

```bash
docker build -t taskflow-backend .
```

## Run Container

```bash
docker run -p 8080:8080 taskflow-backend
```

## Using Docker Compose

```bash
docker-compose up --build
```

This starts:

- Spring Boot Application
- PostgreSQL Database

---

#  API Documentation

Swagger UI is available after starting the application.

```
http://localhost:8080/swagger-ui/index.html
```

OpenAPI documentation:

```
http://localhost:8080/v3/api-docs
```

Swagger provides:

- Interactive API testing
- Request/Response schemas
- Endpoint documentation
- Authentication support

---

#  Authentication

TaskFlow uses JWT (JSON Web Token) authentication.

## Authentication Process

1. Register a new user.
2. Login using valid credentials.
3. Receive a JWT token.
4. Include the token in subsequent requests.

Example:

```http
Authorization: Bearer <your-jwt-token>
```

All task-related endpoints require a valid JWT token.

---

# 📌 API Endpoints

| Method | Endpoint | Description | Authentication |
|--------|----------|-------------|----------------|
| POST | `/api/auth/register` | Register a new user | ❌ |
| POST | `/api/auth/login` | Login user | ❌ |
| GET | `/api/tasks` | Get all tasks | ✅ |
| GET | `/api/tasks/{id}` | Get task by ID | ✅ |
| POST | `/api/tasks` | Create a task | ✅ |
| PUT | `/api/tasks/{id}` | Update task | ✅ |
| DELETE | `/api/tasks/{id}` | Delete task | ✅ |

---

#  Testing

TaskFlow includes automated integration tests.

Run all tests:

Linux/macOS

```bash
mvn test
```

Windows

```powershell
mvn test
```

Testing tools used:

- JUnit 5
- Spring Boot Test
- MockMvc
- Testcontainers
- PostgreSQL Test Container

---

#  Logging & Monitoring

TaskFlow includes production-style logging and monitoring.

### Logging

- SLF4J
- Logback
- Console Logging
- File Logging

### Monitoring

Spring Boot Actuator provides:

- Health Endpoint

```
/actuator/health
```

- Metrics Endpoint

```
/actuator/metrics
```

- Environment Information

```
/actuator/info
```
---

#  Continuous Integration (CI/CD)

GitHub Actions is used to automate the build and testing process.

The CI pipeline performs:

- Repository Checkout
- Java Environment Setup
- Maven Dependency Caching
- Project Build
- Automated Testing
- Build Verification

The workflow is automatically triggered on:

- Push to the main branch
- Pull Requests

---

#  Postman Collection

A Postman collection is included with the project for easy API testing.

The collection contains:

- User Registration
- User Login
- Create Task
- Get All Tasks
- Get Task by ID
- Update Task
- Delete Task

Import the collection into Postman and update the JWT token after login.

---

#  Future Enhancements

Potential improvements include:

- Role-Based Access Control (RBAC)
- Refresh Token Support
- Email Verification
- Password Reset Functionality
- Pagination & Sorting
- Advanced Search & Filtering
- Task Categories & Labels
- File Attachments
- Notifications
- Deployment to AWS or Azure

---

#  About the Author

This project was developed by **Sanjali Reddy** as part of a 15-day Backend Internship Learning Path.

Passionate about Java backend development, I enjoy building secure REST APIs, exploring modern backend technologies, and applying industry best practices to create reliable applications.

### Connect

- GitHub: https://github.com/SunkiSanjali
- LinkedIn: https://linkedin.com/in/sanjali-reddy-sunki

---

#  License

This project was developed as part of a 15-day backend internship learning program for educational purposes.

Feel free to fork, learn from, and improve the project.
