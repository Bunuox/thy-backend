DROP TABLE IF EXISTS TBL_USER;
DROP TABLE IF EXISTS TBL_NOTE;

CREATE TABLE TBL_USER(
    id varchar(36) PRIMARY KEY,
    username VARCHAR(250) NOT NULL,
    email VARCHAR(250) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE TBL_NOTE(
    id varchar(36) PRIMARY KEY,
    user_id varchar(36) NOT NULL,
    title varchar(250) NOT NULL,
    description varchar(250),
    created_at DATE,
    updated_at DATE,
    due_date DATE,
    status varchar(1) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES TBL_USER(id)
);