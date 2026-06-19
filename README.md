# taskflow-backend
# TaskFlow Backend Learning Journey

This repository contains my internship learning tasks and hands-on implementations completed as part of the TaskFlow Backend Learning Path.

Day 1 - Java Fundamentals, OOP & SOLID Principles

Topics Covered

Object-Oriented Programming (OOP)
Classes and Objects
Inheritance
Abstraction
Interfaces
Polymorphism
SOLID Principles

Implementations

OOP Package

Employee and Manager classes
Notification abstraction with EmailNotification implementation
Payment abstraction with CardPayment implementation
User class demonstration

SOLID Package

MessageService interface
EmailService implementation
SmsService implementation
WhatsAppService implementation

Concepts Practiced

Single Responsibility Principle
Open/Closed Principle
Interface-based design
Dependency abstraction


Day 2 - Modern Java Features

Topics Covered

Records
Streams API
Optional
Lambda Expressions
Method References
Switch Pattern Matching

Implementations

Modern Java Package

Task record
Stream operations:
  * Filtering
  * Mapping
  * Sorting
  * Grouping
  * Counting
Optional usage
Switch pattern matching examples

Concepts Practiced

Functional programming in Java
Immutable data with Records
Stream-based data processing
Modern Java language features



Day 3 - Spring Boot Fundamentals & Project Bootstrap

Topics Covered

Spring Boot
Spring IoC Container
Dependency Injection
Spring Boot Auto Configuration
Maven Dependency Management
Layered Architecture

Project Structure
com.taskflow
  controller
  service
  repository
  dto
  entity
  exception
  config
  oop
  solid
  modernjava


Implementations

Spring Boot application bootstrap
HealthController
Application configuration
Dev profile setup

API Endpoint

http
GET /api/health


Sample Response:

json
{
  "status": "UP",
  "version": "1.0.0"
}


Verification

Application runs successfully on port 8080
Health endpoint tested successfully
Actuator health endpoint enabled

Technologies Used

Java 21
Maven
Spring Boot 3.5.3
Spring Web
Spring Boot Actuator
Spring Validation
Lombok
Git & GitHub

Day 4 – REST API Design, Controllers, Services & DTOs

Implemented a Task Management REST API using Spring Boot.
Created a layered architecture with Controller, Service, DTO, and Entity classes.
Developed a `Task` entity to represent task data.
Created DTOs for request and response handling.
Implemented `TaskService` to manage task operations using in-memory storage.
Developed `TaskController` exposing REST endpoints for CRUD operations.
Added the following endpoints:
  * `GET /api/tasks` – Retrieve all tasks
  * `GET /api/tasks/{id}` – Retrieve a task by ID
  * `POST /api/tasks` – Create a new task
  * `PUT /api/tasks/{id}` – Update an existing task
  * `DELETE /api/tasks/{id}` – Delete a task
Successfully ran and tested the application locally on port 8080.

Day 5 - Database Integration with Spring Data JPA & PostgreSQL

Completed
- Configured PostgreSQL datasource in Spring Boot
- Added Spring Data JPA and Hibernate
- Converted Task to a JPA Entity
- Created User and Project entities
- Implemented entity relationships:
  - User (1:N) Task
  - Project (1:N) Task
- Created TaskRepository, UserRepository, and ProjectRepository
- Replaced in-memory storage with PostgreSQL persistence
- Verified CRUD operations using PostgreSQL database

Technologies Used
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL

## Author

Sanjali Reddy
B.Tech Information Technology
Internship Learning Project - TaskFlow Backend
