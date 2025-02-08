# 🏡 Cafofo Backend

## 📌 Project Overview
**Cafofo** (a Portuguese word meaning **cozy home**) is a **real estate marketplace** backend application that facilitates property listings, user interactions, and secure property transactions. The system includes **authentication, authorization, property management, offers, and financial reporting**.

This project is built with **Spring Boot**, following a **RESTful architecture**, and incorporates **JWT-based authentication**, **email notifications**, and **file upload capabilities**.

---

## 🚀 Technologies Used

- **Java 17 & Spring Boot 3.2.2** – Backend framework for handling business logic and APIs.
- **Spring Security & JWT** – Ensures secure authentication and role-based access control.
- **Spring Data JPA & Hibernate** – ORM for seamless database management.
- **PostgreSQL** – Primary database for storing property and user data.
- **Spring Boot Mail** – Sends email notifications to users.
- **Spring Context Support** – Provides advanced application context features.
- **ModelMapper** – Simplifies object mapping between DTOs and entities.
- **JUnit, Mockito** – Unit and integration testing to ensure system stability.
- **Swagger/OpenAPI** – API documentation for easy testing and integration.
- **Maven** – Dependency management and build automation.

---

## 📌 Features

- **User Authentication & Role-Based Access**
  - Secure login and JWT authentication.
  - Role-based access for **Admins, Owners, and Customers**.

- **Property Management**
  - Owners can **list, update, and delete** properties.
  - Customers can **browse, filter, and view property details**.

- **Offers & Transactions**
  - Customers can make offers on properties.
  - Owners can review, accept, or reject offers.

- **User Management**
  - Registration and authentication for **admins, owners, and customers**.
  - Secure password reset functionality.

- **File Uploads**
  - Owners can upload property images.
  - Secure file storage with a dedicated **upload_download** directory.

- **Email Notifications**
  - Users receive emails on **account registration, offers, and property updates**.

- **Database & Performance**
  - Uses **PostgreSQL** as the primary database.
  - **Optimized queries** with custom repository implementations.

---

## 📡 API Endpoints

### 🔑 **Authentication**
- `POST /api/v1/auth/authenticate` – Authenticate a user and return a JWT token.
- `POST /api/v1/auth/register-admin` – Register a new admin.
- `POST /api/v1/auth/register-owner` – Register a new property owner.
- `POST /api/v1/auth/register-customer` – Register a new customer.

### 🏡 **Properties**
- `GET /api/v1/properties` – Retrieve all available properties.
- `POST /api/v1/properties` – Add a new property.
- `PUT /api/v1/properties/{id}` – Update property details.
- `DELETE /api/v1/properties/{id}` – Remove a property.
- `GET /api/v1/properties/{id}` – Retrieve property details.

### 💰 **Offers & Transactions**
- `GET /api/v1/customers/{customerId}/offers` – Get customer’s offers.
- `POST /api/v1/owners/{ownerId}/properties/{propertyId}/offers` – Submit an offer for a property.
- `GET /api/v1/owners/{ownerId}/properties/{propertyId}/offers` – Retrieve offers for a specific property.
- `GET /api/v1/owners/{ownerId}/properties/{propertyId}/offers/{offerId}` – Retrieve a specific offer.
- `POST /api/v1/customers/{customerId}/make-payment` – Make a payment for a property.

### 📦 **File Uploads**
- `POST /api/v1/owners/{ownerId}/upload` – Upload property images.
- `GET /api/v1/owners/{ownerId}/properties/{propId}` – Retrieve uploaded property images.

### 👥 **User & Admin Management**
- `GET /api/v1/users/{id}` – Retrieve user details.
- `PATCH /api/v1/customers/{customerId}/update-profile` – Update customer profile.
- `POST /api/v1/admin/reset-password` – Reset admin password.
- `GET /api/v1/admin/owners-to-be-approved` – Retrieve unapproved owners.
- `PUT /api/v1/admin/owners/{id}` – Approve or reject an owner.

---

## ⚙️ Setup & Installation

### Prerequisites:
- **JDK 17**
- **Maven**
- **PostgreSQL**
- **Docker (Optional for containerization)**

### Steps:

1. **Clone the Repository:**
   ```sh
   git clone https://github.com/yourusername/cafofo-backend.git
   cd cafofo-backend

2. **Configure the Database**:
    ```yml      
    //Update the application.yml file:
    spring:
      datasource:
        url: jdbc:postgresql://localhost:5432/cafofo_backend_db
        username: postgres
        password: admin
   
 3. **Run the Application:**
       ```ssh      
      mvn spring-boot:run
