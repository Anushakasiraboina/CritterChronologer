# 🐾 Critter Chronologer

A Spring Boot REST API for managing a pet care scheduling system. This application allows customers to register pets, employees to manage their availability and skills, and administrators to create schedules for pet services.

## Features

- Customer management
  - Create customers
  - Retrieve customer information
- Pet management
  - Register pets
  - Assign pets to owners
  - Retrieve pets by owner
- Employee management
  - Create employees
  - Update employee availability
  - Store employee skills
  - Find employees by skills and availability
- Schedule management
  - Create schedules
  - Retrieve schedules by employee
  - Retrieve schedules by pet
  - Retrieve schedules by customer

## Technologies Used

- Java 8
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- H2 Database (for testing)
- Maven
- JUnit

## Project Structure

```
src
├── main
│   ├── java
│   └── resources
└── test
```

## Build and Run

Clone the repository

```bash
git clone https://github.com/Anushakasiraboina/CritterChronologer.git
```

Move into the project

```bash
cd CritterChronologer
```

Build the project

```bash
mvn clean package
```

Run the application

```bash
mvn spring-boot:run
```

The application starts on:

```
http://localhost:8082
```

## Running Tests

Execute all tests using Maven:

```bash
mvn test
```

Current project status:

- Functional tests passing
- Maven build successful

## API Overview

### Customer APIs

- Create Customer
- Get All Customers
- Get Owner By Pet

### Pet APIs

- Create Pet
- Get Pets By Owner

### Employee APIs

- Create Employee
- Set Availability
- Find Employees By Skills

### Schedule APIs

- Create Schedule
- Get Schedule By Employee
- Get Schedule By Pet
- Get Schedule By Customer

## Project Highlights

- Layered Spring Boot architecture
- RESTful API implementation
- Entity relationships using JPA
- MySQL database integration
- Automated testing with JUnit
- Maven-based project management

## Author

**Anusha Kasiraboina**

GitHub:
https://github.com/Anushakasiraboina/CritterChronologer
