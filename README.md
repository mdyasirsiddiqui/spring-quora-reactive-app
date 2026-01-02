# Quora Reactive App

## 📌 Overview
This is a **Spring Boot WebFlux** based Quora‑style Question & Answer application.  
It demonstrates a clean reactive architecture with **Controller → Service → Repository → MongoDB**, using DTOs for structured request/response handling.

The project is designed as a foundation for building scalable, event‑driven systems with **MongoDB**, **Elasticsearch**, and **Apache Kafka** integration planned for future versions.

---

## 🚀 Features (Current & Planned)
- REST API endpoint: `/api/questions`
    - **POST**: Create a new question with validation (`title`, `content`)
- DTOs with **Jakarta Validation** for input constraints
- Reactive persistence with **Spring Data MongoDB Reactive**
- Logging with **Slf4j**
- Built with **Gradle** and **Java 21**

### Planned Enhancements
- **GET /api/questions** → Fetch all questions (Flux stream)
- **Search integration with Elasticsearch** → Full‑text search on questions/answers
- **Event streaming with Apache Kafka** → Publish question/answer events for analytics
- **Global Exception Handling** → Consistent error responses
- **Unit & Integration Tests** → JUnit + Reactor Test

---

## 🛠 Tech Stack
- **Java 21**
- **Spring Boot 4.0.1**
- **Spring WebFlux** (Reactive REST APIs)
- **Spring Data MongoDB Reactive**
- **Elasticsearch** (planned)
- **Apache Kafka** (planned)
- **Lombok**
- **Gradle**
- **JUnit 5 / Reactor Test**

## 📖 Changelog

### v1.0.0
- 🚀 First working version of the Quora Reactive App
- Implemented `POST /api/questions` endpoint for creating questions
- Applied **DTO validation** (title/content length constraints)
- Persisted data with **Reactive MongoDB Repository**
- Established clean layered architecture (Controller → Service → Repository → Database)


-------


