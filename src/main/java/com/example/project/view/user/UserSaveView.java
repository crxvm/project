package com.example.project.view.user;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Отображение для сохранения нового пользователя
 */
public class UserSaveView {
    /**
     * идентификатор офиса
     */
    @NotNull
    public Integer officeId;

    /**
     * Имя
     */
    @NotNull
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
    @NotNull
    public String position;

    /**
     * Телефон
     */
    public String phone;

    /**
     * Код документа
     */
    public String docCode;

    /**
     * Название документа
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

    /**
     * Код гражданства страны
     */
    public String citizenshipCode;

    /**
     * Статус идентификации
     */
    public Boolean isIdentified;

}
