-- V3__Create_prod_cost_table.sql
------------------------------------------------------------
-- Создание таблицы для хранения статей производственных затрат
------------------------------------------------------------
CREATE TABLE production_costs (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    energy_cost DECIMAL(19, 2),
    labor_hours DECIMAL(19, 2),
    equipment_cost DECIMAL(19, 2),
    material_cost DECIMAL(19, 2),
    logistics_cost DECIMAL(19, 2),
    license_cost DECIMAL(19, 2),
    tax_cost DECIMAL(19, 2),
    social_cost DECIMAL(19, 2),
    amortization_cost DECIMAL(19, 2),
    equipment_maintenance_cost DECIMAL(19, 2),
    administrative_cost DECIMAL(19, 2),
    rental_cost DECIMAL(19, 2),
    communication_cost DECIMAL(19, 2),
    insurance_cost DECIMAL(19, 2),
    research_and_development_cost DECIMAL(19, 2),
    interest_cost DECIMAL(19, 2),
    eco_cost DECIMAL(19, 2)
);






