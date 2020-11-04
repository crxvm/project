package com.example.project.dao.organization;

import com.example.project.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

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
        em.merge(organization);
    }

    @Override
    public Organization list(Long id, String inn, Boolean is_Active) {
        return null;
    }
}
