This document describes a **Java-based Bug Tracking System**
 designed for software development teams, outlining its **functionality and architecture**.
 It supports **role-based access control** for administrators, managers, developers, and testers, each with **distinct features** such as managing accounts, projects, and bug reports. 
The system follows an **MVC pattern** and uses a **MySQL database** to store entities like employees, projects, and bugs. **Setup instructions** and **workflow guides** are provided, alongside information on **testing** and **potential future enhancements**.
# Bug Tracking System

A console-based Java application for tracking and managing software bugs across projects.

## Features

- **User Authentication**: Secure login for Admin, Manager, and Employee roles
- **Admin Panel**: Manage employees (add, view, update, delete)
- **Manager Panel**: Manage projects and bug types, add bug reports
- **Employee Panel**: View and update bug statuses
- **Database Integration**: MySQL database for persistent data storage

## Prerequisites

- Java Development Kit (JDK) 17 or higher
- MySQL Server 8.0 or higher
- MySQL Connector/J (JDBC driver)

## Setup Instructions

### 1. Database Setup

1. Install MySQL Server if not already installed
2. Log in to MySQL as root or an administrator user
3. Run the database setup script:

```sql
source "<path_to_project>/Bug Tracking System/db/database_setup.sql"
```

This will create the database, tables, and insert sample data.

### 2. Configure Database Connection

Open the `DBConnection.java` file and update the connection details if needed:

```java
private static final String URL = "jdbc:mysql://localhost:3306/BDS";
private static final String USERNAME = "root";
private static final String PASSWORD = "root";
```

Replace `root` with your MySQL username and password.

### 3. Compile and Run

1. Navigate to the project directory
2. Compile the Java files:

```bash
javac -d bin -cp "<path_to_mysql_connector_jar>" Bug\ Tracking\ System/*.java Bug\ Tracking\ System/*/*.java
```

3. Run the application:

```bash
java -cp bin;"<path_to_mysql_connector_jar>" com.bugtracker.Main
```

## Default Login Credentials

### Admin
- Email: admin@bugtracker.com
- Password: admin123

### Manager
- Email: manager@bugtracker.com
- Password: manager123

### Developer
- Email: developer@bugtracker.com
- Password: developer123

### Tester
- Email: tester@bugtracker.com
- Password: tester123

## Project Structure

- `Main.java`: Application entry point
- `model/`: Contains entity classes (Employee, Project, BugReport, BugType)
- `dao/`: Data Access Objects for database operations
- `ui/`: User interface classes for different panels
- `db/`: Database setup script
