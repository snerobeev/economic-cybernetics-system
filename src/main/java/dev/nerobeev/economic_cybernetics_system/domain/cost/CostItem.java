package dev.nerobeev.economic_cybernetics_system.domain.cost;

import java.time.LocalDate;

/*
 ** Конкретная запись о затрате: тип, сумма, дата, связь с продуктом Product
 */

public class CostItem {
    private CostType type;
    private Long amount;
    private LocalDate date;
    // ...
}
