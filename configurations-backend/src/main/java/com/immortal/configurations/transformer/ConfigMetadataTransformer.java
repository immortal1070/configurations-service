package com.immortal.configurations.transformer;

import com.immortal.configurations.api.dto.ConfigMetadataDto;
import com.immortal.configurations.api.dto.PropertyMetadataDto;
import com.immortal.configurations.api.params.PropertyMetadataSearchParams;
import com.immortal.configurations.entity.ConfigMetadataEntity;
import com.immortal.configurations.entity.PropertyMetadataEntity;
import com.immortal.configurations.service.PropertyMetadataService;
import org.apache.commons.collections4.CollectionUtils;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Transactional
public class ConfigMetadataTransformer {

    @Inject
    private PropertyMetadataService propertyMetadataService;

    public ConfigMetadataDto entityToDto(final ConfigMetadataEntity entity) {
        if (entity == null) {
            return null;
        }

        ConfigMetadataDto dto = new ConfigMetadataDto();
        dto.setCreateDate(entity.getCreateDate());
        dto.setUpdateDate(entity.getUpdateDate());
        dto.setId(entity.getId());

        List<PropertyMetadataDto> propertyMetadatas = propertyMetadataService.find(
            new PropertyMetadataSearchParams().setConfigMetadataIds(Collections.singletonList(entity.getId())),
            PropertyMetadataEntity.Graphs.TAGS);
        dto.setPropertyMetadatas(
            propertyMetadatas
        );
        return dto;
    }

    public List<ConfigMetadataDto> entitiesToDtos(final List<ConfigMetadataEntity> entities) {
        if (CollectionUtils.isEmpty(entities)) {
            return Collections.emptyList();
        }

        return entities.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}
