package com.immortal.configurations.dao;

import com.immortal.configurations.entity.ConfigMetadataEntity;
import com.immortal.configurations.interceptors.Logged;

import javax.transaction.Transactional;

@Logged
@Transactional
public class ConfigMetadataDao extends AbstractDao<ConfigMetadataEntity, String> {
    @Override
    public Class<ConfigMetadataEntity> getClazz() {
        return ConfigMetadataEntity.class;
    }
}
