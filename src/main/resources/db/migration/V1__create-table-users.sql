CREATE TABLE users(
    id uuid NOT NULL,
    login varchar(100) NOT NULL,
    password varchar(255) NOT NULL,
    created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT users_pkey PRIMARY KEY (id)
);