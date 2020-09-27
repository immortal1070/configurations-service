package com.immortal.configurations.dao;

import javax.transaction.Transactional;

import com.immortal.configurations.entity.ConfigMetadataEntity;
import com.immortal.configurations.interceptors.Logged;

@Logged
@Transactional
public class ConfigMetadataDao extends AbstractDao<ConfigMetadataEntity, String>
{
    @Override
    public Class<ConfigMetadataEntity> getClazz()
    {
        return ConfigMetadataEntity.class;
    }
}
