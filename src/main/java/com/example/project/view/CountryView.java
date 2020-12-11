package com.example.project.view;

import javax.validation.constraints.NotNull;

/**
 * Отображение для стран из справочника
 */
public class CountryView {
    /**
     * Уникальный идентификатор
     */
    @NotNull
    public String id;

    /**
     * Код страны
     */
    public String citizenshipCode;

    /**
     * Название страны
     */
    public String citizenshipName;
}
