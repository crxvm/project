package com.example.project.dao.document;

import com.example.project.model.Document;
import com.example.project.model.Office;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class DocumentDaoImpl implements DocumentDao {
    private final EntityManager em;

    @Autowired
    public DocumentDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Document> all() {
        TypedQuery<Document> query = em.createQuery("SELECT d FROM Document d", Document.class);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Document getByCode(String code) {
        TypedQuery<Document> query = em.createQuery
                ("SELECT d FROM Document d where d.docCode = :code", Document.class);
        query.setParameter("code", code);
        try {
            return query.getSingleResult();
        }
        catch (Exception e) {
            throw new NoResultException("cannot find document with code: " + code);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Document getByName(String docName) {
        TypedQuery<Document> query = em.createQuery
                ("SELECT d FROM Document  d where d.docName = :docName", Document.class);
        query.setParameter("docName", docName);
        try {
            return query.getSingleResult();
        }
        catch (Exception e) {
            throw new NoResultException("cannot find document with docName: " + docName);
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Document getByNameAndCode(String docName, String docCode) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Document> criteria = cb.createQuery(Document.class);
        Root<Document> document = criteria.from(Document.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.like(document.get("docName"), docName));
        predicates.add(cb.like(document.get("docCode"), docCode));

        TypedQuery<Document> query = em.createQuery(criteria.where(predicates.toArray(new Predicate[predicates.size()])));
        try {
            return query.getSingleResult();
        }
        catch (NoResultException e) {
            throw new NoResultException("cannot find document with docName: " + docName + " and with docCode: "+ docCode);
        }
    }
}
