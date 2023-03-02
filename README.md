# SM360 Backend Tech Assignment

SM360 Backend Tech Assignment REST API based on Java Spring, Spring Boot, Spring Data JPA with MySQL.

## Start the server

```
  ./mvnw spring-boot:run
```

## Documentation

[Documentation](http://localhost:8080/api-docs)

## REST API Endpoints

All inputs and outputs use JSON format.


```
/api/v1/listings
  GET / - Get all listings of a dealer with a given state
  POST / - Create a listing
  PUT /{id} - Update a listing
  PUT /{id}/publish - Publish a listing
  PUT /{id}/unpublish - Unpublish a listing

```