package com.immortal.configurations.dao;

import com.immortal.configurations.entity.PropertyValueEntity;
import com.immortal.configurations.interceptors.Logged;

import javax.transaction.Transactional;
import java.util.UUID;

@Logged
@Transactional
public class PropertyValueDao extends AbstractDao<PropertyValueEntity, UUID> {
    @Override
    public Class<PropertyValueEntity> getClazz() {
        return PropertyValueEntity.class;
    }
}
