package com.example.project.view.office;

import javax.validation.constraints.NotNull;

/**
 * Отображение для обновления данных об оофисе
 */
public class OfficeUpdateView {
    /**
     * Уникальный идентификатор
     */
    @NotNull
    public Long id;

    /**
     * Название офиса
     */
    @NotNull
    public String name;

    /**
     * Адрес офисс
     */
    @NotNull
    public String address;

    /**
     * Телефон офиса
     */
    public String phone;

    /**
     * Статус активности
     */
    public Boolean isActive;
}
