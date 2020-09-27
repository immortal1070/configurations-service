package com.immortal.configurations.rest;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import com.immortal.configurations.api.PropertyValueResource;
import com.immortal.configurations.api.dto.PropertyValueDto;
import com.immortal.configurations.api.dto.PropertyValuePersistDto;
import com.immortal.configurations.interceptors.Logged;
import com.immortal.configurations.service.PropertyValueService;

@Logged
public class PropertyValueController implements PropertyValueResource
{
    @Inject
    private PropertyValueService service;

    @Override
    public PropertyValueDto findById(final UUID id)
    {
        return service.findById(id);
    }

    @Override
    public List<PropertyValueDto> find()
    {
        return service.find();
    }

    @Override
    public PropertyValueDto create(final PropertyValuePersistDto dto)
    {
        final PropertyValueDto created = service.create(dto);
        return service.findById(created.getId());
    }

    @Override
    public PropertyValueDto update(final UUID id, final PropertyValuePersistDto dto)
    {
        return service.update(id, dto);
    }

    @Override
    public PropertyValueDto partialUpdate(final UUID id, final PropertyValuePersistDto dto)
    {
        return service.partialUpdate(id, dto);
    }

    @Override
    public void delete(final UUID id)
    {
        service.delete(id);
    }
}
