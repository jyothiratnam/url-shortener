# 🔗 URL Shortener

A scalable URL Shortener built using **Spring Boot**, **Spring Data JPA**, and **MySQL** following clean layered architecture and RESTful API design.

> 🚀 This project is being developed incrementally to demonstrate production-ready backend engineering concepts, system design, and best practices.

---

# Tech Stack

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA (Hibernate)
- MySQL
- Maven

---

# Project Architecture

```
Client
   │
   ▼
Controller
   │
   ▼
Service
   │
   ▼
Repository
   │
   ▼
MySQL
```

Project follows a **Layered Architecture** with clear separation of responsibilities.

---

# Project Structure

```
src/main/java
│
├── controller
│      UrlController
│      RedirectController
│
├── service
│      UrlService
│
├── service/impl
│      UrlServiceImpl
│
├── repository
│      UrlRepository
│
├── entity
│      UrlMapping
│
├── dto
│      CreateUrlRequest
│      CreateUrlResponse
│
├── util
│      Base62Util
│
└── exception
```

---

# Features Implemented

## ✅ Create Short URL

API

```
POST /api/v1/urls
```

Request

```json
{
    "longUrl":"https://chat.openai.com"
}
```

Response

```json
{
    "shortUrl":"http://localhost:8080/b"
}
```

---

## ✅ Redirect to Original URL

API

```
GET /{shortCode}
```

Example

```
GET /b
```

Response

```
302 Found
```

Browser automatically redirects to

```
https://chat.openai.com
```

---

# Database Design

Table

```
url_mapping
```

| Column | Description |
|---------|-------------|
| id | Primary Key |
| long_url | Original URL |
| short_code | Generated Base62 code |
| click_count | Number of redirects |
| active | URL status |
| created_at | Creation timestamp |
| expires_at | Expiry timestamp (Future Use) |

---

# URL Generation Flow

```
Receive Request
        │
        ▼
Create Entity
        │
        ▼
Save Entity
        │
        ▼
Database Generates ID
        │
        ▼
Base62 Encoding
        │
        ▼
Update shortCode
        │
        ▼
Save Again
        │
        ▼
Return Response
```

---

# Base62 Encoding

Instead of exposing database IDs directly, IDs are converted into Base62 characters.

Example

```
1  -> b
2  -> c
61 -> Z
62 -> ba
```

Advantages

- Short URLs
- URL Safe
- Human Friendly
- Guaranteed Unique (using database IDs)

---

# Design Decisions

## Why DTO?

Entity classes are not exposed directly through REST APIs.

Benefits

- Better Security
- Loose Coupling
- API Stability
- Easier Future Changes

---

## Why ResponseEntity?

Used to customize

- HTTP Status Code
- Response Headers
- Response Body

Example

```
201 Created
302 Found
404 Not Found
```

---

## Why Separate RedirectController?

Instead of

```
GET /api/v1/urls/{code}
```

Project uses

```
GET /{shortCode}
```

This mimics how real URL shortening services work (Bitly, TinyURL).

It also follows the **Single Responsibility Principle** by separating API operations from redirection logic.

---

## Why Two Database Saves?

Current implementation:

```
Insert URL
      │
      ▼
Database generates ID
      │
      ▼
Generate Base62
      │
      ▼
Update shortCode
```

Reason

Auto Increment ID is available only after the first insert.

Future improvement:

Replace Auto Increment with **Snowflake ID Generator** to perform only one database insert.

---

# Concepts Covered

### Spring Boot

- REST APIs
- Layered Architecture
- Dependency Injection
- Constructor Injection
- Spring Data JPA
- Entity Mapping
- DTO Pattern
- ResponseEntity
- Path Variables
- RequestBody
- Validation
- Repository Pattern

---

### Java

- Object-Oriented Design
- Interfaces
- Optional
- StringBuilder
- Utility Classes

---

### Database

- Entity Relationships
- Primary Keys
- Auto Increment
- Unique Constraints
- Timestamps

---

### System Design

- Base62 Encoding
- URL Shortening
- Redirect Flow
- Layered Architecture
- Separation of Concerns

---

# Current Limitations

- Uses Database Auto Increment IDs
- Performs two database writes while creating a URL
- No caching
- No analytics
- No authentication
- No custom aliases
- No expiration support
- No rate limiting

These will be addressed in future phases.

---

# Planned Enhancements

- Redis Cache
- Click Analytics
- URL Expiration
- Custom Short Codes
- Duplicate URL Detection
- Snowflake ID Generator
- Docker
- Nginx
- Rate Limiting
- JWT Authentication
- Kafka Event Processing
- Monitoring & Logging
- AWS Deployment

---

# Learning Goals

This project is being built to explore

- Backend Development
- Spring Boot
- Clean Architecture
- High-Level System Design
- REST API Design
- Performance Optimization
- Production Best Practices

---

# Status

✅ Phase 1 - In Progress

- Spring Boot Setup
- MySQL Integration
- Create Short URL API
- Redirect API
- Base62 Encoding
- Clean Layered Architecture

Next milestone:

- Global Exception Handling
- Request Validation
- Logging
- Redis Integration
- Click Analytics
