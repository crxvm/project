package com.example.project.dao.organization;

import com.example.project.model.Organization;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
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
    public List<Organization> list(String name, String inn, Boolean isActive) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Organization> criteria = cb.createQuery(Organization.class);
        Root<Organization> organization = criteria.from(Organization.class);

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(cb.like(organization.get("name"), name));
        if(inn != null) {
            predicates.add(cb.like(organization.get("inn"), inn));
        }
        if(isActive != null) {
            predicates.add(cb.equal(organization.get("isActive"), isActive));
        }

        TypedQuery<Organization> query = em.createQuery(criteria.where(predicates.toArray(new Predicate[predicates.size()])));
        return query.getResultList();
    }
}
