CREATE TABLE customers
(
    customer_id       SERIAL PRIMARY KEY,
    first_name        VARCHAR(128),
    last_name         VARCHAR(128),
    email             VARCHAR(128) UNIQUE,
    customer_password VARCHAR(128) NOT NULL,
    customer_role     VARCHAR(50)
)