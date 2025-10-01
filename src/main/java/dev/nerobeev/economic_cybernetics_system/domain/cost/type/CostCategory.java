package dev.nerobeev.economic_cybernetics_system.domain.cost.type;

/*
 ** Группировка затрат по категориям (материальные, трудовые, услуги и т.д.)
 */

public enum CostCategory {
    MATERIAL("Материальные затраты"),
    LABOR("Трудовые затраты"),
    DEPRECIATION("Амортизация"),
    SERVICES("Услуги"),
    FINANCIAL("Финансовые затраты"),
    OTHER("Прочие затраты");

    private final String displayName;

    CostCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
