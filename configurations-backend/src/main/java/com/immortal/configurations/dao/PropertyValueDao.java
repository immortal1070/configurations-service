package com.immortal.configurations.dao;

import com.immortal.configurations.api.params.PropertyValueSearchParams;
import com.immortal.configurations.entity.PropertyValueEntity;
import com.immortal.configurations.entity.PropertyValueEntity_;
import com.immortal.configurations.interceptors.Logged;
import com.immortal.configurations.util.criteria.AbstractCriteria;
import com.immortal.configurations.util.criteria.DeleteCriteria;
import com.immortal.configurations.util.criteria.SelectCriteria;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Logged
@Transactional
public class PropertyValueDao extends AbstractDao<PropertyValueEntity, UUID> {
    @Override
    public Class<PropertyValueEntity> getClazz() {
        return PropertyValueEntity.class;
    }

    public List<PropertyValueEntity> find(final PropertyValueSearchParams searchParams) {
        final SelectCriteria<PropertyValueEntity> criteriaHelper = newSelectCriteria();
        fillSearchCriterias(criteriaHelper, searchParams);
        return criteriaHelper.select();
    }

    public void delete(final PropertyValueSearchParams searchParams) {
        final DeleteCriteria<PropertyValueEntity> criteriaHelper = newDeleteCriteria();
        fillSearchCriterias(criteriaHelper, searchParams);
        criteriaHelper.delete();
    }

    private void fillSearchCriterias(final AbstractCriteria<PropertyValueEntity> criteriaHelper, final PropertyValueSearchParams searchParams) {
        criteriaHelper.inCollection(PropertyValueEntity_.name, searchParams.getNames());
        criteriaHelper.inCollection(PropertyValueEntity_.id, searchParams.getIds());
        criteriaHelper.inCollection(PropertyValueEntity_.value, searchParams.getValues());
        criteriaHelper.inCollection(PropertyValueEntity_.configInstanceId, searchParams.getConfigInstanceIds());
    }
}
