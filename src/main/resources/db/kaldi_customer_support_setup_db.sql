CREATE DATABASE IF NOT EXISTS kaldi_customer_support;

use kaldi_customer_support;

CREATE USER IF NOT EXISTS 'kaldi_admin'@'localhost' IDENTIFIED BY '+C7HZ+9z*yv%EfFM161';
GRANT ALL PRIVILEGES ON kaldi_customer_support.* TO 'kaldi_admin'@'localhost';
FLUSH PRIVILEGES;

# TABLE CREATION

CREATE TABLE IF NOT EXISTS Users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    user_password VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS Support (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    support_password VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS Room (
    id INT AUTO_INCREMENT PRIMARY KEY,
    room_name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS Chatroom (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    room_id INT NOT NULL,
    support_id INT,
    FOREIGN KEY (user_id) REFERENCES Users(id),
    FOREIGN KEY (room_id) REFERENCES Room(id),
    FOREIGN KEY (support_id) REFERENCES Support(id),
    UNIQUE(user_id, room_id) 
);

CREATE TABLE IF NOT EXISTS Message (
    id INT AUTO_INCREMENT PRIMARY KEY,
    chatroom_id INT NOT NULL,
    message VARCHAR(255) NOT NULL,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (chatroom_id) REFERENCES Chatroom(id)
);
# INSERTION OF TEST DATA

INSERT INTO Room (room_name) VALUES
('technics'),
('services'),
('talk');

INSERT INTO Users (email,user_password,username) VALUES
('testuser@gmail.com',"testpassword","testuser");

INSERT INTO Support (email,support_password,username) VALUES
('support@gmail.com',"supportpassword","support");

