-- V5__Create_component_materials_join_table.sql
------------------------------------------------------------
-- Создание соединительной таблицы для связи Component <-> Material (@ManyToMany)
------------------------------------------------------------

CREATE TABLE component_materials (

    -- Колонка, ссылающаяся на родительскую таблицу Component
    component_id BIGINT NOT NULL,

    -- Колонка, ссылающаяся на связанную таблицу Material
    material_id BIGINT NOT NULL,

    -- Определение составного первичного ключа
    CONSTRAINT pk_component_materials PRIMARY KEY (component_id, material_id),

    -- Определение внешнего ключа к Component
    CONSTRAINT fk_compmat_component
        FOREIGN KEY (component_id)
        REFERENCES components (id)
        ON DELETE CASCADE,

    -- Определение внешнего ключа к Material
    CONSTRAINT fk_compmat_material
        FOREIGN KEY (material_id)
        REFERENCES materials (id)
        ON DELETE CASCADE
);