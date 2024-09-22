# Library Manager

Library Manager is an Angular application designed to manage book loans in a library. It allows users to view, edit, and manage loans, including details about users and books.

## Table of Contents

- [Library Manager](#library-manager)
  - [Table of Contents](#table-of-contents)
  - Features
  - Prerequisites
  - Installation
  - [Running the Application](#running-the-application)
  - [Project Structure](#project-structure)
  - Services
  - Components
  - Models
  - Styling
  - [Error Handling](#error-handling)
  - Contributing
  - License


## Prerequisites

Before you begin, ensure you have met the following requirements:

- Node.js and npm installed
- Angular CLI installed globally

## Installation

1. Clone the repository:

```bash
git clone https://github.com/joaoagr1/library-manager.git
cd library-manager
```

2. Install dependencies:

```bash
npm install
```

## Running the Application

To run the application locally, use the following command:
(Its important use npm install instead of ng serve due to proxy config)

```bash
npm start
```

Navigate to `http://localhost:4200/` in your browser to see the application running.

## Project Structure

The project structure is as follows:

```
src/
├── app/
│   ├── models/
│   │   ├── book.model.ts
│   │   ├── loan.model.ts
│   │   └── user.model.ts
│   ├── services/
│   │   ├── book-service.ts
│   │   ├── loan-service.ts
│   │   └── user-service.ts
│   ├── loans/
│   │   ├── edit-loan-dialog/
│   │   │   ├── edit-loan-dialog.component.html
│   │   │   ├── edit-loan-dialog.component.ts
│   │   │   └── edit-loan-dialog.component.css
│   │   ├── loans.component.html
│   │   ├── loans.component.ts
│   │   └── loans.component.css
│   ├── books/
│   │   ├── book-list/
│   │   │   ├── book-list.component.html
│   │   │   ├── book-list.component.ts
│   │   │   └── book-list.component.css
│   │   ├── book-detail/
│   │   │   ├── book-detail.component.html
│   │   │   ├── book-detail.component.ts
│   │   │   └── book-detail.component.css
│   ├── users/
│   │   ├── user-list/
│   │   │   ├── user-list.component.html
│   │   │   ├── user-list.component.ts
│   │   │   └── user-list.component.css
│   │   ├── user-detail/
│   │   │   ├── user-detail.component.html
│   │   │   ├── user-detail.component.ts
│   │   │   └── user-detail.component.css
│   ├── home/
│   │   ├── home.component.html
│   │   ├── home.component.ts
│   │   └── home.component.css
│   ├── app.component.html
│   ├── app.component.ts
│   └── app.module.ts
├── assets/
├── environments/
├── index.html
├── main.ts
└── styles.css
```

Screens:

![image](https://github.com/user-attachments/assets/b7e9521c-862f-4fd5-82da-5c24cabc9daf)

![image](https://github.com/user-attachments/assets/68b32144-1b4c-409c-84de-d22025b0f3fc)

![image](https://github.com/user-attachments/assets/92fede05-ca66-4929-b472-309270091649)

![image](https://github.com/user-attachments/assets/4be83444-7122-4c1b-9bd4-e5d85b5b38b3)

