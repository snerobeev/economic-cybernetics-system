package dev.nerobeev.economic_cybernetics_system.service;

import java.math.BigDecimal;

public interface CostReportable { // реализовать в сервисе

    // Методы для расчета отдельных статей затрат
    BigDecimal getLaborCost();        // Затраты на труд (Зарплата)

    BigDecimal getEnergyCost();       // Энергетические затраты (Электричество, Газ)

    BigDecimal getTransportCost();    // Транспортные затраты (Логистика, Доставка)

    BigDecimal getEnvironmentalCost();// Экологические сборы, утилизация

    BigDecimal getAmortizationCost(); // Амортизация оборудования

    BigDecimal getSocialCost(); // Социальные выплаты

    BigDecimal getAddedCost(); // Добавленная стоимость - прибыль

    default BigDecimal getTotalCost() {
        return getLaborCost()
                .add(getEnergyCost())
                .add(getTransportCost())
                .add(getEnvironmentalCost())
                .add(getAmortizationCost())
                .add(getSocialCost())
                .add(getAddedCost());
    }
}
