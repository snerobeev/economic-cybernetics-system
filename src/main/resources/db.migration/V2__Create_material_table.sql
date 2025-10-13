-- V2__Create_material_table.sql
------------------------------------------------------------
-- Создание таблицы для хранения сущности Material
------------------------------------------------------------

CREATE TABLE materials (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    ucode VARCHAR(255) NOT NULL UNIQUE,
    unit VARCHAR(50) NOT NULL,
    cost_per_unit BIGINT NOT NULL,
    price_per_unit BIGINT NOT NULL,
    producer VARCHAR(50) NOT NULL,
    quantity BIGINT NOT NULL,
    status VARCHAR(50) NOT NULL,
    industry_code VARCHAR(50) NOT NULL,
    plan_period VARCHAR(255),
    production_date DATE NOT NULL,
    strategic BOOLEAN NOT NULL,
    product_id BIGINT,
    CONSTRAINT fk_materials_product FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);
