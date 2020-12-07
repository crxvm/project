package com.example.project.controller.user;

import com.example.project.service.user.UserService;
import com.example.project.view.ResultView;
import com.example.project.view.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import javax.validation.Valid;
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
        UserView userView = userService.getById(id);
        if(userView == null) {
            throw new NoResultException("cannot find user with id: " + id);
        }
        return userView;
    }

    @PostMapping("/save")
    @ResponseBody
    public ResultView save(@Valid @RequestBody UserSaveView userSaveView){
        userService.save(userSaveView);
        return new ResultView();
    }

    @PostMapping("/update")
    @ResponseBody
    public ResultView update(@Valid @RequestBody UserUpdateView userUpdateView){
        userService.update(userUpdateView);
        return new ResultView();
    }

    @PostMapping("/list")
    @ResponseBody
    public List<UserListOutView> list(@Valid @RequestBody UserListInView userListInView) {
        return userService.list(userListInView);
    }

}
