package com.example.project.controller.user;

import com.example.project.service.user.UserService;
import com.example.project.view.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<UserListOutView> list(@RequestBody UserListInView userListInView) {
        return userService.list(userListInView);
    }


}
