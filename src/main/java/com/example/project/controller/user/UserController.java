package com.example.project.controller.user;

import com.example.project.service.user.UserService;
import com.example.project.view.UserListView;
import com.example.project.view.UserSaveView;
import com.example.project.view.UserUpdateView;
import com.example.project.view.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserView getById(@PathVariable("id") Long id) {
        return userService.getById(id);
    }

    @PostMapping("/save")
    public void save(@RequestBody UserSaveView userSaveView){
        userService.save(userSaveView);
    }

    @PostMapping("/update")
    public void update(@RequestBody UserUpdateView userUpdateView){
        userService.update(userUpdateView);
    }

    @PostMapping("/list")
    public UserListView list(@RequestBody UserSaveView userSaveView) {
        return userService.list(userSaveView);
    }


}
