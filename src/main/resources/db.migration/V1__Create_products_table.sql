-- V1__Create_products_table.sql
------------------------------------------------------------
-- Создание таблицы для хранения сущности Product
------------------------------------------------------------
CREATE TABLE products (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    ucode VARCHAR(255) UNIQUE,
    unit VARCHAR(50) NOT NULL,
    cost_per_unit DECIMAL(19, 2),
    price_per_unit DECIMAL(19, 2),
    producer VARCHAR(50),
    quantity DECIMAL(19, 2),
    status VARCHAR(50),
    industry_code VARCHAR(50),
    plan_period VARCHAR(255),
    production_date DATE,
    strategic BOOLEAN NOT NULL
);