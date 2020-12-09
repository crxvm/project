package com.example.project.dao.user;


import com.example.project.model.User;
import com.example.project.model.UserDocument;

import java.util.List;

/**
 * DAO для управления сущностями пользователя
 */
public interface UserDao {
    /**
     * Получить сущность пользователя по полю id
     * @param id уникальный идентификатор
     * @return объект {@link User}
     */
    User getById(Long id);

    /**
     * Сохранить сущности пользователя и его документы
     * @param user {@link User}
     * @param userDocument документы пользователя {@link UserDocument}
     */
    void save(User user, UserDocument userDocument);

    /**
     * Обновить данные сущностей пользователя и его документы
     * @param user {@link User}
     * @param userDocument {@link UserDocument}
     */
    void update(User user, UserDocument userDocument);

    /**
     * Получить сущности пользователей по полям:
     * @param officeId идентификатор офиса
     * @param fName имя
     * @param lName фамилия
     * @param mName отчество
     * @param position должность
     * @param docCode код документа
     * @param citizenshipCode код страны гражданства
     * @return список объектов {@link User}
     */
    List<User> list(Integer officeId, String fName, String lName, String mName, String position, String docCode, String citizenshipCode);
}
