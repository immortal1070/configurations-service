package com.immortal.configurations.transformer;

import com.immortal.configurations.api.dto.PropertyValueDto;
import com.immortal.configurations.api.dto.PropertyValuePersistDto;
import com.immortal.configurations.entity.ConfigInstanceEntity;
import com.immortal.configurations.entity.PropertyValueEntity;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class PropertyValueTransformer {

    public Consumer<PropertyValueEntity> dtoToModifier(final PropertyValuePersistDto dto, final ConfigInstanceEntity configInstanceEntity) {
        return (entity) -> {
            entity.setName(dto.getName());
            entity.setValue(dto.getValue());
            entity.setConfigInstance(configInstanceEntity);
            entity.setConfigInstanceId(configInstanceEntity.getId());
        };
    }

    public PropertyValueDto entityToDto(final PropertyValueEntity entity) {
        if (entity == null) {
            return null;
        }

        PropertyValueDto dto = new PropertyValueDto();

        dto.setId(entity.getId());
        dto.setCreateDate(entity.getCreateDate());
        dto.setUpdateDate(entity.getUpdateDate());
        dto.setConfigInstanceId(entity.getConfigInstanceId());
        dto.setName(entity.getName());
        dto.setValue(entity.getValue());
        return dto;
    }

    public List<PropertyValueDto> entitiesToDtos(final List<PropertyValueEntity> entities) {
        if (CollectionUtils.isEmpty(entities)) {
            return Collections.emptyList();
        }

        return entities.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}
