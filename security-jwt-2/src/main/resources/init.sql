CREATE TABLE IF NOT EXISTS users
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255)        NOT NULL,
    email    VARCHAR(255) UNIQUE NOT NULL,
    role     VARCHAR(50)         NOT NULL
);


CREATE TABLE IF NOT EXISTS topic
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS message
(
    id        SERIAL PRIMARY KEY,
    user_id   int REFERENCES users (id),
    text      VARCHAR(255) NOT NULL,
    date_time TIMESTAMP    NOT NULL,
    topic_id  int REFERENCES topic (id)
);