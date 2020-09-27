package com.immortal.configurations.dao;

import java.util.UUID;

import javax.transaction.Transactional;

import com.immortal.configurations.entity.ConfigInstanceEntity;
import com.immortal.configurations.interceptors.Logged;

@Logged
@Transactional
public class ConfigInstanceDao extends AbstractDao<ConfigInstanceEntity, UUID>
{
    @Override
    public Class<ConfigInstanceEntity> getClazz()
    {
        return ConfigInstanceEntity.class;
    }
}
