package com.immortal.configurations.rest;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.immortal.configurations.api.ConfigMetadataResource;
import com.immortal.configurations.api.dto.ConfigMetadataDto;
import com.immortal.configurations.api.dto.ConfigMetadataPersistDto;
import com.immortal.configurations.interceptors.Logged;
import com.immortal.configurations.service.ConfigMetadataService;

@Logged
public class ConfigMetadataController implements ConfigMetadataResource
{
    private final static Logger logger = Logger.getLogger(ConfigMetadataController.class.getName());

    @Inject
    private ConfigMetadataService configMetadataService;

    @Override
    public ConfigMetadataDto findById(final String id)
    {
        return configMetadataService.findById(id);
    }

    @Override
    public List<ConfigMetadataDto> find()
    {
        return configMetadataService.find();
    }

    @Override
    public ConfigMetadataDto register(final ConfigMetadataPersistDto configMetadataPersistDto)
    {
        configMetadataService.register(configMetadataPersistDto);
        return configMetadataService.findById(configMetadataPersistDto.getId());
    }

    @Override
    public void delete(final String id)
    {
        configMetadataService.delete(id);
    }
}
