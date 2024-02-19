DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

CREATE TABLE Users(
    User_ID  INT PRIMARY KEY,
    Login    VARCHAR(50) not null ,
    Password VARCHAR(50) not null
);

CREATE TABLE Chatroom
(
    Chatroom_ID    INT PRIMARY KEY,
    Chatroom_Name  VARCHAR(50),
    id_User INT,
    CONSTRAINT id_user_convert FOREIGN KEY (id_User) REFERENCES Users (User_ID)
);

CREATE TABLE IF NOT EXISTS Message
(
    Message_ID       INT PRIMARY KEY,
    id_User          INT,
    id_Chatroom      INT,
    Message_Text     TEXT,
    Message_DateTime TIME,
    CONSTRAINT id_user_convert FOREIGN KEY (id_User) REFERENCES Users (User_ID),
    CONSTRAINT id_room_convert FOREIGN KEY (id_Chatroom) REFERENCES Chatroom (Chatroom_ID)
);
