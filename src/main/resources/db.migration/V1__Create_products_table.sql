-- V1__Create_products_table.sql
------------------------------------------------------------
-- Создание таблицы для хранения сущности Product
------------------------------------------------------------
CREATE TABLE products (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    ucode VARCHAR(255) UNIQUE,
    unit VARCHAR(50) NOT NULL,
    cost_per_unit BIGINT NOT NULL,
    price_per_unit BIGINT NOT NULL,
    producer VARCHAR(50),
    quantity BIGINT NOT NULL,
    status VARCHAR(50) NOT NULL,
    industry_code VARCHAR(50) NOT NULL,
    plan_period VARCHAR(255) NOT NULL,
    production_date DATE,
    strategic BOOLEAN NOT NULL
);