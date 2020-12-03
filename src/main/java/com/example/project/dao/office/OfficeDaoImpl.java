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
        Predicate predicate1 =cb.equal(office.get("orgId"), orgId);
        Predicate predicate2 =cb.like(office.get("name"), name);
        Predicate predicate3 =cb.like(office.get("phone"), phone);
        Predicate predicate4 =cb.equal(office.get("isActive"), isActive);

        predicates.add(predicate1);
        if(name != null) {
            predicates.add(predicate2);
        }
        if(phone != null) {
            predicates.add(predicate3);
        }
        if(isActive != null) {
            predicates.add(predicate4);
        }

        TypedQuery<Office> query = em.createQuery(criteria.where(predicates.toArray(new Predicate[predicates.size()])));
        return query.getResultList();
    }
}
