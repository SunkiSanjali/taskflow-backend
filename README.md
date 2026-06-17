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
├── controller
├── service
├── repository
├── dto
├── entity
├── exception
├── config
├── oop
├── solid
└── modernjava


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


## Author

Sanjali Reddy
B.Tech Information Technology
Internship Learning Project - TaskFlow Backend
