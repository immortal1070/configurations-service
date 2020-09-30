package com.immortal.configurations.service;

import com.immortal.configurations.api.dto.ConfigInstanceDto;
import com.immortal.configurations.api.dto.ConfigInstancePersistDto;
import com.immortal.configurations.api.dto.PropertyValuePersistDto;
import com.immortal.configurations.api.params.PropertyValueSearchParams;
import com.immortal.configurations.dao.ConfigInstanceDao;
import com.immortal.configurations.dao.PropertyValueDao;
import com.immortal.configurations.entity.ConfigInstanceEntity;
import com.immortal.configurations.entity.PropertyValueEntity;
import com.immortal.configurations.interceptors.Logged;
import com.immortal.configurations.transformer.ConfigInstanceTransformer;
import com.immortal.configurations.transformer.PropertyValueTransformer;
import com.immortal.configurations.validation.ExistConfigInstance;
import org.apache.commons.collections4.CollectionUtils;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Logged
@Transactional
public class ConfigInstanceService {
    @Inject
    private ConfigInstanceDao dao;

    @Inject
    private PropertyValueDao propertyValueDao;

    @Inject
    private ConfigInstanceTransformer transformer;

    @Inject
    private PropertyValueTransformer valueTransformer;

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
        ConfigInstanceEntity created = dao.create(transformer.dtoToModifier(dto));
        persistProperties(created, dto.getPropertyValues(), true);
        return transformer.entityToDto(created);
    }

    public ConfigInstanceDto update(@ExistConfigInstance final UUID id, final ConfigInstancePersistDto dto) {
        ConfigInstanceEntity updated = dao.update(id, transformer.dtoToModifier(dto));
        persistProperties(updated, dto.getPropertyValues(), true);
        return transformer.entityToDto(updated);
    }

    public ConfigInstanceDto partialUpdate(@ExistConfigInstance final UUID id, final ConfigInstancePersistDto dto) {
        ConfigInstanceEntity updated = dao.update(id, transformer.dtoToPartialModifier(dto));
        persistProperties(updated, dto.getPropertyValues(), false);
        return transformer.entityToDto(updated);
    }

    public void delete(@ExistConfigInstance final UUID id) {
        propertyValueDao.delete(new PropertyValueSearchParams().setConfigInstanceIds(Collections.singletonList(id)));
        dao.deleteById(id);
    }

    private void persistProperties(final ConfigInstanceEntity configInstanceEntity,
                                   final List<PropertyValuePersistDto> newValues,
                                   final Boolean deleteMissingValues) {
        final List<PropertyValueEntity> existingValues = propertyValueDao.find(new PropertyValueSearchParams()
            .setConfigInstanceIds(Collections.singletonList(configInstanceEntity.getId())));

        final Set<String> namesToPersist = CollectionUtils.emptyIfNull(newValues)
            .stream().map(PropertyValuePersistDto::getName)
            .collect(Collectors.toSet());

        final Map<String, PropertyValueEntity> existingNameToEntity = new HashMap<>();

        final List<UUID> idsToDelete = new ArrayList<>();
        existingValues.forEach(entity -> {
            existingNameToEntity.put(entity.getName(), entity);
            if (deleteMissingValues && !namesToPersist.contains(entity.getName())) {
                idsToDelete.add(entity.getId());
            }
        });

        if (deleteMissingValues && CollectionUtils.isNotEmpty(idsToDelete)) {
            propertyValueDao.delete(new PropertyValueSearchParams().setIds(idsToDelete));
        }

        CollectionUtils.emptyIfNull(newValues).forEach(
            propertyToRegister -> persistValueProperty(configInstanceEntity.getId(),
                propertyToRegister, existingNameToEntity));
    }

    private void persistValueProperty(final UUID configInstanceId,
                                      final PropertyValuePersistDto propertyValue,
                                      final Map<String, PropertyValueEntity> existingNameToEntity) {
        final PropertyValueEntity existing = existingNameToEntity.get(propertyValue.getName());

        final Consumer<PropertyValueEntity> modifier = valueTransformer.dtoToModifier(propertyValue,
            dao.findById(configInstanceId));

        if (existing != null) {
            propertyValueDao.update(existing.getId(), modifier);
        } else {
            propertyValueDao.create(modifier);
        }
    }

}
