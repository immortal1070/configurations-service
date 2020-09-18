package com.immortal.configurations.util.criteria;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;

public class SelectCriteria<T> extends AbstractCriteria<T>
{
    protected CriteriaQuery<T> cq;

    public SelectCriteria(final EntityManager em, final Class<T> clazz)
    {
        super(em, clazz);
        this.cq = this.cb.createQuery(clazz);
        this.root = this.cq.from(clazz);
    }

    public List<T> select()
    {
        validateExecuted();

        cq.where(cb.and(criterias.toArray(new Predicate[0])));
        cq.select(root);

        return em.createQuery(cq).getResultList();
    }
//
//    public SelectCriteria<T> inCollection(final SingularAttribute<T, String> attribute, final List<String> collection)
//    {
//        if (CollectionUtils.isNotEmpty(collection))
//        {
//            if (collection.size() == 1)
//            {
//                criterias.add(cb.equal(root.get(attribute), collection.get(0)));
//            }
//            else
//            {
//                criterias.add(root.get(attribute).in(collection));
//            }
//        }
//        return this;
//    }
}