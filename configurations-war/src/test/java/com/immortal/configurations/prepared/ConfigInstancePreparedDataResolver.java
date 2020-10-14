package com.immortal.configurations.prepared;

import com.immortal.configurations.api.ConfigInstanceResource;
import com.immortal.configurations.api.ConfigMetadataResource;
import com.immortal.configurations.api.dto.ConfigInstanceDto;
import com.immortal.configurations.util.TestRestClient;


/**
 * If parameter "ConfigInstanceDto" is used in tests, then this resolver will create the entity, will
 * inject it into the parameter and will delete the entity after test is executed.
 */
public class ConfigInstancePreparedDataResolver extends PreparedTestDataResolver<ConfigInstanceDto> {

    private final static ConfigMetadataResource configMetadataResource = new TestRestClient()
        .getRestProxy(ConfigMetadataResource.class);

    private final static ConfigInstanceResource configInstanceResource = new TestRestClient()
        .getRestProxy(ConfigInstanceResource.class);

    @Override
    public ConfigInstanceDto create() {
        configMetadataResource.register(new ConfigMetadataPreparedData().persistDto());
        return configInstanceResource.create(new ConfigInstancePreparedData().persistDto());
    }

    @Override
    public void cleanup() {
        configInstanceResource.find().forEach(configInstanceDto -> configInstanceResource.delete(configInstanceDto.getId()));
        configMetadataResource.find().forEach(configInstanceDto -> configMetadataResource.delete(configInstanceDto.getId()));
    }

    @Override
    public Class<ConfigInstanceDto> getClazz() {
        return ConfigInstanceDto.class;
    }
}
