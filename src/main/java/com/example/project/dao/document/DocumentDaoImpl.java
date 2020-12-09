package com.example.project.dao.document;

import com.example.project.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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
        return query.getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Document getByName(String docName) {
        TypedQuery<Document> query = em.createQuery
                ("SELECT d FROM Document  d where d.docName = :docName", Document.class);
        query.setParameter("docName", docName);
        return query.getSingleResult();
    }

}
