package com.example.project.service.user;

import com.example.project.view.user.*;

import java.util.List;

public interface UserService {
    UserView getById(Long id);
    void save(UserSaveView view);
    void update(UserUpdateView view);
    List<UserListOutView> list(UserListInView userListInView);
}
