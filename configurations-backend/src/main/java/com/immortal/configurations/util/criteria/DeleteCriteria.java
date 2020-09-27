package com.immortal.configurations.util.criteria;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Predicate;

public class DeleteCriteria<T> extends AbstractCriteria<T> {
    private final CriteriaDelete<T> cq;

    public DeleteCriteria(final EntityManager em, final Class<T> clazz) {
        super(em, clazz);
        this.cq = this.cb.createCriteriaDelete(clazz);
        this.root = this.cq.from(clazz);
    }

    public int delete() {
        validateExecuted();

        cq.where(cb.and(criterias.toArray(new Predicate[0])));

        return createQuery(cq).executeUpdate();
    }
}
