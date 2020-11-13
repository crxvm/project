package com.example.project.dao.user;

import com.example.project.model.User;
import com.example.project.model.UserDocument;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

@Repository
public class UserDaoImpl implements UserDao{

    @Autowired
    public UserDaoImpl(EntityManager em) {
        this.em = em;
    }

    private final EntityManager em;
    @Override

    public User getById(Long id) { CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> criteria = cb.createQuery(User.class);
        Root<User> user = criteria.from(User.class);
        TypedQuery<User> query = em.createQuery(criteria.where(cb.equal(user.get("id"), id)));
        return query.getSingleResult();
    }

    @Override
    public User list(Integer officeId, String fName, String lName, String mName, String position, String docCode, String citizenshipCode) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> criteria = cb.createQuery(User.class);
        Root<User> user = criteria.from(User.class);
        Join country = user.join("country");
        Join userDocument = user.join("userDocument");
        Join document = userDocument.join("document");

        TypedQuery<User> query = em.createQuery
                (criteria.where(cb.and(cb.equal(user.get("officeId"), officeId), cb.like(user.get("firstName"), fName),
                        cb.like(user.get("secondName"), lName)), cb.like(user.get("middleName"), mName),
                        cb.like(user.get("position"), position), cb.like(country.get("citizenshipCode"), citizenshipCode),
                        cb.like(document.get("docCode"), docCode)
                ));
        return query.getSingleResult();
    }

    @Override
    public void update(User user, UserDocument userDocument) {
        Session session = em.unwrap(Session.class);
        session.update(user);
        session.update(userDocument);
    }

    @Override
    public void save(User user, UserDocument userDocument) {
        em.persist(user);
        userDocument.setUser(user);
        em.persist(userDocument);
    }
}
