package com.rentalkars.hibernate.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class AbstractDao <I extends Serializable, Id extends Serializable>{

    @PersistenceContext
    protected EntityManager em;
    protected final Class<I> entityClass;
    CriteriaBuilder builder;

    public AbstractDao() {
        this.entityClass = (Class<I>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    private CriteriaQuery<I> InitCriteria() {
        builder = this.em.getCriteriaBuilder();
        return builder.createQuery(this.entityClass);
    }

    public List<I> SelTutti() {
        CriteriaQuery<I> query = this.InitCriteria();

        return this.em.createQuery(query.select(query.from(this.entityClass))).getResultList();
    }

    public I SelById(Id id) {
        CriteriaQuery<I> query = this.InitCriteria();

        return this.em.createQuery(query.where(builder.equal(query.from(this.entityClass).get("id"), id))).getSingleResult();
    }

    public void Inserisci(I entity) {
        this.em.persist(entity);
        flushAndClear();
    }

    public void Aggiorna(I entity) {
        this.em.merge(entity);
        flushAndClear();
    }

    public void Elimina(I entity) {
        this.em.remove(this.em.contains(entity) ? entity : this.em.merge(entity));
        flushAndClear();
    }

    public void EliminaById(Id id) {
        CriteriaQuery<I> query = this.InitCriteria();
        this.em.createQuery(query.where(builder.equal(query.from(this.entityClass).get("id"), id))).executeUpdate();
        flushAndClear();
    }

    public void flushAndClear() {
        em.flush();
        em.clear();
    }

}
