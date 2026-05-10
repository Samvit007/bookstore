# 📚 Online Bookstore (Full-Stack)

A modern, full-stack online bookstore application built with **Java Spring Boot** and a rich, responsive frontend. This project features a complete shopping experience, from browsing books to secure checkout, all backed by a **MySQL** database.

## ✨ Features

- **Dynamic Homepage**: Browse a wide collection of books with high-quality covers and details.
- **Advanced Search & Filter**: Find books by title, author, or genre instantly.
- **Book Details**: Comprehensive view of book descriptions, specifications, and user reviews.
- **User Authentication**: Secure Login and Signup system with validation.
- **Interactive Shopping Cart**: Add, remove, and update quantities of books in real-time.
- **Seamless Checkout**: Multi-step checkout process with order confirmation.
- **Permanent Storage**: All data (books, users, reviews, orders) is persisted in a MySQL database.
- **Rich UI/UX**: Premium design with glassmorphism effects, smooth transitions, and responsive layouts.

## 🛠️ Tech Stack

### Frontend
- **HTML5 & CSS3**: Custom styles with modern aesthetics.
- **JavaScript (ES6+)**: Dynamic UI updates and API interactions.
- **Google Fonts**: Inter & Outfit for premium typography.

### Backend
- **Java 21**
- **Spring Boot 3.5.0**
- **Spring Data JPA**: For robust database management.
- **Spring Web MVC**: REST API development.
- **MySQL**: Persistent relational database.
- **Maven**: Project management and build tool.

## 🚀 Getting Started

### Prerequisites
- **JDK 21** or higher.
- **MySQL Server** installed and running.
- **Maven** (included via `./mvnw`).

### 1. Database Setup
1. Open your MySQL Command Line or Workbench.
2. Create the project database:
   ```sql
   CREATE DATABASE bookstore_db;
   ```

### 2. Configuration
Update the `src/main/resources/application.properties` file with your MySQL credentials:
```properties
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 3. Running the Project
Navigate to the project root and run:
```powershell
.\mvnw.cmd spring-boot:run
```
The application will start at `http://localhost:8080`.

## 📂 Project Structure

```
bookstore/
├── src/
│   ├── main/
│   │   ├── java/com/example/bookstore/
│   │   │   ├── controller/   # REST API Endpoints
│   │   │   ├── model/        # JPA Entities
│   │   │   ├── repository/   # Data Access Interfaces
│   │   │   └── service/      # Business Logic
│   │   └── resources/
│   │       ├── static/       # Frontend (HTML, CSS, JS)
│   │       └── application.properties
└── pom.xml
```

## 📸 Screenshots

*(Add your screenshots here)*
### Homepage
![Homepage](https://raw.githubusercontent.com/Samvit007/bookstore/main/src/main/resources/static/screenshots/home.png)

### Book Details
![Book Details](https://raw.githubusercontent.com/Samvit007/bookstore/main/src/main/resources/static/screenshots/details.png)

### Shopping Cart
![Cart](https://raw.githubusercontent.com/Samvit007/bookstore/main/src/main/resources/static/screenshots/cart.png)

### Checkout
![Checkout](https://raw.githubusercontent.com/Samvit007/bookstore/main/src/main/resources/static/screenshots/checkout.png)



## 🛣️ API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/books` | Fetch all books (supports search/genre) |
| `GET` | `/api/books/{id}` | Get book details |
| `POST` | `/api/auth/register` | User registration |
| `POST` | `/api/auth/login` | User login |
| `GET` | `/api/cart` | View current cart |
| `POST` | `/api/cart` | Add item to cart |
| `POST` | `/api/checkout` | Place an order |

---
*Created with ❤️ by Antigravity*
