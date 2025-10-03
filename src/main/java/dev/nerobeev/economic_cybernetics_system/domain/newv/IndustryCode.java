package dev.nerobeev.economic_cybernetics_system.domain.newv;


public enum IndustryCode {

    // ОКВЭД: A - Сельское, лесное хозяйство, охота, рыболовство
    AGRICULTURE("A", "Сельское, лесное хозяйство, охота, рыболовство"),

    // ОКВЭД: B - Добыча полезных ископаемых (песок)
    MINING("B", "Добыча полезных ископаемых"),

    // ОКВЭД: C - Обрабатывающие производства (многосоставные продукты)
    MANUFACTURING("C", "Обрабатывающие производства"),

    // ОКВЭД: F - Строительство
    CONSTRUCTION("F", "Строительство"),

    // ОКВЭД: G - Торговля, не является производством!!!
    TRADE("G", "Торговля оптовая и розничная");

    private final String mark;
    private final String description;

    IndustryCode(String mark, String description) {
        this.mark = mark;
        this.description = description;
    }

    public String getMark() {
        return mark;
    }

    public String getDescription() {
        return description;
    }
}
