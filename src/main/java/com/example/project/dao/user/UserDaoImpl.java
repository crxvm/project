package com.example.project.dao.user;

import com.example.project.model.User;
import com.example.project.model.UserDocument;
import com.example.project.model.mapper.MapperFacade;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    private final EntityManager em;
    private final MapperFacade mapperFacade;

    @Autowired
    public UserDaoImpl(EntityManager em, MapperFacade mapperFacade) {
        this.em = em;
        this.mapperFacade = mapperFacade;
    }

    @Override

    public User getById(Long id) { CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> criteria = cb.createQuery(User.class);
        Root<User> user = criteria.from(User.class);
        TypedQuery<User> query = em.createQuery(criteria.where(cb.equal(user.get("id"), id)));
        return query.getSingleResult();
    }

    public UserDocument getUdById(Long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<UserDocument> criteria = cb.createQuery(UserDocument.class);
        Root<UserDocument> userDocument = criteria.from(UserDocument.class);
        TypedQuery<UserDocument> query = em.createQuery(criteria.where(cb.equal(userDocument.get("id"), id)));
        return query.getSingleResult();
    }

    @Override
    public List<User> list(Integer officeId, String fName, String lName, String mName, String position, String docCode, String citizenshipCode) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> criteria = cb.createQuery(User.class);
        Root<User> user = criteria.from(User.class);
        Join country = user.join("country");
        Join userDocument = user.join("userDocument");
        Join document = userDocument.join("document");

        List<Predicate> list = new ArrayList<>();
        Predicate predicate1 = cb.equal(user.get("officeId"), officeId);
        Predicate predicate2 = cb.like(user.get("firstName"), fName);
        Predicate predicate3 = cb.like(user.get("secondName"), lName);
        Predicate predicate4 = cb.like(user.get("middleName"), mName);
        Predicate predicate5 = cb.like(user.get("position"), position);
        Predicate predicate6 = cb.like(country.get("citizenshipCode"), citizenshipCode);
        Predicate predicate7 = cb.like(document.get("docCode"), docCode);

        list.add(predicate1);
        if(fName != null) {
            list.add(predicate2);
        }
        if(lName != null) {
            list.add(predicate3);
        }
        if(mName != null) {
            list.add(predicate4);
        }
        if(position != null) {
            list.add(predicate5);
        }
        if(citizenshipCode != null) {
            list.add(predicate6);
        }
        if(docCode != null) {
            list.add(predicate7);
        }

        TypedQuery<User> query = em.createQuery(criteria.where(list.toArray(new Predicate[list.size()])));

        return query.getResultList();
    }

    @Override
    public void update(User user, UserDocument userDocument) {
        UserDocument uD = getUdById(userDocument.getUserId());
        User u = getById(user.getId());
        u.setVersion(u.getVersion() + 1);
        u.setCountry(user.getCountry());
        u.setFirstName(user.getFirstName());
        u.setMiddleName(user.getMiddleName());
        u.setSecondName(user.getSecondName());
        u.setOfficeId(user.getOfficeId());
        u.setPhone(user.getPhone());
        u.setIdentified(user.getIdentified());
        u.setPosition(user.getPosition());
        uD.setDocument(userDocument.getDocument());
        uD.setDocDate(userDocument.getDocDate());
        uD.setDocNumber(userDocument.getDocNumber());
        uD.setVersion(userDocument.getVersion() + 1);
    }



    @Override
    public void save(User user, UserDocument userDocument) {
        em.persist(user);
        userDocument.setUser(user);
        em.persist(userDocument);
    }
}
