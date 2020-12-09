package com.example.project.view.user;

import java.util.Date;

/**
 * Отображение пользователя
 */
public class UserView {
    /**
     * Уникальный идентификатор
     */
    public Long id;

    /**
     * Имя
     */
    public String firstName;

    /**
     * Идентификатор офиса
     */
    public Integer officeId;

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
     * Телефон
     */
    public String phone;

    /**
     * Статус идентификации
     */
    public Boolean isIdentified;

    /**
     * Название гражданства
     */
    public String citizenshipName;

    /**
     * Название докмента
     */
    public String docName;

    /**
     * Номер документа
     */
    public String docNumber;

    /**
     * Дата выдачи документа
     */
    public Date docDate;

}
