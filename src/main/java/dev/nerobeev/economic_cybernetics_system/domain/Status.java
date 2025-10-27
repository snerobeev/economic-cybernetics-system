package dev.nerobeev.economic_cybernetics_system.domain;

public enum Status {
  RAW,                // Сырой материал (нефть, руда, древесина и тд)
  COMPONENT,          // Компонент (состоящий из множества материалов)
  PRODUCT,            // Продукт (состоящий из множества компонентов и материалов, обработанный материал)
  EXPORTED_PRODUCT,   // Экспортируемый продукт
  RECYCLED,           // Переработанный
  INVENTORY           // Складской остаток
}
