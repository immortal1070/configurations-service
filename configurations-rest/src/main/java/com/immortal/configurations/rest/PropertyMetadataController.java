package com.immortal.configurations.rest;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import com.immortal.configurations.api.PropertyMetadataResource;
import com.immortal.configurations.api.dto.PropertyMetadataDto;
import com.immortal.configurations.api.dto.PropertyMetadataPersistDto;
import com.immortal.configurations.api.params.PropertyMetadataSearchParams;
import com.immortal.configurations.interceptors.Logged;
import com.immortal.configurations.service.PropertyMetadataService;

@Logged
public class PropertyMetadataController implements PropertyMetadataResource
{
    @Inject
    private PropertyMetadataService service;

    @Override
    public PropertyMetadataDto findById(final UUID id)
    {
        return service.findById(id);
    }

    @Override
    public List<PropertyMetadataDto> find(final PropertyMetadataSearchParams searchParams)
    {
        return service.find(searchParams);
    }

    @Override
    public PropertyMetadataDto create(final PropertyMetadataPersistDto dto)
    {
        final PropertyMetadataDto created = service.create(dto);
        return service.findById(created.getId());
    }

    @Override
    public PropertyMetadataDto update(final UUID id, final PropertyMetadataPersistDto dto)
    {
        return service.update(id, dto);
    }

    @Override
    public PropertyMetadataDto partialUpdate(final UUID id, final PropertyMetadataPersistDto dto)
    {
        return service.partialUpdate(id, dto);
    }

    @Override
    public void delete(final UUID id)
    {
        service.delete(id);
    }
}
