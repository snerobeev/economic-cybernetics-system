package dev.nerobeev.economic_cybernetics_system.domain;

public enum UnitOfMeasure {
  CUB_M("м куб"),
  KILO("кг"),
  KWH("кВт·ч"),
  LIT("л"),
  PCS("шт"),
  SQU_M("м кв"),
  TON("тн");

  private final String symbol;

  UnitOfMeasure(String symbol) {
    this.symbol = symbol;
  }

  public String getSymbol() {
    return symbol;
  }
  }
