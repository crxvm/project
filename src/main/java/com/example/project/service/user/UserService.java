package com.example.project.service.user;

import com.example.project.view.user.*;

import java.util.List;

/**
 * Сервис для управления информацией о пользователях
 * получая отображение и преобразуя в сущность для передачи в DAO слой
 */
public interface UserService {
    /**
     * Получить сущность сущность пользователя по параметру id, и преобразовать в отображение
     * @param id уникальный идентификатор
     * @return объект {@link UserView}
     */
    UserView getById(Long id);

    /**
     *  Преобразовать отображение и передать его в DAO для сохранения новой сущности пользователя
     * @param view {@link UserSaveView}
     */
    void save(UserSaveView view);

    /**
     * Преобразовать отображение и передать его в DAO для обновления данных сущности пользователя
     * @param view {@link UserUpdateView}
     */
    void update(UserUpdateView view);

    /**
     * Получить список отображений пользователей по пораметрам, передав их в DAO
     * @param userListInView {@link UserListInView}
     * @return список объектов {@link UserListOutView}
     */
    List<UserListOutView> list(UserListInView userListInView);
}
