DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS articles;

CREATE TABLE users (
    userId VARCHAR(60) NOT NULL,
    password VARCHAR(60) NOT NULL,
    name VARCHAR(60) NOT NULL,
    email VARCHAR(60) NOT NULL,
    PRIMARY KEY (userId)
);

CREATE TABLE articles (
    id INTEGER AUTO_INCREMENT NOT NULL ,
    writer VARCHAR(60) NOT NULL,
    title VARCHAR(60) NOT NULL,
    contents VARCHAR(500) NOT NULL,
    PRIMARY KEY (id)
);
