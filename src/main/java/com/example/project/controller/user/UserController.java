package com.example.project.controller.user;

import com.example.project.service.user.UserService;
import com.example.project.view.ResultView;
import com.example.project.view.organization.OrganizationView;
import com.example.project.view.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import javax.validation.Valid;
import java.util.List;

/**
 * Контроллер для управление данными о пользователях
 */
@RestController
@RequestMapping(value = "api/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Возвращает пользователя по id
     * @param id - уникальный идентефикатор
     * @return объект для отображения данных пользователя {@link UserView}
     */
    @GetMapping("/{id}")
    public UserView getById(@PathVariable("id") Long id) {
        UserView userView = userService.getById(id);
        if(userView == null) {
            throw new NoResultException("cannot find user with id: " + id);
        }
        return userView;
    }

    /**
     * Сохраняет нового пользователя
     * @param userSaveView отображение для сохранения нового пользователя {@link UserSaveView}
     * @return возвращается результат выполнения, пример: result = "success"
     */
    @PostMapping("/save")
    @ResponseBody
    public ResultView save(@Valid @RequestBody UserSaveView userSaveView){
        userService.save(userSaveView);
        return new ResultView();
    }

    /**
     * Обновляет данные пользователя
     * @param userUpdateView отображение для обновления данных пользователя {@link UserUpdateView}
     * @return возвращается результат выполнения, пример: result = "success"
     */
    @PostMapping("/update")
    @ResponseBody
    public ResultView update(@Valid @RequestBody UserUpdateView userUpdateView){
        userService.update(userUpdateView);
        return new ResultView();
    }

    /**
     * Возвращает список пользователей по указанным параметрам в теле запроса
     * @param userListInView отображение для получения списка пользователей
     * @return список объектов {@link UserListOutView}
     */
    @PostMapping("/list")
    @ResponseBody
    public List<UserListOutView> list(@Valid @RequestBody UserListInView userListInView) {
        return userService.list(userListInView);
    }

}
