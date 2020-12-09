package com.example.project.view.organization;

import javax.validation.constraints.NotNull;

/**
 * Отображение организации
 */
public class OrganizationView {
    /**
     * Уникальный идентификатор
     */
    @NotNull
    public Long id;

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
     * Индивидуальный номер налогоплательщика
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
