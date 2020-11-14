package com.example.project.service.user;

import com.example.project.view.UserListView;
import com.example.project.view.UserSaveView;
import com.example.project.view.UserUpdateView;
import com.example.project.view.UserView;

import java.util.List;

public interface UserService {
    UserView getById(Long id);
    void save(UserSaveView view);
    void update(UserUpdateView view);
    List<UserListView> list(UserSaveView userView);
}
