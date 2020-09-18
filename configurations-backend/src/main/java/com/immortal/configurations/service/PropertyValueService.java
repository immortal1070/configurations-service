package com.immortal.configurations.service;

import java.util.List;
import java.util.UUID;


import javax.inject.Inject;

import com.immortal.configurations.api.dto.PropertyValueDto;
import com.immortal.configurations.api.dto.PropertyValuePersistDto;
import com.immortal.configurations.dao.PropertyValueDao;
import com.immortal.configurations.interceptors.Logged;
import com.immortal.configurations.transformer.PropertyValueTransformer;
import com.immortal.configurations.validation.ExistPropertyValue;

@Logged
public class PropertyValueService
{
    @Inject
    private PropertyValueDao dao;

    @Inject
    private PropertyValueTransformer transformer;

    public boolean exists(final UUID id)
    {
        return dao.exists(id);
    }

    public PropertyValueDto findById(final UUID id)
    {
        return transformer.entityToDto(dao.findById(id));
    }

    public List<PropertyValueDto> find()
    {
        return transformer.entitiesToDtos(dao.findAll());
    }

    public PropertyValueDto create(final PropertyValuePersistDto dto)
    {
        return transformer.entityToDto(dao.create(transformer.dtoToModifier(dto)));
    }

    public PropertyValueDto update(@ExistPropertyValue final UUID id, final PropertyValuePersistDto dto)
    {
        return transformer.entityToDto(dao.update(id, transformer.dtoToModifier(dto)));
    }

    public PropertyValueDto partialUpdate(@ExistPropertyValue final UUID id, final PropertyValuePersistDto dto)
    {
        return transformer.entityToDto(dao.update(id, transformer.dtoToPartialModifier(dto)));
    }

    public void delete(@ExistPropertyValue final UUID id)
    {
        dao.deleteById(id);
    }
}
