package com.immortal.configurations.dao;

import com.immortal.configurations.util.criteria.DeleteCriteria;
import com.immortal.configurations.util.criteria.SelectCriteria;
import com.immortal.configurations.util.criteria.UpdateCriteria;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Consumer;

import static com.immortal.configurations.constants.PersistenceConstants.PERSISTENCE_CONTEXT;

//import com.immortal.utils.audit.AuditActionsEnum;
//import com.immortal.utils.persistence.AuditDaoManager;
//import com.immortal.utils.persistence.JmsDaoManager;

/**
 * Implements Create/Update/Delete operations for JPA entity.
 *
 * @param <T> entity type
 */
public abstract class AbstractDao<T, I>
{
    public abstract Class<T> getClazz();

    @PersistenceContext(unitName = PERSISTENCE_CONTEXT)
    private EntityManager em;

    public EntityManager getEm()
    {
        return em;
    }

    public SelectCriteria<T> newSelectCriteria()
    {
        return new SelectCriteria<T>(em, getClazz());
    }

    public UpdateCriteria<T> newUpdateCriteria()
    {
        return new UpdateCriteria<T>(em, getClazz());
    }

    public DeleteCriteria<T> newDeleteCriteria()
    {
        return new DeleteCriteria<T>(em, getClazz());
    }

    public void invalidateCache() {
    };


//    public JmsDaoManager<T> getJmsDaoManager()
//    {
//        return null;
//    }
//
//    public AuditDaoManager<T> getAuditDaoManager()
//    {
//        return null;
//    }

    public T create(final T entity)
    {
        getEm().persist(entity);
        getEm().flush();

//        if (isJmsEnabled())
//        {
////            getJmsDaoManager().sendCreatedJms(entity);
//        }
//
//        if (isAuditEnabled())
//        {
////            getAuditDaoManager().sendCreateAuditDto(entity);
//        }

        return entity;
    }

    public T create(final Consumer<T> modifier)
    {
        try
        {
            final Constructor<T> defaultConstructor = getClazz().getConstructor();
            final T entity = defaultConstructor.newInstance();
            modifier.accept(entity);
            final T created = create(entity);
            invalidateCache();
            return created;
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public T update(final I id, final Consumer<T> modifier)
    {
        final T entity = findById(id);

//        Object jmsDtoBeforeUpdate = isJmsEnabled() ? getJmsDaoManager().getJmsDtoBeforeUpdate(entity) : null;
//        Object auditSnapshotBeforeUpdate = isAuditEnabled() ? getAuditDaoManager().entityToSnapshot(entity) : null;

        modifier.accept(entity);

        //TODO update only when entity is different?

        final T updatedEntity = update(entity);

//        if (isJmsEnabled())
//        {
////            getJmsDaoManager().sendUpdateJms(updatedEntity, jmsDtoBeforeUpdate);
//        }
//        if (isAuditEnabled())
//        {
////            getAuditDaoManager().sendUpdateAudit(updatedEntity, auditSnapshotBeforeUpdate);
//        }

        return updatedEntity;
    }

    protected T update(final T entity)
    {
        final T result = getEm().merge(entity);
        getEm().flush();
        invalidateCache();
        return result;
    }

    public void deleteById(final I id)
    {
        final T entity = getEm().find(getClazz(), id);
        if (entity != null)
        {
            delete(entity);
        }
    }

    public void delete(final T entity)
    {
//        final Object jmsDtoBeforeDelete = isJmsEnabled() ? getJmsDaoManager().getJmsBeforeDeleteDto(entity) : null;
//        final Object auditSnapshotBeforeDelete = isAuditEnabled() ?
//                getAuditDaoManager().entityToSnapshot(entity) :
//                null;
//        Object auditDto = isAuditEnabled() ?
//                getAuditDaoManager()
//                        .transformEntityToAuditDto(entity, null, auditSnapshotBeforeDelete, AuditActionsEnum.DELETE) :
//                null;

        getEm().remove(entity);
        getEm().flush();

        invalidateCache();

//        if (isJmsEnabled())
//        {
//            getJmsDaoManager().sendDeleteJms(jmsDtoBeforeDelete);
//        }
//        if (isAuditEnabled())
//        {
//            getAuditDaoManager().sendAuditDto(auditDto);
//        }
    }

    public int executeNamedQueryUpdate(final String namedQueryName, final Map<String, Object> parameters)
    {
        Set<Entry<String, Object>> rawParameters = parameters.entrySet();
        Query query = getEm().createNamedQuery(namedQueryName);

        rawParameters.forEach(e -> query.setParameter(e.getKey(), e.getValue()));
        final int updateResult = query.executeUpdate();
        invalidateCache();
        return updateResult;
    }

    public void refresh(final T entity)
    {
        getEm().refresh(entity);
    }

    public T getReference(final I id)
    {
        return getEm().getReference(getClazz(), id);
    }

    public T findById(final I id)
    {
        return getEm().find(getClazz(), id);
    }

    public List<T> findAll()
    {
        CriteriaBuilder cb = getEm().getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(getClazz());
        Root<T> root = cq.from(getClazz());
        cq.select(root);
        return getEm().createQuery(cq).getResultList();
    }

    public boolean exists(final I id)
    {
        //can't use getReference here, because Hibernate doesn't throw EntityNotFoundException immediately -
        //it's thrown only when getter is called
        return id != null && (getEm().find(getClazz(), id) != null);
    }

    public void clearCache()
    {
        getEm().getEntityManagerFactory().getCache().evict(getClazz());
    }

//    private boolean isJmsEnabled()
//    {
//        return getJmsDaoManager() != null && getJmsDaoManager().getJmsDaoConfig().isJmsEnabled();
//    }
//
//    private boolean isAuditEnabled()
//    {
//        return getAuditDaoManager() != null && getAuditDaoManager().getAuditDaoConfig().isAuditEnabled();
//    }
}
