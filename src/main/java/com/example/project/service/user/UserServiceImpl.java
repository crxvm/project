package com.example.project.service.user;

import com.example.project.dao.user.UserDao;
import com.example.project.model.User;
import com.example.project.model.mapper.MapperFacade;
import com.example.project.view.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao dao;
    private final MapperFacade mapperFacade;

    @Autowired
    public UserServiceImpl(UserDao dao, MapperFacade mapperFacade) {
        this.dao = dao;
        this.mapperFacade = mapperFacade;
    }

    @Override
    @Transactional
    public UserView getById(Long id) {
        User user = dao.getById(id);
        UserView view = mapperFacade.map(user, UserView.class);
        view.documentId = user.getUserDocument().getDocumentId();
        view.docNumber = user.getUserDocument().getDocNumber();
        view.docDate = user.getUserDocument().getDocDate();
        return view;
    }
}
