package com.immortal.configurations.util.criteria;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.SingularAttribute;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Util class which collects the common logic for select/delete/update criterias.
 */
public abstract class AbstractCriteria<T>
{
    public static final Logger logger = Logger.getLogger(AbstractCriteria.class.getName());

    private final static String FETCH_GRAPH = "javax.persistence.fetchgraph";

    protected CriteriaBuilder cb;
    protected Root<T> root;
    protected List<Predicate> criterias;
    protected EntityManager em;
    protected Class<T> clazz;
    protected Map<String, Object> hints;

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
        this.hints = new HashMap<>();
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

    public AbstractCriteria<T> addGraph(final String graphName)
    {
        if (StringUtils.isNotBlank(graphName)) {
            EntityGraph<?> graph = this.em.getEntityGraph(graphName);
            hints.put(FETCH_GRAPH, graph);
        }
        return this;
    }

    public AbstractCriteria<T> addHint(String key, Object value)
    {
        hints.put(key, value);
        return this;
    }

    public AbstractCriteria<T> addHints(Map<String, Object> hints)
    {
        this.hints.putAll(hints);
        return this;
    }

    protected Query createQuery(final CriteriaDelete<T> cq) {
        return fillHints(em.createQuery(cq));
    }

    protected Query createQuery(final CriteriaUpdate<T> cq) {
        return fillHints(em.createQuery(cq));
    }

    protected TypedQuery<T> createQuery(final CriteriaQuery<T> cq) {
        return fillHints(em.createQuery(cq));
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

    private <Q extends Query> Q fillHints(final Q query) {
        hints.forEach(query::setHint);
        return query;
    }
}
