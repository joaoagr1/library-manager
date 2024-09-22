![image](https://github.com/user-attachments/assets/b7144792-179c-4088-b7ff-59d126660571)Sure, here is a comprehensive README documentation for your Angular application:

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

![image](https://github.com/user-attachments/assets/f4f00c97-43a1-4b16-951e-2f9357cce363)

![image](https://github.com/user-attachments/assets/730646fa-027d-4d4c-ab27-1900f1dded73)

![image](https://github.com/user-attachments/assets/6ba954fb-632a-41b4-84e3-1d533fc20ba6)

![image](https://github.com/user-attachments/assets/be0449b1-dae0-480d-8cf1-31569a70281a)

