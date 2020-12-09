package com.example.project.view.organization;

import javax.validation.constraints.NotNull;

/**
 * Отображение для сохранения новой организации
 */
public class OrganizationSaveView {
    /**
     * Название организации
     */
    @NotNull
    public String name;

    /**
     * Полное название организации
     */
    @NotNull
    public String fullName;

    /**
     * Индивидуальной номер налогоплательщика
     */
    @NotNull
    public String inn;

    /**
     * Код причины постановки
     */
    @NotNull
    public String kpp;

    /**
     * Адрес организации
     */
    @NotNull
    public String address;

    /**
     * Телефон организации
     */
    public String phone;

    /**
     * Статус активности
     */
    public Boolean isActive;
}
