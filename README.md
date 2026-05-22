# CakeBackers Management System

## Overview
CakeBackers is a Java console-based bakery management application built using Core Java and JDBC with MySQL. The project follows a layered architecture using:

- UI Layer
- Service Layer
- DAO Layer
- Model Layer
- Utility Classes
- Exception Handling

The system helps manage:

- Cakes
- Customers
- Orders
- Billing
- Feedback

---

# Project Structure

```text
CakeBackers-main/
│
├── src/
│   ├── dao/
│   ├── exception/
│   ├── model/
│   ├── service/
│   ├── ui/
│   └── util/
│
├── bin/
├── .settings/
├── .classpath
└── .project
```

---

# Technologies Used

- Java
- JDBC
- MySQL
- Eclipse IDE

---

# Features

## 1. Cake Management
- Add cake
- View cake by ID
- View all cakes
- Modify cake details
- Deactivate cake

## 2. Customer Management
- Add customer
- View customer
- View all customers
- Update customer details
- Delete customer

## 3. Order Management
- Place order
- View order
- View all orders
- Cancel order

## 4. Billing Management
- Generate bill
- View billing details
- View all bills

## 5. Feedback Management
- Add feedback
- View feedback
- View all feedback

---

# Package Description

## dao
Contains database operations using JDBC.

## model
Contains entity/model classes.

## service
Contains business logic.

## util
Utility/helper classes.

## exception
Custom exception classes.

## ui
Contains the main application entry point.

---

# Database Configuration

Update the database credentials inside:

```java
src/util/DatabaseUtil.java
```

Example:

```java
private static final String URL = "jdbc:mysql://localhost:3306/cakebackers";
private static final String USERNAME = "root";
private static final String PASSWORD = "your_password";
```

---

# Suggested MySQL Tables

## cakes

```sql
CREATE TABLE cakes (
    cake_id INT PRIMARY KEY AUTO_INCREMENT,
    cake_name VARCHAR(100),
    flavor VARCHAR(100),
    price DOUBLE,
    is_active BOOLEAN
);
```

## customers

```sql
CREATE TABLE customers (
    customer_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_name VARCHAR(100),
    phone VARCHAR(15),
    email VARCHAR(100)
);
```

## orders

```sql
CREATE TABLE orders (
    order_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT,
    cake_id INT,
    quantity INT,
    order_date DATE,
    status VARCHAR(50)
);
```

## billing

```sql
CREATE TABLE billing (
    bill_id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT,
    total_amount DOUBLE,
    billing_date DATE
);
```

## feedback

```sql
CREATE TABLE feedback (
    feedback_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT,
    comments VARCHAR(255),
    rating INT
);
```

---

# How to Run

## Step 1: Clone the Repository

```bash
git clone <repository-url>
```

## Step 2: Import Project

Import the project into Eclipse IDE.

## Step 3: Configure MySQL

- Create the database
- Create required tables
- Update database credentials in `DatabaseUtil.java`

## Step 4: Run Application

Run:

```text
src/ui/MainUI.java
```

---

# Important Concepts Used

- JDBC Connectivity
- CRUD Operations
- Exception Handling
- Collections Framework
- Layered Architecture
- Scanner Input Handling
- PreparedStatement
- ResultSet
- OOP Principles

---
