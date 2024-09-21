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
- [Error Handling](#error-handling)
- [Testing](#testing)
- [License](#license)

## Requirements

- Java 11 or higher
- Maven 3.6.0 or higher
- Spring Boot 2.5.4 or higher

## Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/yourusername/library-manager.git
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