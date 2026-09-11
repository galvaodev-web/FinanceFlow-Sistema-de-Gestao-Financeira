# FinanceFlow Architecture

## Objective

FinanceFlow is a portfolio-grade personal finance application designed to demonstrate backend architecture, relational persistence, automated testing, API documentation, containerization and continuous integration in a realistic full-stack project.

## High-level architecture

```text
React client
    |
    | HTTP / JSON
    v
Spring Boot REST API
    |
    +--> Controllers
    |       |
    |       v
    +--> Services (business rules)
    |       |
    |       v
    +--> Repositories (Spring Data JPA)
            |
            v
        PostgreSQL
```

The backend follows a layered architecture to keep HTTP concerns, business rules and persistence separate.

## Backend responsibilities

### Controller layer

Receives HTTP requests, validates request payloads through Bean Validation and exposes REST resources. Controllers should remain thin and delegate business decisions to services.

### Service layer

Contains application and domain rules. Services orchestrate repositories, calculations and validations and are the main target of unit tests.

### Repository layer

Uses Spring Data JPA to isolate persistence concerns and database queries from the rest of the application.

### DTO and mapping layer

DTOs define the API contract independently from persistence entities. Mapping prevents controllers from exposing JPA entities directly.

### Exception handling

Centralized exception handling provides predictable HTTP error responses and keeps error mapping outside business services.

## Frontend responsibilities

The React application consumes the REST API and organizes UI concerns into pages, reusable components, hooks, services and utility functions. API access is kept outside visual components whenever possible.

## Infrastructure

- **PostgreSQL** is the primary relational database.
- **Docker Compose** provides a reproducible local environment for backend and database.
- **GitHub Actions** validates backend tests and frontend builds on pushes and pull requests.
- **Dependabot** monitors Maven, npm and GitHub Actions dependencies.
- **Swagger/OpenAPI** exposes an interactive API contract for development and evaluation.

## Testing strategy

The current automated test suite focuses on service-level business rules using JUnit and Mockito. The next testing milestone is integration testing with a real PostgreSQL-compatible environment using Testcontainers.

## Security roadmap

Authentication and authorization are intentionally tracked as an architectural evolution instead of being implemented as a superficial login screen. Planned work includes:

1. Spring Security configuration.
2. User persistence and password hashing with BCrypt.
3. JWT-based authentication.
4. Resource ownership so transactions, categories and budgets are scoped to the authenticated user.
5. Authorization tests for protected endpoints.

## Database evolution roadmap

The application currently uses Hibernate schema management for local development. A production-oriented evolution will introduce Flyway migrations so database changes are versioned and reproducible.

## Engineering principles

- Keep controllers thin.
- Put business rules in services.
- Prefer explicit API contracts through DTOs.
- Validate at system boundaries.
- Keep secrets outside source control.
- Automate repeatable checks in CI.
- Add dependencies only when they solve a concrete engineering problem.
- Document architectural decisions that materially affect maintainability.
