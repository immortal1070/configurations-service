package com.immortal.configurations.util.criteria;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import java.util.List;

public class SelectCriteria<T> extends AbstractCriteria<T> {
    protected CriteriaQuery<T> cq;

    public SelectCriteria(final EntityManager em, final Class<T> clazz) {
        super(em, clazz);
        this.cq = this.cb.createQuery(clazz);
        this.root = this.cq.from(clazz);
    }

    public List<T> select() {
        validateExecuted();

        cq.where(cb.and(criterias.toArray(new Predicate[0])));
        cq.select(root);

        return createQuery(cq).getResultList();
    }
}
