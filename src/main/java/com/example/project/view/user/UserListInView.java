package com.example.project.view.user;

import javax.validation.constraints.NotNull;

/**
 * Отображение передачи параметров для полчения списка пользователей
 */
public class UserListInView {
    /**
     * Идентефикатор офиса
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
