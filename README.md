# Pismo Home Assignment

This project implements the Pismo backend coding assignment. It provides REST APIs to create accounts and register financial transactions while applying the required debit/credit rules.

## Technologies

- Java 17
- Spring Boot 3
- Spring Data JPA
- H2 Database
- Flyway Migration
- SpringDoc OpenAPI (Swagger)
- Docker
- JUnit + Mockito for testing

## How to Run

### Using Maven
mvn clean install
mvn spring-boot:run

### Using Docker
docker build -t assignment .
docker run -p 8080:8080 assignment


## API Documentation

Swagger UI:

http://localhost:8080/api/swagger-ui.html

OpenAPI JSON:

http://localhost:8080/api/api-docs

## Endpoints

### Create Account
POST `/api/accounts`

Request:
{
“document_number”: "12345678900"
}

Response:
{
“account_id”: 1,
“document_number”: "12345678900"
}

### Get Account
GET `/api/accounts/{id}`

---

### Create Transaction
POST `/api/transactions`

Request:
{
“account_id”: 1,
“operation_type_id”: 1,
“amount”: 100.00
}

Note: Debit operations are stored as negative values automatically.

Response:
{
“transaction_id”: 10,
“account_id”: 1,
“operation_type_id”: 1,
“amount”: -100.00,
“event_date”: “2025-01-01T10:00:00Z”
}

### List Accounts / Transactions
Supports pagination and sorting.

Example:
GET /api/accounts?page=0&size=5&sortBy=account_id&direction=asc

## Business Rules

- Operation types determine how the amount is stored:
    - Purchase / Withdrawal / Installment → stored as negative
    - Payment → stored as positive

## Testing

Run tests using:

mvn test

## Notes
- H2 database is used for local execution with Flyway for schema setup.
