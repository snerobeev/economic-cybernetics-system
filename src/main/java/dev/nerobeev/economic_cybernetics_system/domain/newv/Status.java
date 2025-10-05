package dev.nerobeev.economic_cybernetics_system.domain.newv;

public enum Status {
  RAW,                // Материал
  COMPONENT,          // Компонент
  PRODUCT,            // Продукт
  EXPORTED_PRODUCT,   // Экспортируемый продукт
  RECYCLED,           // Переработанный
  INVENTORY           // Складской остаток
}
