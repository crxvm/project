package com.example.project.dao.user;


import com.example.project.model.User;

public interface UserDao {
    User getById(Long id);
    void save(User user);
}
