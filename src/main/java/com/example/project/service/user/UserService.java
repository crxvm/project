package com.example.project.service.user;

import com.example.project.view.UserSaveView;
import com.example.project.view.UserView;

public interface UserService {
    UserView getById(Long id);
    void save(UserSaveView view);
}
