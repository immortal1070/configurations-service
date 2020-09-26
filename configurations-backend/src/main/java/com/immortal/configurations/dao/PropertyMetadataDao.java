package com.immortal.configurations.dao;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import com.immortal.configurations.api.params.PropertyMetadataSearchParams;
import com.immortal.configurations.entity.PropertyMetadataEntity;
import com.immortal.configurations.entity.PropertyMetadataEntity_;
import com.immortal.configurations.interceptors.Logged;
import com.immortal.configurations.util.criteria.AbstractCriteria;
import com.immortal.configurations.util.criteria.DeleteCriteria;
import com.immortal.configurations.util.criteria.SelectCriteria;

@Logged
@Transactional
public class PropertyMetadataDao extends AbstractDao<PropertyMetadataEntity, UUID>
{
    @Override
    public Class<PropertyMetadataEntity> getClazz()
    {
        return PropertyMetadataEntity.class;
    }

    public List<PropertyMetadataEntity> find(final PropertyMetadataSearchParams searchParams)
    {
        final SelectCriteria<PropertyMetadataEntity> criteriaHelper = newSelectCriteria();
        fillSearchCriterias(criteriaHelper, searchParams);
        return criteriaHelper.select();
    }

    public void delete(final PropertyMetadataSearchParams searchParams)
    {
        final DeleteCriteria<PropertyMetadataEntity> criteriaHelper = newDeleteCriteria();
        fillSearchCriterias(criteriaHelper, searchParams);
        criteriaHelper.delete();
    }

    private void fillSearchCriterias(final AbstractCriteria<PropertyMetadataEntity> criteriaHelper, final PropertyMetadataSearchParams searchParams)
    {
        criteriaHelper.inCollection(PropertyMetadataEntity_.group, searchParams.getGroups());
        criteriaHelper.inCollection(PropertyMetadataEntity_.name, searchParams.getNames());
        criteriaHelper.inCollection(PropertyMetadataEntity_.id, searchParams.getIds());
        criteriaHelper.inCollection(PropertyMetadataEntity_.configMetadataId, searchParams.getConfigMetadataIds());
    }
}
