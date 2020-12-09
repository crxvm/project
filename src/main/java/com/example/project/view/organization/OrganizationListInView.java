package com.example.project.view.organization;

import javax.validation.constraints.NotNull;

/**
 * Отображение для передачи параметров организаций
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
