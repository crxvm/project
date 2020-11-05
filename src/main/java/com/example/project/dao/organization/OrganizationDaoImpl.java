package com.example.project.dao.organization;

import com.example.project.model.Organization;
import org.aspectj.weaver.ast.Or;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
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
    public Organization list(String name, String inn, Boolean is_Active) {
        Query q = em.createQuery("Select O from Organization O where O.name =:name " +
                "and  O.inn LIKE CONCAT('%',:inn ,'%')");

        if(inn == null || inn == "") {
            inn = "%";
        }
        q.setParameter("inn", inn);
        q.setParameter("name", name);
        return (Organization) q.getResultList().get(0);
    }
}
