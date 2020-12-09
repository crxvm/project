package com.example.project.view.office;

import javax.validation.constraints.NotNull;

/**
 * Отображение для сохранения нового офиса
 */
public class OfficeSaveView {
    /**
     * Идентификатор орагнизации
     */
    @NotNull
    public Long orgId;

    /**
     *
     */
    public String name;
    public String address;
    public String phone;
    public Boolean isActive;
}
