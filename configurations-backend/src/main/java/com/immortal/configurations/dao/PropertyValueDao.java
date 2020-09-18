package com.immortal.configurations.dao;

import java.util.UUID;

import javax.transaction.Transactional;

import com.immortal.configurations.entity.PropertyValueEntity;
import com.immortal.configurations.interceptors.Logged;

@Logged
@Transactional
public class PropertyValueDao extends AbstractDao<PropertyValueEntity, UUID>
{
    @Override
    public Class<PropertyValueEntity> getClazz()
    {
        return PropertyValueEntity.class;
    }
}
