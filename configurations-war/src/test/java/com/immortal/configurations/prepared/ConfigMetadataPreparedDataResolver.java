package com.immortal.configurations.prepared;

import com.immortal.configurations.api.ConfigMetadataResource;
import com.immortal.configurations.api.dto.ConfigMetadataDto;
import com.immortal.configurations.util.TestRestClient;
import org.jboss.logging.Logger;


/**
 * If parameter "ConfigMetadataDto" is used in tests, then this resolver will create the entity, will
 * inject it into the parameter and will delete the entity after test is executed.
 */
public class ConfigMetadataPreparedDataResolver extends PreparedTestDataResolver<ConfigMetadataDto> {

    private final static Logger logger = Logger.getLogger(ConfigMetadataPreparedDataResolver.class);

    private final static ConfigMetadataResource resource = new TestRestClient()
        .getRestProxy(ConfigMetadataResource.class);

    @Override
    public ConfigMetadataDto create() {
        return resource.register(new ConfigMetadataPreparedData().persistDto());
    }

    @Override
    public void cleanup() {
        resource.find().forEach(configMetadataDto -> resource.delete(configMetadataDto.getId()));
    }

    @Override
    public Class<ConfigMetadataDto> getClazz() {
        return ConfigMetadataDto.class;
    }



}
