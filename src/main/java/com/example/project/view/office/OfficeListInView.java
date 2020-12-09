package com.example.project.view.office;

import javax.validation.constraints.NotNull;

/**
 * Отображение для получения списка офисов по фильтрам
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
