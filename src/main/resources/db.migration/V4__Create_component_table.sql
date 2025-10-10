-- V4__Create_components_table.sql
------------------------------------------------------------
-- Создание таблицы для сущности Component
------------------------------------------------------------

CREATE TABLE components (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    ucode VARCHAR(255) NOT NULL UNIQUE,
    unit VARCHAR(50) NOT NULL,
    cost_per_unit DECIMAL(19, 2) NOT NULL,
    price_rep_unit DECIMAL(19, 2) NOT NULL,
    producer VARCHAR(50) NOT NULL,
    quantity DECIMAL(19, 2) NOT NULL,
    status VARCHAR(50) NOT NULL,
    industry_code VARCHAR(50),
    plan_period VARCHAR(255),
    production_date DATE,
    strategic BOOLEAN
);