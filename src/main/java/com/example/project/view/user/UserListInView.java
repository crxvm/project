package com.example.project.view.user;

import javax.validation.constraints.NotNull;

/**
 * Фильтр для поиска пользователей по параметрам
 */
public class UserListInView {
    /**
     * идентификатор офиса
     */
    @NotNull
    public Integer officeId;

    /**
     * Имя
     */
    public String firstName;

    /**
     * Фамилия
     */
    public String secondName;

    /**
     * Отчество
     */
    public String middleName;

    /**
     * Должность
     */
    public String position;

    /**
     * Код документа
     */
    public String docCode;

    /**
     * Код страны гражданства
     */
    public String citizenshipCode;
}
