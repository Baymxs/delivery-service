CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR (255),
    surname VARCHAR (255),
    patronymic VARCHAR (255),
    email VARCHAR (255),
    phone VARCHAR (255)
);

CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    address VARCHAR(255),
    delivery_date TIMESTAMP,
    name VARCHAR(255),
    state BOOLEAN,
    user_id INTEGER REFERENCES users(id)
);

CREATE TABLE tasks (
    id SERIAL PRIMARY KEY,
    date TIMESTAMP,
    state BOOLEAN,
    order_id INTEGER REFERENCES orders(id)
);

CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    cost INTEGER,
    description VARCHAR(1024),
    name VARCHAR(255)
);

CREATE TABLE orders_products (
    orders_id INTEGER,
    products_id INTEGER,
    PRIMARY KEY (orders_id, products_id)
);