package com.immortal.configurations.rest;

import com.immortal.configurations.api.ConfigInstanceResource;
import com.immortal.configurations.api.dto.ConfigInstanceDto;
import com.immortal.configurations.api.dto.ConfigInstancePersistDto;
import com.immortal.configurations.interceptors.Logged;
import com.immortal.configurations.service.ConfigInstanceService;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Logged
public class ConfigInstanceController implements ConfigInstanceResource {
    @Inject
    private ConfigInstanceService service;

    @Override
    public ConfigInstanceDto findById(final UUID id) {
        return service.findById(id);
    }

    @Override
    public List<ConfigInstanceDto> find() {
        return service.find();
    }

    @Override
    public ConfigInstanceDto create(@Valid final ConfigInstancePersistDto dto) {
        final ConfigInstanceDto created = service.create(dto);
        //TODO add it everywhere
        return service.findById(created.getId());
    }

    @Override
    public ConfigInstanceDto update(final UUID id, @Valid final ConfigInstancePersistDto dto) {
        return service.update(id, dto);
    }

    @Override
    public ConfigInstanceDto partialUpdate(final UUID id, @Valid final ConfigInstancePersistDto dto) {
        return service.partialUpdate(id, dto);
    }

    @Override
    public void delete(final UUID id) {
        service.delete(id);
    }
}
