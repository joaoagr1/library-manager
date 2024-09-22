### Book Service API Documentation

## Overview

This API provides functionalities to manage books in a library system. It includes operations to create, retrieve, update, delete, and list books, as well as to fetch book information from the Google Books API.

## Table of Contents

- [Overview](#overview)
- [Table of Contents](#table-of-contents)
- [Requirements](#requirements)
- [Installation](#installation)
- [Configuration](#configuration)
- [Usage](#usage)
    - [Endpoints](#endpoints)
        - [Create Book](#create-book)
        - [Get Book by ID](#get-book-by-id)
        - [Update Book](#update-book)
        - [Delete Book](#delete-book)
        - [List Books](#list-books)
        - [Add Book by Title](#add-book-by-title)
        - [Get Information by Title from Google API](#get-information-by-title-from-google-api)
        - [Create User](#create-user)
        - [Update User](#update-user)
        - [Delete User](#delete-user)
        - [Get User by ID](#get-user-by-id)
        - [List Users](#list-users)
        - [Create Loan](#create-loan)
        - [Get Loan by ID](#get-loan-by-id)
        - [Update Loan](#update-loan)
        - [Delete Loan](#delete-loan)
        - [List Loans](#list-loans)
- [Error Handling](#error-handling)
- [Testing](#testing)
- [License](#license)

## Requirements

- Java 21 or higher
- Maven 3.6.0 or higher
- Spring Boot 2.5.4 or higher

## Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/joaoagr1/library-manager.git
   cd library-manager
   ```

2. Build the project:
   ```sh
   mvn clean install
   ```

## Configuration

Configure the application properties in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/library
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
google.books.api.key=your_google_books_api_key
```

## Usage

### Endpoints

#### Create Book

- **URL:** `/books`
- **Method:** `POST`
- **Request Body:**
  ```json
  {
    "title": "The Lord of the Rings",
    "author": "J.R.R. Tolkien",
    "isbn": "978-3-16-148410-0",
    "year": 2019,
    "category": "BIOGRAPHY"
  }
  ```
- **Response:**
  ```json
  {
    "id": "uuid",
    "title": "The Lord of the Rings",
    "author": "J.R.R. Tolkien",
    "isbn": "978-3-16-148410-0",
    "year": 2019,
    "category": "BIOGRAPHY"
  }
  ```

#### Get Book by ID

- **URL:** `/books/{id}`
- **Method:** `GET`
- **Response:**
  ```json
  {
    "id": "uuid",
    "title": "The Lord of the Rings",
    "author": "J.R.R. Tolkien",
    "isbn": "978-3-16-148410-0",
    "year": 2019,
    "category": "BIOGRAPHY"
  }
  ```

#### Update Book

- **URL:** `/books/{id}`
- **Method:** `PUT`
- **Request Body:**
  ```json
  {
    "title": "The Lord of the Rings",
    "author": "J.R.R. Tolkien",
    "isbn": "978-3-16-148410-0",
    "year": 2019,
    "category": "BIOGRAPHY"
  }
  ```
- **Response:**
  ```json
  {
    "id": "uuid",
    "title": "The Lord of the Rings",
    "author": "J.R.R. Tolkien",
    "isbn": "978-3-16-148410-0",
    "year": 2019,
    "category": "BIOGRAPHY"
  }
  ```

#### Delete Book

- **URL:** `/books/{id}`
- **Method:** `DELETE`
- **Response:** `204 No Content`

#### List Books

- **URL:** `/books`
- **Method:** `GET`
- **Query Parameters:**
    - `page` (default: 0)
    - `size` (default: 10)
- **Response:**
  ```json
  {
    "content": [
      {
        "id": "uuid",
        "title": "The Lord of the Rings",
        "author": "J.R.R. Tolkien",
        "isbn": "978-3-16-148410-0",
        "year": 2019,
        "category": "BIOGRAPHY"
      }
    ],
    "pageable": {
      "pageNumber": 0,
      "pageSize": 10
    },
    "totalElements": 1,
    "totalPages": 1
  }
  ```

#### Add Book by Title

- **URL:** `/books/add-by-title`
- **Method:** `POST`
- **Request Body:**
  ```json
  {
    "title": "The Lord of the Rings"
  }
  ```
- **Response:**
  ```json
  {
    "id": "uuid",
    "title": "The Lord of the Rings",
    "author": "J.R.R. Tolkien",
    "isbn": "978-3-16-148410-0",
    "year": 2019,
    "category": "BIOGRAPHY"
  }
  ```

#### Get Information by Title from Google API

- **URL:** `/books/info-by-title`
- **Method:** `GET`
- **Query Parameters:**
    - `title`
- **Response:**
  ```json
  {
    "title": "The Lord of the Rings",
    "author": "J.R.R. Tolkien",
    "isbn": "978-3-16-148410-0",
    "year": 2019,
    "category": "BIOGRAPHY"
  }
  ```

#### Create User

- **URL:** `/library/users`
- **Method:** `POST`
- **Request Body:**
  ```json
  {
    "name": "John Doe",
    "email": "john.doe@example.com",
    "password": "password123"
  }
  ```
- **Response:**
  ```json
  {
    "id": "uuid",
    "name": "John Doe",
    "email": "john.doe@example.com"
  }
  ```

#### Update User

- **URL:** `/library/users/{id}`
- **Method:** `PUT`
- **Request Body:**
  ```json
  {
    "name": "John Doe",
    "email": "john.doe@example.com",
    "password": "newpassword123"
  }
  ```
- **Response:**
  ```json
  {
    "id": "uuid",
    "name": "John Doe",
    "email": "john.doe@example.com"
  }
  ```

#### Delete User

- **URL:** `/library/users/{id}`
- **Method:** `DELETE`
- **Response:** `204 No Content`

#### Get User by ID

- **URL:** `/library/users/{id}`
- **Method:** `GET`
- **Response:**
  ```json
  {
    "id": "uuid",
    "name": "John Doe",
    "email": "john.doe@example.com"
  }
  ```

#### List Users

- **URL:** `/library/users`
- **Method:** `GET`
- **Response:**
  ```json
  [
    {
      "id": "uuid",
      "name": "John Doe",
      "email": "john.doe@example.com"
    }
  ]
  ```


## Testing

Run the tests using Maven:

```sh
mvn test
```

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.

## Error Handling

Errors are returned in the following format:

```json
{
  "error": "Error message",
  "status": 400
}
```

## Testing

Run the tests using Maven:

```sh
mvn test
```

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.




       




