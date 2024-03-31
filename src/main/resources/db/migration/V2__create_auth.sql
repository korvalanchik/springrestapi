CREATE TABLE users (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(100) NOT NULL,
    enabled BOOLEAN NOT NULL
);

CREATE TABLE authorities (
    username VARCHAR(50),
    authority VARCHAR(50),
    FOREIGN KEY (username) REFERENCES users(username),
    PRIMARY KEY (username, authority)
);

--  user has role USER and password is codejava.
--  admin has role ADMIN with password is nimda.
INSERT INTO users VALUES ( 'user', '$2a$10$XptfskLsT1l/bRTLRiiCgejHqOpgXFreUnNUa35gJdCr2v2QbVFzu', true );
INSERT INTO users VALUES ( 'admin', '$2a$10$zxvEq8XzYEYtNjbkRsJEbukHeRx3XS6MDXHMu8cNuNsRfZJWwswDy', true );

INSERT INTO authorities VALUES ( 'user', 'USER' );
INSERT INTO authorities VALUES ( 'admin', 'ADMIN' );
