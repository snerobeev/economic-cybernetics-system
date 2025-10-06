package dev.nerobeev.economic_cybernetics_system.domain;


public enum IndustryCode {

    // ОКВЭД: A - Сельское, лесное хозяйство, охота, рыболовство
    AGRICULTURE("A", "Сельское, лесное хозяйство, охота, рыболовство"),

    // ОКВЭД: B - Добыча полезных ископаемых (песок)
    MINING("B", "Добыча полезных ископаемых"),

    // ОКВЭД: C - Обрабатывающие производства (многосоставные продукты)
    // Пищевая промышленность, текстильное производство, металлургия, машиностроение, электроника
    MANUFACTURING("C", "Обрабатывающие производства"),

    // ОКВЭД: D - Энергетика
    ENERGY("D", "Энергетика"),

    // ОКВЭД: E - Водоснабжение
    WATER("E", "Водоснабжение"),

    // ОКВЭД: F - Строительство
    CONSTRUCTION("F", "Строительство"),

    // ОКВЭД: G - Торговля, не является производством!!!
    TRADE("G", "Торговля оптовая и розничная"),

    // ОКВЭД: H - Транспорт
    TRANSPORT("H", "Транспорт и логистика");

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
