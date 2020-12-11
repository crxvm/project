package com.example.project.view.organization;

import javax.validation.constraints.NotNull;

/**
 * Фильтр для поиска организаций
 */
public class OrganizationListInView {
    /**
     * Название организации
     */
    @NotNull
    public String name;

    /**
     * Индивидуальный номер налогоплательщика
     */
    public String inn;

    /**
     * Статус активности
     */
    public Boolean isActive;
}
