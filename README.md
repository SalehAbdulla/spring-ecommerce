# Spring E-Commerce Backend API

A RESTful e-commerce backend API built with Spring Boot. This project provides category and product management endpoints with pagination, sorting, validation, and a persistent H2 database.

## Tech Stack

- **Java 17**
- **Spring Boot 4.0.6**
- **Spring Data JPA** — ORM and repository abstraction
- **H2 Database** — file-based persistent storage
- **ModelMapper 3.2.4** — DTO/entity mapping
- **Lombok** — reduces boilerplate code
- **Spring Boot Validation** — bean validation for request bodies
- **Spring Boot DevTools** — development-time tools

## Project Structure

```
spring-ecommerce/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/ecommerce/project/
│   │   │       ├── EcomApplication.java       # Main application entry point
│   │   │       ├── config/                    # AppConstants, AppConfig
│   │   │       ├── controller/                # REST controllers
│   │   │       ├── Exceptions/                # Custom exceptions & global handler
│   │   │       ├── model/                     # JPA entities (Category, Product)
│   │   │       ├── payload/                   # DTOs & response wrappers
│   │   │       ├── repositories/              # Spring Data JPA repositories
│   │   │       └── service/                   # Business logic layer
│   │   └── resources/
│   │       └── application.properties         # Application configuration
│   └── test/
│       └── java/com/ecommerce/project/        # Tests
├── data/                                      # H2 database files (generated at runtime)
└── pom.xml                                    # Maven configuration
```

## Getting Started

### Prerequisites

- **JDK 17** or later
- **Maven** 3.6+ (or use the Maven Wrapper if configured)

### Running the Application

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080` (default Spring Boot port).

### H2 Database Console

The H2 console is enabled and accessible at:

```
http://localhost:8080/h2-console
```

| Setting | Value |
|---------|-------|
| JDBC URL | `jdbc:h2:file:./data/ecom` |
| Username | `sa` |
| Password | *(empty)* |

The database persists to a file located at `./data/ecom.mv.db`. Schema updates are handled automatically via `spring.jpa.hibernate.ddl-auto=update`.

## API Endpoints

### Category Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/public/categories` | Get a paginated list of all categories |
| `POST` | `/api/public/create-category` | Create a new category |
| `PUT` | `/api/admin/update-category/{categoryId}` | Update an existing category |
| `DELETE` | `/api/admin/delete-category/{categoryId}` | Delete a category |

### Product Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/admin/categories/{categoryId}/product` | Add a product to a category |
| `GET` | `/api/public/products` | Get a paginated list of all products |

### Query Parameters

Both `GET /api/public/categories` and `GET /api/public/products` support the following query parameters:

| Parameter | Default | Description |
|-----------|---------|-------------|
| `pageNumber` | `0` | Page index (zero-based) |
| `pageSize` | `10` | Number of items per page |
| `sortBy` | `id` | Field to sort by |
| `sortOrder` | `asc` | Sort direction (`asc` or `desc`) |

Example:

```bash
GET /api/public/products?pageNumber=0&pageSize=5&sortBy=name&sortOrder=asc
```

## Data Models

### Category

| Field | Type | Validation |
|-------|------|------------|
| `id` | `Long` | Auto-generated |
| `categoryName` | `String` | Required, length 3–30 |
| `products` | `Set<Product>` | One-to-many relation |

### Product

| Field | Type | Validation |
|-------|------|------------|
| `id` | `Long` | Auto-generated |
| `name` | `String` | Required |
| `description` | `String` | Required, length 3–255 |
| `price` | `Double` | Required, must be positive |
| `discount` | `Double` | 0–100 |
| `specialPrice` | `Double` | Must be positive |
| `quantity` | `Integer` | Minimum 0 |
| `image` | `String` | Max length 255 |
| `category` | `Category` | Many-to-one relation |