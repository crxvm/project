package com.example.project.dao.user;


import com.example.project.model.User;
import com.example.project.model.UserDocument;

import java.util.List;

public interface UserDao {
    User getById(Long id);
    UserDocument getUserDocumentById(Long id);
    void save(User user, UserDocument userDocument);
    void update(User user, UserDocument userDocument);
    List<User> list(Integer officeId, String fName, String lName, String mName, String position, String docCode, String citizenshipCode);
}
