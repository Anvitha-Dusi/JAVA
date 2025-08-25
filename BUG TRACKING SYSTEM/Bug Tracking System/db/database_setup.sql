-- Create the database if it doesn't exist
CREATE DATABASE IF NOT EXISTS BDS;

-- Use the database
USE BDS;

-- Create Employee table
CREATE TABLE IF NOT EXISTS Employee (
    empCode INT PRIMARY KEY,
    empName VARCHAR(50) NOT NULL,
    empEmail VARCHAR(50) UNIQUE NOT NULL,
    empPassword VARCHAR(50) NOT NULL,
    gender VARCHAR(10) CHECK (gender IN ('Male', 'Female', 'Other')),
    DOB VARCHAR(10),
    mobileNo BIGINT,
    Role VARCHAR(20) CHECK (Role IN ('Admin', 'Manager', 'Developer', 'Tester'))
);

-- Create Project table
CREATE TABLE IF NOT EXISTS Project (
    projectID INT PRIMARY KEY,
    projectName VARCHAR(100) NOT NULL,
    SDate VARCHAR(10),
    EDate VARCHAR(10),
    projectDec TEXT
);

-- Create BugType table
CREATE TABLE IF NOT EXISTS BugType (
    bugCode INT PRIMARY KEY,
    bugCategory VARCHAR(50) NOT NULL,
    bugSeverity VARCHAR(20) CHECK (bugSeverity IN ('Low', 'Medium', 'High', 'Critical'))
);

-- Create BugReport table
CREATE TABLE IF NOT EXISTS BugReport (
    bugNo INT PRIMARY KEY,
    bugCode INT,
    projectID INT,
    TCode INT,
    ECode INT,
    status VARCHAR(20) CHECK (status IN ('pending', 'resolved', 'rejected')),
    bugDes TEXT,
    FOREIGN KEY (bugCode) REFERENCES BugType(bugCode),
    FOREIGN KEY (projectID) REFERENCES Project(projectID),
    FOREIGN KEY (TCode) REFERENCES Employee(empCode),
    FOREIGN KEY (ECode) REFERENCES Employee(empCode)
);

-- Create AssignProject table
CREATE TABLE IF NOT EXISTS AssignProject (
    assignID INT PRIMARY KEY AUTO_INCREMENT,
    projectID INT,
    empCode INT,
    FOREIGN KEY (projectID) REFERENCES Project(projectID),
    FOREIGN KEY (empCode) REFERENCES Employee(empCode)
);

-- Insert default admin user
INSERT INTO Employee (empCode, empName, empEmail, empPassword, gender, DOB, mobileNo, Role)
VALUES (1001, 'Admin User', 'admin@bugtracker.com', 'admin123', 'Male', '01/01/1990', 1234567890, 'Admin')
ON DUPLICATE KEY UPDATE empName = 'Admin User';

-- Insert sample data for testing
-- Sample Manager
INSERT INTO Employee (empCode, empName, empEmail, empPassword, gender, DOB, mobileNo, Role)
VALUES (1002, 'Manager User', 'manager@bugtracker.com', 'manager123', 'Female', '02/02/1992', 9876543210, 'Manager')
ON DUPLICATE KEY UPDATE empName = 'Manager User';

-- Sample Developer
INSERT INTO Employee (empCode, empName, empEmail, empPassword, gender, DOB, mobileNo, Role)
VALUES (1003, 'Developer User', 'developer@bugtracker.com', 'developer123', 'Male', '03/03/1995', 5555555555, 'Developer')
ON DUPLICATE KEY UPDATE empName = 'Developer User';

-- Sample Tester
INSERT INTO Employee (empCode, empName, empEmail, empPassword, gender, DOB, mobileNo, Role)
VALUES (1004, 'Tester User', 'tester@bugtracker.com', 'tester123', 'Female', '04/04/1993', 6666666666, 'Tester')
ON DUPLICATE KEY UPDATE empName = 'Tester User';

-- Sample Project
INSERT INTO Project (projectID, projectName, SDate, EDate, projectDec)
VALUES (101, 'E-Commerce Website', '01/01/2023', '12/31/2023', 'Online shopping platform with user authentication and payment processing')
ON DUPLICATE KEY UPDATE projectName = 'E-Commerce Website';

-- Sample Bug Types
INSERT INTO BugType (bugCode, bugCategory, bugSeverity)
VALUES (201, 'UI Issue', 'Low')
ON DUPLICATE KEY UPDATE bugCategory = 'UI Issue';

INSERT INTO BugType (bugCode, bugCategory, bugSeverity)
VALUES (202, 'Functional Error', 'Medium')
ON DUPLICATE KEY UPDATE bugCategory = 'Functional Error';

INSERT INTO BugType (bugCode, bugCategory, bugSeverity)
VALUES (203, 'Security Vulnerability', 'Critical')
ON DUPLICATE KEY UPDATE bugCategory = 'Security Vulnerability';

-- Sample Bug Reports
INSERT INTO BugReport (bugNo, bugCode, projectID, TCode, ECode, status, bugDes)
VALUES (301, 201, 101, 1004, 1003, 'pending', 'Login button not aligned properly on mobile view')
ON DUPLICATE KEY UPDATE bugDes = 'Login button not aligned properly on mobile view';

INSERT INTO BugReport (bugNo, bugCode, projectID, TCode, ECode, status, bugDes)
VALUES (302, 202, 101, 1004, 1003, 'resolved', 'Cart total not updating when items are removed')
ON DUPLICATE KEY UPDATE bugDes = 'Cart total not updating when items are removed';

INSERT INTO BugReport (bugNo, bugCode, projectID, TCode, ECode, status, bugDes)
VALUES (303, 203, 101, 1004, 1003, 'pending', 'User passwords stored in plaintext')
ON DUPLICATE KEY UPDATE bugDes = 'User passwords stored in plaintext';

-- Sample Project Assignments
INSERT INTO AssignProject (projectID, empCode)
VALUES (101, 1002)
ON DUPLICATE KEY UPDATE projectID = 101;

INSERT INTO AssignProject (projectID, empCode)
VALUES (101, 1003)
ON DUPLICATE KEY UPDATE projectID = 101;

INSERT INTO AssignProject (projectID, empCode)
VALUES (101, 1004)
ON DUPLICATE KEY UPDATE projectID = 101;