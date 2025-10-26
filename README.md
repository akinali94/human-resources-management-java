# HR Management System

This is a Human Resources (HR) Management System built with **Spring Web MVC**.  
The system includes three main roles: **Admin**, **Manager**, and **Employee**, and supports typical HR operations like leave requests, expense tracking, and user role management.

---

## Roles & Permissions

#### Admin
- Create and manage **Managers** and **Companies**
- Update manager and company information

#### Manager
- View and manage their **Employees**
- Approve or reject:
  - **Leave Requests**
  - **Expense Requests**
- Add employees to the company. Upon creation, employees receive an email to set their password and log in.

#### Employee
- Submit **Leave Requests**
- Submit **Expense Requests** for approval

---

The project was developed using a multi-module Maven structure following Spring best practices. The project utilizes Spring Security for authentication and role-based authorization.

---

## Technologies Used

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Web MVC](https://docs.spring.io/spring-framework/reference/web/webmvc.html)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Spring Security](https://spring.io/projects/spring-security)
- [Thymeleaf](https://www.thymeleaf.org/)
- [PostgreSQL](https://www.postgresql.org/)
- **Multi-module Maven Architecture**
- **Repository Pattern**


---

## Running on your Local

### ⚙️ Prerequisites

- [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)


### Build the Project

```bash
mvn clean install
```

### Run the Application

```bash
mvn spring-boot:run -pl humanresources-web
```

Or you can run the application JAR directly:

```bash
java -jar humanresources-web/target/humanresources-web-0.0.1-SNAPSHOT.jar
```
