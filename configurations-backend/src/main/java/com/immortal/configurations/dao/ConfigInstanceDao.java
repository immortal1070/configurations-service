package com.immortal.configurations.dao;

import com.immortal.configurations.entity.ConfigInstanceEntity;
import com.immortal.configurations.interceptors.Logged;

import javax.transaction.Transactional;
import java.util.UUID;

@Logged
@Transactional
public class ConfigInstanceDao extends AbstractDao<ConfigInstanceEntity, UUID> {
    @Override
    public Class<ConfigInstanceEntity> getClazz() {
        return ConfigInstanceEntity.class;
    }
}
