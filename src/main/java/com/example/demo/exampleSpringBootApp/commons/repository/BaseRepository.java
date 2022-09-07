package com.example.demo.exampleSpringBootApp.commons.repository;

import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class BaseRepository {

    @PersistenceContext
    protected EntityManager em;
    private Session session;


    public static long count(EntityManager em, Root<?> root, CriteriaQuery<?> criteria)
    {
        final CriteriaBuilder builder=em.getCriteriaBuilder();
        final CriteriaQuery<Long> countCriteria=builder.createQuery(Long.class);

        countCriteria.select(builder.count(root.get("id")));

        for(Root<?> fromRoot : criteria.getRoots()){
            countCriteria.getRoots().add(fromRoot);
        }

        final Predicate whereRestriction=criteria.getRestriction();
        if(whereRestriction!=null){
            countCriteria.where(whereRestriction);
        }

        final Predicate groupRestriction=criteria.getGroupRestriction();
        if(groupRestriction!=null){
            countCriteria.having(groupRestriction);
        }

        countCriteria.groupBy(criteria.getGroupList());
        countCriteria.distinct(criteria.isDistinct());
        return em.createQuery(countCriteria).getSingleResult();
    }

    /*
    * Métodos para habilitar el soft delete a la hora de realizar una petición a BD
    */
    public void enableFilterSoftDeleted() {
        this.session = this.em.unwrap(Session.class);
        this.session.enableFilter("softDeleted");
    }

    public void disableFilterSoftDeleted() {
        if (this.session != null) {
            this.session.disableFilter("softDeleted");
            this.session = null;
        }
    }

}
