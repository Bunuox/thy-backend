DROP TABLE IF EXISTS TBL_USER;
DROP TABLE IF EXISTS TBL_NOTE;

CREATE TABLE TBL_USER(
id VARCHAR(36) PRIMARY KEY,
username VARCHAR(250) NOT NULL,
mail VARCHAR(250) NOT NULL,
password VARCHAR(30) NOT NULL,
CONSTRAINT unique_username UNIQUE (username),
CONSTRAINT unique_mail UNIQUE (mail)
);

CREATE TABLE TBL_NOTE(
    id varchar(36) PRIMARY KEY,
    user_id varchar(36) NOT NULL,
    title varchar(250) NOT NULL,
    desc varchar(250),
    created_at DATE,
    updated_at DATE,
    due_date DATE,
    status varchar(1) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES TBL_USER(id)
);

CREATE SEQUENCE T_USER_SEQ
    START WITH 1
    INCREMENT BY 1
    NO CACHE;

CREATE SEQUENCE T_NOTES_SEQ
    START WITH 1
    INCREMENT BY 1
    NO CACHE;