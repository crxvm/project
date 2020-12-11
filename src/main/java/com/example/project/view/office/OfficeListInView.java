package com.example.project.view.office;

import javax.validation.constraints.NotNull;

/**
 * Фильтр для поиска офисов
 */
public class OfficeListInView {
    /**
     * id организации
     */
    @NotNull
    public Integer orgId;

    /**
     * Название офиса
     */
    public String name;

    /**
     * Телефон офиса
     */
    public String phone;

    /**
     * Статус активности
     */
    public Boolean isActive;
}
