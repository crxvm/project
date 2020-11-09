package com.example.project.dao.user;

import com.example.project.model.User;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
        /*TypedQuery<User> query = em.createQuery("select u from User u  left join User_Document ud " +
                "on u.id = ud.userId where" +
                " u.id = :id", User.class);
        query.setParameter("id", id);
        return query.getSingleResult();*/
    }
}
