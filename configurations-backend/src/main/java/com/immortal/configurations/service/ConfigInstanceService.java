package com.immortal.configurations.service;

import com.immortal.configurations.api.dto.ConfigInstanceDto;
import com.immortal.configurations.api.dto.ConfigInstancePersistDto;
import com.immortal.configurations.dao.ConfigInstanceDao;
import com.immortal.configurations.interceptors.Logged;
import com.immortal.configurations.transformer.ConfigInstanceTransformer;
import com.immortal.configurations.validation.ExistConfigInstance;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@Logged
public class ConfigInstanceService {
    @Inject
    private ConfigInstanceDao dao;

    @Inject
    private ConfigInstanceTransformer transformer;

    public boolean exists(final UUID id) {
        return dao.exists(id);
    }

    public ConfigInstanceDto findById(final UUID id) {
        return transformer.entityToDto(dao.findById(id));
    }

    public List<ConfigInstanceDto> find() {
        return transformer.entitiesToDtos(dao.findAll());
    }

    public ConfigInstanceDto create(final ConfigInstancePersistDto dto) {
        return transformer.entityToDto(dao.create(transformer.dtoToModifier(dto)));
    }

    public ConfigInstanceDto update(@ExistConfigInstance final UUID id, final ConfigInstancePersistDto dto) {
        return transformer.entityToDto(dao.update(id, transformer.dtoToModifier(dto)));
    }

    public ConfigInstanceDto partialUpdate(@ExistConfigInstance final UUID id, final ConfigInstancePersistDto dto) {
        return transformer.entityToDto(dao.update(id, transformer.dtoToPartialModifier(dto)));
    }

    public void delete(@ExistConfigInstance final UUID id) {
        dao.deleteById(id);
    }
}
