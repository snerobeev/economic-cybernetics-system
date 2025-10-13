-- V3__Create_prod_cost_table.sql
------------------------------------------------------------
-- Создание таблицы для хранения статей производственных затрат
------------------------------------------------------------
CREATE TABLE production_costs (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    energy_cost BIGINT NOT NULL,
    labor_hours BIGINT NOT NULL,
    equipment_cost BIGINT NOT NULL,
    material_cost BIGINT NOT NULL,
    logistics_cost BIGINT NOT NULL,
    license_cost BIGINT NOT NULL,
    tax_cost BIGINT NOT NULL,
    social_cost BIGINT NOT NULL,
    amortization_cost BIGINT NOT NULL,
    equipment_maintenance_cost BIGINT NOT NULL,
    administrative_cost BIGINT NOT NULL,
    rental_cost BIGINT NOT NULL,
    communication_cost BIGINT NOT NULL,
    insurance_cost BIGINT NOT NULL,
    research_and_development_cost BIGINT NOT NULL,
    interest_cost BIGINT NOT NULL,
    eco_cost BIGINT NOT NULL
);






