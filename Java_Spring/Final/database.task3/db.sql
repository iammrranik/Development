-- ==========================================
-- 1. DATABASE INITIALIZATION
-- ==========================================

-- Create the database
CREATE DATABASE IF NOT EXISTS librarydb;

-- Select the database to use
USE librarydb;

-- ==========================================
-- 2. TABLE CREATION (DDL)
-- ==========================================

-- Create Member Table
CREATE TABLE IF NOT EXISTS member (
    id    INT AUTO_INCREMENT PRIMARY KEY,
    name  VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(50)
);

-- Create Book Table
CREATE TABLE IF NOT EXISTS book (
    id              INT AUTO_INCREMENT PRIMARY KEY,
    title           VARCHAR(255) NOT NULL,
    author          VARCHAR(255) NOT NULL,
    availableCopies INT          NOT NULL,
    price           FLOAT        NOT NULL
);

-- Create Orders Table (Borrowing Records)
-- memberId and bookId are Foreign Keys
CREATE TABLE IF NOT EXISTS orders (
    id                 INT AUTO_INCREMENT PRIMARY KEY,
    memberId           INT   NOT NULL,
    bookId             INT   NOT NULL,
    quantity           INT   NOT NULL,
    borrowDate         DATE  NOT NULL,
    expectedReturnDate DATE  NOT NULL,
    actualReturnDate   DATE  DEFAULT NULL,
    fine               FLOAT DEFAULT 0.0,
    FOREIGN KEY (memberId) REFERENCES member(id),
    FOREIGN KEY (bookId)   REFERENCES book(id)
);

-- ==========================================
-- 3. DATA POPULATION (DML)
-- ==========================================

-- Insert sample Members
INSERT INTO member (name, email, phone) VALUES
('Rahim Ahmed', 'rahim@example.com', '01711000000'),
('Karim Ullah', 'karim@example.com', '01822000000'),
('Nila Sultana', 'nila@example.com', '01933000000');

-- Insert sample Books
INSERT INTO book (title, author, availableCopies, price) VALUES
('Java Programming', 'Herbert Schildt', 10, 550.0),
('Spring Boot in Action', 'Craig Walls', 5, 750.0),
('Clean Code', 'Robert C. Martin', 3, 1200.0);

-- ==========================================
-- 4. UTILITY & MAINTENANCE COMMANDS
-- ==========================================

-- Check all data
SELECT * FROM member;
SELECT * FROM book;
SELECT * FROM orders;

-- To clear all borrow history but keep members and books
TRUNCATE TABLE orders;

-- To delete specific records (Example)
DELETE FROM member WHERE id = 1;

-- To update stock manually (Example)
UPDATE book SET availableCopies = 15 WHERE id = 1;

-- To remove everything (USE WITH CAUTION)
-- DROP TABLE IF EXISTS orders;
-- DROP TABLE IF EXISTS member;
-- DROP TABLE IF EXISTS book;
-- DROP DATABASE IF EXISTS librarydb;

