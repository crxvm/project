package com.example.project.dao.office;

import com.example.project.model.Office;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Repository
public class OfficeDaoImpl implements OfficeDao {
    private final EntityManager em;

    @Autowired
    public OfficeDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Office office) {
        em.persist(office);
    }

    @Override
    public void update(Office office) {
        Session session = em.unwrap(Session.class);
        session.update(office);
    }

    @Override
    public Office getById(Long id) {
        return em.find(Office.class, id);
    }

    @Override
    public Office list(Integer orgId, String name, String phone, Boolean isActive) {
        Query q = em.createQuery(
                "SELECT o FROM Office o WHERE o.orgId = :orgId " +
                        "and (o.name is null or o.name LIKE CONCAT('%',:name ,'%')) " +
                        "and (o.phone is null or o.phone LIKE CONCAT('%',:phone ,'%'))" +
                        "and o.isActive = :isActive");
        if(name == "") {
            name = "%";
        }
        if(phone == "") {
            phone = "%";
        }
        q.setParameter("orgId" ,orgId);
        q.setParameter("name", name);
        q.setParameter("phone", phone);
        q.setParameter("isActive", isActive);
        return (Office) q.getResultList().get(0);
    }
}
