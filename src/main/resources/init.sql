CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

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
    id   UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS message
(
    id   UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    user_id   int REFERENCES users (id),
    text      VARCHAR(255) NOT NULL,
    date_time TIMESTAMP    NOT NULL,
    topic_id  UUID REFERENCES topic (id)
);