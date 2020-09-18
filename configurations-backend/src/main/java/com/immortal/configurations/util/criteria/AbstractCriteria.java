package com.immortal.configurations.util.criteria;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.commons.collections4.CollectionUtils;

public abstract class AbstractCriteria<T>
{
    protected CriteriaBuilder cb;
    protected Root<T> root;
    protected List<Predicate> criterias;
    protected EntityManager em;
    protected Class<T> clazz;

    /**
     * marks if select was already executed.
     */
    protected boolean executed;

    public AbstractCriteria(final EntityManager em, final Class<T> clazz)
    {
        this.em = em;
        this.cb = em.getCriteriaBuilder();
        this.criterias = new ArrayList<>();
        this.clazz = clazz;
    }

    public CriteriaBuilder getCb()
    {
        return cb;
    }

    public Root<T> getRoot()
    {
        return root;
    }

    public Class<T> getClazz()
    {
        return clazz;
    }

    public List<Predicate> getCriterias()
    {
        return criterias;
    }

    public <V> AbstractCriteria<T> inCollection(final SingularAttribute<T, V> attribute, final List<V> collection)
    {
        if (CollectionUtils.isNotEmpty(collection))
        {
            if (collection.size() == 1)
            {
                criterias.add(cb.equal(root.get(attribute), collection.get(0)));
            }
            else
            {
                criterias.add(root.get(attribute).in(collection));
            }
        }
        return this;
    }

    protected void validateExecuted()
    {
        if (executed)
        {
            throw new IllegalStateException("Query was already executed for this criterias!");
        }
        executed = true;
    }

    protected Predicate getCriteriasJoinedWithAnd()
    {
        return cb.and(criterias.toArray(new Predicate[0]));
    }
}