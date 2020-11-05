package com.example.project.dao.office;

import com.example.project.view.Office;
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
    public Office list(String org_Id, String name, String phone, Boolean is_Active) {
        Query q = em.createQuery(
                "SELECT o FROM Office o WHERE o.org_id = :org_id " +
                        "and o.name LIKE CONCAT('%',:name ,'%')" +
                        "and o.phone LIKE CONCAT('%',:phone ,'%')");
        if(name == null || name == "") {
            name = "%";
        }
        if(phone == null || phone == "") {
            name = "%";
        }
        q.setParameter("name", name);
        q.setParameter("phone", phone);
        return (Office) q.getResultList().get(0);
    }
}
