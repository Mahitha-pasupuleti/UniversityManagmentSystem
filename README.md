# 🎓 University Management System (Microservices-Based)

A robust, secure, and scalable **University Management System** built using **Java + Spring Boot**, designed with a **microservices architecture**. This system manages core university operations like departments, courses, professors, students, and user authentication.

---

## ⚙️ Tech Stack

- Java 17
- Spring Boot (Microservices)
- Spring Security
- Spring Cloud
- REST APIs
- JWT-based Authentication

---

## 🧱 Microservices Architecture

- **Authentication Service**
  - Handles user login, registration, and role-based JWT authentication
- **Department Service**
- **Course Service**
- **Professor Service**
- **Student Service**
- **Enrollment Service**

Each service is **independently deployable**, communicates via REST, and is structured for future scaling.

---

## 🔐 Authentication Highlights

- Fully working **JWT-based Authentication**
- Role-based access for:
  - President
  - Vice President
  - Department Admins
  - Professors
  - Students
- Token validation middleware to secure service-to-service communication

---

## 🚀 Features

- 📁 Create & manage university **departments**
- 📚 Add and assign **courses** to departments and professors
- 👨‍🏫 Add **professors** and manage their teaching load
- 🎓 Enroll **students** into course sections
- 🔐 Secure access control using microservice-aware JWT authentication

---

## 📐 Architecture Principles

- Microservices for modularity and scalability
- Clean separation of concerns using:
  - Controller → Service → Repository layers
- Secure inter-service communication
- Scalable and extensible structure for real-world deployment

---

## 📦 ServiceName
├── 📁 controller        # REST API endpoints
├── 📁 service           # Business logic
├── 📁 model             # Entity and DTO classes
├── 📁 repository        # JPA repositories
├── 📁 config            # Configuration classes (e.g., security, beans)
├── 📁 exception         # Custom exceptions and handlers
├── 📁 dto               # Data Transfer Objects (optional)
└── 📄 application.properties / application.yml


---

## 💻 How to Run (Basic)

Each microservice can be run independently:

```bash
cd AuthService
./mvnw spring-boot:run

cd DepartmentService
./mvnw spring-boot:run

# Repeat for other services
```
