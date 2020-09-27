package com.immortal.configurations.service;

import com.immortal.configurations.api.dto.PropertyMetadataDto;
import com.immortal.configurations.api.dto.PropertyMetadataGroupDto;
import com.immortal.configurations.api.dto.PropertyMetadataPersistDto;
import com.immortal.configurations.api.dto.PropertyMetadataRegisterDto;
import com.immortal.configurations.api.params.PropertyMetadataSearchParams;
import com.immortal.configurations.dao.PropertyMetadataDao;
import com.immortal.configurations.entity.PropertyMetadataEntity;
import com.immortal.configurations.interceptors.Logged;
import com.immortal.configurations.transformer.PropertyMetadataTransformer;
import com.immortal.configurations.validation.ExistConfigMetadata;
import com.immortal.configurations.validation.ExistPropertyMetadata;
import org.apache.commons.collections4.CollectionUtils;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Logged
@Transactional
public class PropertyMetadataService {
    public static final Logger logger = Logger.getLogger(PropertyMetadataService.class.getName());

    @Inject
    private PropertyMetadataDao dao;

    @Inject
    private PropertyMetadataTransformer transformer;

    public boolean exists(final UUID id) {
        return dao.exists(id);
    }

    public PropertyMetadataDto findById(final UUID id) {
        return transformer.entityToDto(dao.findById(id));
    }

    public List<PropertyMetadataDto> find(final PropertyMetadataSearchParams searchParams) {
        return find(searchParams, null);
    }

    public List<PropertyMetadataDto> find(final PropertyMetadataSearchParams searchParams, final String graphName) {
        return transformer.entitiesToDtos(dao.find(searchParams, graphName));
    }

    public PropertyMetadataDto create(final PropertyMetadataPersistDto dto) {
        return transformer.entityToDto(dao.create(transformer.dtoToModifier(dto)));
    }

    public PropertyMetadataDto update(@ExistPropertyMetadata final UUID id, final PropertyMetadataPersistDto dto) {
        return transformer.entityToDto(dao.update(id, transformer.dtoToModifier(dto)));
    }

    public PropertyMetadataDto partialUpdate(@ExistPropertyMetadata final UUID id, final PropertyMetadataPersistDto dto) {
        return transformer.entityToDto(dao.update(id, transformer.dtoToPartialModifier(dto)));
    }

    public void delete(@ExistPropertyMetadata final UUID id) {
        dao.deleteById(id);
    }

    public void delete(final PropertyMetadataSearchParams searchParams) {
        dao.delete(searchParams);
    }

    /**
     * Registers the given group properties metadata.
     * <p>
     * Deletes the existing properties metadata if they are not present in the new group
     * Adds / updates needed properties metadata.
     * <p>
     * In the end the group in the database will contain only data from the input parameters.
     */
    public void registerGroup(@ExistConfigMetadata final String configMetadataId,
                              final PropertyMetadataGroupDto metadataGroup) {
        final List<PropertyMetadataEntity> existingPropertiesInGroup = dao.find(new PropertyMetadataSearchParams()
            .setGroups(Collections.singletonList(metadataGroup.getGroupName())));

        final List<PropertyMetadataRegisterDto> propertiesToRegister = metadataGroup.getPropertiesMetadatas();
        final Set<String> namesToRegister = propertiesToRegister.stream().map(PropertyMetadataRegisterDto::getName)
            .collect(Collectors.toSet());

        final Map<String, PropertyMetadataEntity> existingNameToEntity = new HashMap<>();
        final List<UUID> idsToDelete = new ArrayList<>();
        existingPropertiesInGroup.forEach(entity -> {
            existingNameToEntity.put(entity.getName(), entity);
            if (!namesToRegister.contains(entity.getName())) {
                idsToDelete.add(entity.getId());
            }
        });

        if (CollectionUtils.isNotEmpty(idsToDelete)) {
            dao.delete(new PropertyMetadataSearchParams().setIds(idsToDelete));
        }

        propertiesToRegister.forEach(
            propertyToRegister -> registerProperty(configMetadataId, metadataGroup.getGroupName(),
                propertyToRegister, existingNameToEntity));
    }

    private void registerProperty(final String metadataId, final String group,
                                  final PropertyMetadataRegisterDto propertyMetadata,
                                  final Map<String, PropertyMetadataEntity> existingNameToEntity) {
        final PropertyMetadataEntity existing = existingNameToEntity.get(propertyMetadata.getName());

        final Consumer<PropertyMetadataEntity> modifier = transformer
            .dtoToModifier(metadataId, group, propertyMetadata);

        if (existing != null) {
            dao.update(existing.getId(), modifier);
        } else {
            dao.create(modifier);
        }
    }
}
