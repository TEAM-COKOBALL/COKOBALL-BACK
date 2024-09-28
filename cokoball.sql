show databases;
create database cokoball;
use cokoball;

CREATE TABLE User (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
username VARCHAR(255) NOT NULL UNIQUE,
password VARCHAR(255) NOT NULL,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
level int
);

CREATE TABLE Emotion (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
type VARCHAR(255) NOT NULL
);

CREATE TABLE Solution (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
emotion_id BIGINT NOT NULL,
content VARCHAR(255) NOT NULL,
CONSTRAINT FK_emotion FOREIGN KEY (emotion_id) REFERENCES Emotion(id)
);

CREATE TABLE Diary (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
diary_id VARCHAR(255) NOT NULL,
content TEXT NOT NULL,
check_solution BOOLEAN DEFAULT FALSE,
user_id BIGINT,
emotion_id BIGINT,
create_date DATE,
CONSTRAINT FK_User_Diary FOREIGN KEY (user_id) REFERENCES User(id),
CONSTRAINT FK_Emotion_Diary FOREIGN KEY (emotion_id) REFERENCES Emotion(id)
);

insert into Emotion value(1, 'Joy');
insert into Emotion value(2, 'Sadness');
insert into Emotion value(3, 'Anger');
insert into Emotion value(4, 'Lethargy');
insert into Emotion value(5, 'Fear');

SELECT * FROM Emotion