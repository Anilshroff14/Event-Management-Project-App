# Event-Management-Project-App
An Event Management System built using Java Spring Boot and PostgreSQL that helps manage events, venues, organizers, attendees, and registrations. The application performs full CRUD (Create, Read, Update, Delete) operations and exposes RESTful APIs tested using Postman.

Features

Manage multiple entities:

Event — Manage event details such as name, date, description, etc.

Venue — Manage venue information like location and capacity

Organizer — Manage event organizers

Attendee — Manage attendee details

Registration — Manage event registrations

Full CRUD operations for each module

RESTful API architecture using Spring Boot (MVC pattern)

PostgreSQL used for persistent data storage

Tested APIs using Postman

Proper exception handling and input validation

Layered architecture: Controller → Service → Repository

Technologies Used

Java Spring Boot

PostgreSQL

Spring Data JPA

Maven

Postman

Java 17+

Project Structure
EventManagementApp/
├── src/
│   ├── main/
│   │   ├── java/com/example/eventmanagement/
│   │   │   ├── controller/
│   │   │   ├── service/
│   │   │   ├── repository/
│   │   │   ├── model/
│   │   │   └── EventManagementApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── data.sql
│   └── test/
└── pom.xml

Setup Instructions

Clone the repository

git clone https://github.com/<your-username>/event-management-app.git
cd event-management-app


Configure the database in application.properties

spring.datasource.url=jdbc:postgresql://localhost:5432/eventdb
spring.datasource.username=postgres
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true


Build and run the application

mvn clean install
mvn spring-boot:run


The application will start at http://localhost:8080

API Endpoints

Base URL: http://localhost:8080/api

Examples:

GET /events

POST /events

PUT /events/{id}

DELETE /events/{id}

Similar endpoints exist for:

/venue

/organizer

/attendee

/registration

Learning Outcomes

Gained practical experience with Spring Boot framework

Implemented RESTful APIs using Controller-Service-Repository pattern

Understood JPA and database mapping with entity relationships

Improved API testing and debugging using Postman

Developed a scalable and maintainable backend system
