-- V6__Create_product_components_join_table.sql
------------------------------------------------------------
-- Создание соединительной таблицы для связи Product <-> Component (@ManyToMany)
------------------------------------------------------------

CREATE TABLE product_components (

    -- Колонка, ссылающаяся на родительскую таблицу Product
    product_id BIGINT NOT NULL,

    -- Колонка, ссылающаяся на связанную таблицу Component
    component_id BIGINT NOT NULL,

    -- Определение составного первичного ключа
    CONSTRAINT pk_product_components PRIMARY KEY (product_id, component_id),

    -- Определение внешнего ключа к Product
    CONSTRAINT fk_prodcomp_product
        FOREIGN KEY (product_id)
        REFERENCES products (id)
        ON DELETE CASCADE,

    -- Определение внешнего ключа к Component
    CONSTRAINT fk_prodcomp_component
        FOREIGN KEY (component_id)
        REFERENCES components (id)
        ON DELETE CASCADE
);