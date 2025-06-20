
# Student Management System

A single-user, admin-based web application built with **Spring Boot**, **Thymeleaf**, and **MySQL**, designed for managing students, teachers, and courses in an academic institution. It supports CRUD operations, structured course management (semesters and subjects), and data export functionality.

## Features

- Admin Login Authentication
- Manage **Students** and **Teachers** (Add, Edit, Delete, List)
- Create and manage **Courses**, each containing:
  - Semesters
  - Subjects with individual fees
- View structured **Course Details** including total fee calculation
- Export course structure to **PDF** and **Excel**
- Responsive UI with **Bootstrap 4**
- Session management using **Spring Security**
- Interactive **Admin Dashboard** displaying:
  - Total Students, Teachers, Courses
  - Quick Actions panel for managing entities
  -  Analytics section with(Chart.js integration)


## Technologies Used

| Layer       | Technology             |
|-------------|------------------------|
| Backend     | Java 21, Spring Boot   |
| Security    | Spring Security        |
| Frontend    | Thymeleaf, HTML, CSS, Bootstrap 4 |
| Database    | MySQL                  |
| Export      | iTextPDF, Apache POI   |
| Dev Tools   | STS4, Maven            |

## Getting Started

### Prerequisites
- Java 21+
- MySQL 8+
- Maven 3.6+
- Spring Tool Suite (or IntelliJ/Eclipse)

### 1. Clone the Repository
```bash
git clone https://github.com/VirajLadkat/student-management-system.git
cd student-management-system
```

### 2. Configure the Database
Create a MySQL database named `student`. Update the `application.properties` file:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/student
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```

### 3. Run the Application
```bash
mvn spring-boot:run
```

Access the app at: `http://localhost:8080/`

### 4. Default Admin Credentials
> *(Set or update as per your configuration if login is present)*

---

## Project Structure

```
src/
├── controller/
├── entity/
├── repository/
├── service/
├── serviceImpl/
├── config/
└── templates/
```

---

## Project Highlights

- Implemented full **role-based design** even though this version supports a single admin user.
- Used **modular service layers** with separation of concerns (controller, service, repository).
- Export functions built with **iTextPDF** and **Apache POI**.
- Dynamic front-end forms with Thymeleaf and responsive Bootstrap UI.
- Built an **interactive Dashboard** to summarize key system metrics and enable fast navigation.

---

## License

This project is open-source and available under the [MIT License](LICENSE).

---

## Author

**Viraj Ladkat**  
[GitHub Profile](https://github.com/VirajLadkat)
