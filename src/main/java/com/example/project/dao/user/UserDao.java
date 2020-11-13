package com.example.project.dao.user;


import com.example.project.model.User;
import com.example.project.model.UserDocument;

public interface UserDao {
    User getById(Long id);
    void save(User user, UserDocument userDocument);
}
