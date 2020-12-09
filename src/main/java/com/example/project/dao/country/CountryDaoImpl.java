package com.example.project.dao.country;

import com.example.project.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * {inheritDoc}
 */
@Repository
public class CountryDaoImpl implements CountryDao {
    private final EntityManager em;
    @Autowired
    public CountryDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {inheritDoc}
     */
    @Override
    public List<Country> all() {
        TypedQuery<Country> all = em.createQuery("SELECT c FROM Country c", Country.class);
        return all.getResultList();
    }

    /**
     * {inheritDoc}
     */
    @Override
    public Country getByCode(String code) {
        TypedQuery<Country> query = em.createQuery
                ("SELECT c from Country c where c.citizenshipCode = :code", Country.class   );
        query.setParameter("code", code);
        return query.getSingleResult();
    }
}
