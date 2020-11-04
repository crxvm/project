package com.example.project.dao.country;

import com.example.project.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CountryDaoImpl implements CountryDao {
    private final EntityManager em;
    @Autowired
    public CountryDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Country> all() {
        TypedQuery<Country> all = em.createQuery("SELECT c FROM Country c", Country.class);
        return all.getResultList();
    }
}
