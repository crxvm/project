package com.example.project.dao.user;

import com.example.project.model.User;
import com.example.project.model.UserDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class UserDaoImpl implements UserDao{

    private final EntityManager em;

    @Autowired
    public UserDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override

    /**
     * {@inheritDoc}
     */
    public User getById(Long id) { return em.find(User.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> list(Integer officeId, String fName, String lName, String mName, String position, String docCode, String citizenshipCode) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> criteria = cb.createQuery(User.class);
        Root<User> user = criteria.from(User.class);
        Join country = user.join("country");
        Join userDocument = user.join("userDocument");
        Join document = userDocument.join("document");

        List<Predicate> list = new ArrayList<>();

        list.add(cb.equal(user.get("officeId"), officeId));
        if(fName != null) {
            list.add(cb.like(user.get("firstName"), fName));
        }
        if(lName != null) {
            list.add(cb.like(user.get("secondName"), lName));
        }
        if(mName != null) {
            list.add(cb.like(user.get("middleName"), mName));
        }
        if(position != null) {
            list.add(cb.like(user.get("position"), position));
        }
        if(citizenshipCode != null) {
            list.add(cb.like(country.get("citizenshipCode"), citizenshipCode));
        }
        if(docCode != null) {
            list.add(cb.like(document.get("docCode"), docCode));
        }

        TypedQuery<User> query = em.createQuery(criteria.where(list.toArray(new Predicate[list.size()])));

        return query.getResultList();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void update(User user, UserDocument userDocument) {
        User u = getById(user.getId());
        if(u == null) {
            throw new NoResultException("cannot find user with id: " + user.getId());
        }
        UserDocument uD = u.getUserDocument();

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
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void save(User user, UserDocument userDocument) {
        em.persist(user);
        userDocument.setUser(user);
        em.persist(userDocument);
    }
}
