package com.example.project.dao.organization;

import com.example.project.model.Organization;
import org.aspectj.weaver.ast.Or;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class OrganizationDaoImpl implements OrganizationDao {
    private final EntityManager em;
    @Autowired
    public OrganizationDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Organization organization) {
        em.persist(organization);
    }

    @Override
    public Organization getOrganizationById(Long id) {
        return em.find(Organization.class, id);
    }

    @Override
    public void update(Organization organization) {
        Session session = em.unwrap(Session.class);
        session.update(organization);
    }

    @Override
    public List<Organization> list(Long id, String inn, Boolean is_Active) {
        Query query =  em.createQuery("SELECT o FROM Organization o " +
                "where o.id =: id and o.inn = :inn and o.isActive = :is_Active", Organization.class);
        query.setParameter("id", id);
        query.setParameter("inn", inn);
        query.setParameter("is_Active", is_Active);
        return query.getResultList();

    }
}
