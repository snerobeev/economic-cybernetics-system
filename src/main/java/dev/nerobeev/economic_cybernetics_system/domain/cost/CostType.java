package dev.nerobeev.economic_cybernetics_system.domain.cost;

/*
 ** СПРАВОЧНИК конкретных видов затрат (энергия, зарплата, реклама и т.д.)
 */

public enum CostType {
    RAW_MATERIALS("Сырье и материалы", "Основные материалы для производства", CostCategory.MATERIAL, true, true),
    ENERGY("Энергия", "Электроэнергия, тепловая энергия", CostCategory.MATERIAL, true, true),
    DIRECT_LABOR("Прямые трудовые затраты", "Заработная плата основных рабочих", CostCategory.LABOR, true, true),
    DEPRECIATION_EQUIPMENT("Амортизация оборудования", "Износ производственного оборудования", CostCategory.DEPRECIATION, false, false),
    TRANSPORT_SERVICES("Транспортные услуги", "Услуги по перевозке", CostCategory.SERVICES, false, false),
    INTEREST_EXPENSES("Проценты по кредитам", "Плата за заемные средства", CostCategory.FINANCIAL, false, false),
    OTHER("Прочие затраты", "Другие производственные затраты", CostCategory.OTHER, false, false);

    private final String displayName;
    private final String description;
    private final CostCategory category;
    private final boolean isDirect;
    private final boolean isVariable;

    CostType(String displayName, String description, CostCategory category, boolean isDirect, boolean isVariable) {
        this.displayName = displayName;
        this.description = description;
        this.category = category;
        this.isDirect = isDirect;
        this.isVariable = isVariable;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }

    public CostCategory getCategory() {
        return category;
    }

    public boolean isDirect() {
        return isDirect;
    }

    public boolean isVariable() {
        return isVariable;
    }
}
