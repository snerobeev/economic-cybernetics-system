package dev.nerobeev.economic_cybernetics_system.domain.measure;

public enum UnitOfMeasure {
    KILOGRAM("кг"),
    TON("тн"),
    LITER("л"),
    CUBIC_METER("м куб"),
    PIECE("шт"),
    SQUARE_METER("м кв"),
    KWH("кВт·ч"),
    GIGACALORIE("Гкал");

    private final String symbol;

    UnitOfMeasure(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
