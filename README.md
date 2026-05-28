# Employee Management System (EMS)

A full-stack **Employee Management System (EMS)** built using **Spring Boot, React, MySQL, Spring Security, and Bootstrap**.

The application enables organizations to manage employees, departments, authentication, authorization, and profile management through a responsive web interface.

---

# Features

## Employee Management

* Create Employee
* View Employee Details
* Update Employee Information
* Delete Employee
* Employee Unique EEID generation

## Department Management

* Create Departments
* Update Departments
* Delete Departments
* Employee → Department Mapping

## Profile Photo Upload

* Upload employee profile images
* Multipart file handling
* Local server storage
* Profile image preview support

## Security

* Spring Security Integration
* HTTP Basic Authentication
* Role Based Authorization
* Endpoint Protection

## Validation

* Request DTO validation
* Email validation
* Required field validation

## Frontend

* Responsive Bootstrap UI
* Employee Dashboard
* Department Dashboard
* Responsive Tables
* Responsive Navigation

---

# Tech Stack

## Backend

* Java
* Spring Boot
* Spring Security
* Spring Data JPA
* Hibernate
* MySQL
* Maven

## Frontend

* React
* Axios
* Bootstrap

---

# Project Structure

## Backend

```txt
Springbt_restapi
│
├── controller
├── dto
│   ├── request
│   └── response
├── entity
├── mapper
├── repository
├── security
├── service
│   └── impl
├── config
└── exception
```

## Frontend

```txt
src
│
├── components
├── services
├── assets
└── App.js
```

---

# Prerequisites

Install the following before running the project:

* Java 21 (or your configured version)
* Maven
* Node.js
* npm
* MySQL
* Git

---

# Backend Setup (Spring Boot)

## 1. Clone Repository

```bash
git clone https://github.com/bhw-git/employee-management-system.git

cd Employee-Management-System
```

---

## 2. Configure Database

Create MySQL database.

```sql
CREATE DATABASE ems_db;
```

---

## 3. Configure application.properties

Navigate to:

```txt
src/main/resources/application.properties
```

Configure:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ems_db

spring.datasource.username=root

spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true
```

---

## 4. Run Backend Application

From project root:

```bash
mvn spring-boot:run
```

Or run directly from IDE:

```txt
Run → SpringbtRestapiApplication.java
```

Backend server runs on:

```txt
http://localhost:8080
```

---

# Frontend Setup (React)

Navigate to frontend folder.

Example:

```bash
cd employee-management-frontend
```

---

## Install Dependencies

```bash
npm install
```

---

## Run Frontend

```bash
npm run dev
```

Frontend runs on:

```txt
http://localhost:3000
```

---

# Authentication

Current implementation uses **Spring Security Basic Authentication**.

Example credentials:

```txt
EMPLOYEE

Username: employee
Password: employee123
```

```txt
MANAGER

Username: manager
Password: manager123
```

```txt
HR

Username: hr
Password: hr123
```

```txt
ADMIN

Username: admin
Password: admin123
```

---

# API Endpoints

## Employee APIs

| Method | Endpoint              | Description     |
| ------ | --------------------- | --------------- |
| GET    | /api/employees        | Get Employees   |
| GET    | /api/employees/{eeid} | Get Employee    |
| POST   | /api/employees        | Create Employee |
| PATCH  | /api/employees/{eeid} | Update Employee |
| DELETE | /api/employees/{eeid} | Delete Employee |

---

## Department APIs

| Method | Endpoint              | Description       |
| ------ | --------------------- | ----------------- |
| GET    | /api/departments      | Get Departments   |
| POST   | /api/departments      | Create Department |
| PATCH  | /api/departments/{id} | Update Department |
| DELETE | /api/departments/{id} | Delete Department |

---

# Running Full Application

### Start Backend

```bash
mvn spring-boot:run
```

---

### Start Frontend

```bash
npm run dev
```

---

### Open Browser

Frontend:

```txt
http://localhost:3000
```

Backend:

```txt
http://localhost:8080
```

---

# Future Enhancements

* JWT Authentication
* Employee Self Dashboard
* HR / Manager Role Access
* Attendance Management
* Leave Management
* Audit Logs
* Dashboard Analytics
* Cloud File Storage
* Email Notifications

---

# Author

Bhuvanesh

Built using Spring Boot + React.
