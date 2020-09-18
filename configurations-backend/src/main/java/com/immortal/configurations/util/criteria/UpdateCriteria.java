package com.immortal.configurations.util.criteria;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaUpdate;

public class UpdateCriteria<T> extends AbstractCriteria<T>
{
    private final CriteriaUpdate<T> cq;

    public UpdateCriteria(final EntityManager em, final Class<T> clazz)
    {
        super(em, clazz);
        this.cq = this.cb.createCriteriaUpdate(clazz);
        this.root = this.cq.from(clazz);
    }

    public int update()
    {
        validateExecuted();

        cq.where(getCriteriasJoinedWithAnd());

        return em.createQuery(cq).executeUpdate();
    }
}