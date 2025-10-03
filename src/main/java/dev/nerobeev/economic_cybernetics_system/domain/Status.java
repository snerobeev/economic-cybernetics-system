package dev.nerobeev.economic_cybernetics_system.domain;

public enum Status {
  RAW_MATERIAL,       // Материал
  COMPONENT,          // Компонент
  PRODUCT,            // Продукт
  EXPORTED_PRODUCT,   // Экспортируемый продукт
  RECYCLED,           // Переработанный
  INVENTORY           // Складской остаток
}
