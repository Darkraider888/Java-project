CREATE DATABASE dev_helper_db;
USE dev_helper_db;

CREATE TABLE tasks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    task_name VARCHAR(255) NOT NULL,
    is_done BOOLEAN DEFAULT FALSE
);

CREATE TABLE contacts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    role VARCHAR(100),
    email VARCHAR(150)
);

CREATE TABLE snippets (
    id INT AUTO_INCREMENT PRIMARY KEY,
    code_text TEXT
);

CREATE TABLE progress_logs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    log_text TEXT,
    log_date DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE links (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(150),
    url VARCHAR(500)
);

-- Some sample data so your app has something to show right away
INSERT INTO tasks (task_name) VALUES ('Install IntelliJ'), ('Install JDK 26'), ('Install MySQL');
INSERT INTO contacts (name, role, email) VALUES ('Mehedi Hasan Siddique', 'Backend Dev', 'mehedi@company.com');
INSERT INTO links (title, url) VALUES ('Project Docs', 'https://example.com/docs');