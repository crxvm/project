package com.example.project.dao.office;

import com.example.project.model.Office;
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
        Office office1 = getById(office.getId());
        office1.setAddress(office.getAddress());
        office1.setName(office.getName());
        office1.setPhone(office.getPhone());
        office1.setActive(office.getActive());
    }

    @Override
    public Office getById(Long id) {
        return em.find(Office.class, id);
    }

    @Override
    public List<Office> list(Integer orgId, String name, String phone, Boolean isActive) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Office> criteria = cb.createQuery(Office.class);
        Root<Office> office = criteria.from(Office.class);

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(cb.equal(office.get("orgId"), orgId));

        if(name != null) {
            predicates.add(cb.like(office.get("name"), name));
        }
        if(phone != null) {
            predicates.add(cb.like(office.get("phone"), phone));
        }
        if(isActive != null) {
            predicates.add(cb.equal(office.get("isActive"), isActive));
        }

        TypedQuery<Office> query = em.createQuery(criteria.where(predicates.toArray(new Predicate[predicates.size()])));
        return query.getResultList();
    }
}
