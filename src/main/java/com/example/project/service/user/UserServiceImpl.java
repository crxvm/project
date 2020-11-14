package com.example.project.service.user;

import com.example.project.dao.country.CountryDao;
import com.example.project.dao.document.DocumentDao;
import com.example.project.dao.user.UserDao;
import com.example.project.model.Document;
import com.example.project.model.User;
import com.example.project.model.UserDocument;
import com.example.project.model.mapper.MapperFacade;
import com.example.project.view.UserListView;
import com.example.project.view.UserSaveView;
import com.example.project.view.UserUpdateView;
import com.example.project.view.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final MapperFacade mapperFacade;
    private final DocumentDao documentDao;
    private final CountryDao countryDao;

    @Autowired
    public UserServiceImpl(UserDao userDao, MapperFacade mapperFacade, DocumentDao documentDao, CountryDao countryDao) {
        this.userDao = userDao;
        this.mapperFacade = mapperFacade;
        this.documentDao = documentDao;
        this.countryDao = countryDao;
    }

    @Override
    @Transactional
    public UserView getById(Long id) {
        User user = userDao.getById(id);
        UserView view = mapperFacade.map(user, UserView.class);
        return view;
    }

    @Override
    @Transactional
    public void save(UserSaveView view) {
        view.document = documentDao.getByCode(view.docCode);
        view.country = countryDao.getByCode(view.citizenshipCode);
        UserDocument userDocument = mapperFacade.map(view, UserDocument.class);
        userDao.save(mapperFacade.map(view, User.class), userDocument);
    }

    @Override
    @Transactional
    public void update(UserUpdateView view) {
        view.document = documentDao.getByName(view.docName);
        view.country = countryDao.getByCode(view.citizenshipCode);
        view.userId = view.id;
        UserDocument userDocument = mapperFacade.map(view, UserDocument.class);
        userDao.update(mapperFacade.map(view, User.class), userDocument);
    }

    @Override
    @Transactional
    public List<UserListView> list(UserSaveView userView) {
        List<User> users = userDao.list(userView.officeId, userView.firstName, userView.secondName, userView.middleName, userView.position,
                userView.docCode, userView.citizenshipCode);
        return mapperFacade.mapAsList(users, UserListView.class);
    }
}
