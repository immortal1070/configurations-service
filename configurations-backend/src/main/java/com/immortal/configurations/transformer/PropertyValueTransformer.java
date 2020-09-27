package com.immortal.configurations.transformer;

import com.immortal.configurations.api.dto.PropertyValueDto;
import com.immortal.configurations.api.dto.PropertyValuePersistDto;
import com.immortal.configurations.entity.PropertyValueEntity;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static com.immortal.configurations.api.dto.PropertyValuePersistDto.Fields.NAME;
import static com.immortal.configurations.api.dto.PropertyValuePersistDto.Fields.VALUE;

public class PropertyValueTransformer {
    private ModifiersMap<PropertyValueEntity, PropertyValuePersistDto> partialUpdate = new ModifiersMap<PropertyValueEntity, PropertyValuePersistDto>() {
        {
            //TODO
//            put(CONFIGURATION_INSTANCE,
//                    (entity, dto) -> entity.setConfigurationInstance(dto.getConfigurationInstance()));

            put(NAME, (entity, dto) -> entity.setName(dto.getName()));

            put(VALUE, (entity, dto) -> entity.setValue(dto.getValue()));

        }
    };

    //To read dto! With id and dates, etc.
    public PropertyValueDto entityToDto(final PropertyValueEntity entity) {
        if (entity == null) {
            return null;
        }

        PropertyValueDto dto = new PropertyValueDto();

        dto.setId(entity.getId());

        dto.setCreateDate(entity.getCreateDate());

        dto.setUpdateDate(entity.getUpdateDate());
        //TODO
//        dto.setConfigurationInstance(entity.getConfigurationInstance());

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

//    public PropertyValueEventDto entityToJmsDto(final PropertyValueEntity entity)
//    {
//        return entityToJmsDto(entity, new PropertyValueEventDto());
//    }
//
//    public <T extends PropertyValueEventDto> T entityToJmsDto(final PropertyValueEntity entity, final T dto)
//    {
//        if (entity == null)
//        {
//            return null;
//        }
//
//        dto.setId(entity.getId());
//
//        dto.setCreateDate(entity.getCreateDate());
//
//        dto.setUpdateDate(entity.getUpdateDate());
//
//        dto.setConfigurationInstance(entity.getConfigurationInstance());
//
//        dto.setName(entity.getName());
//
//        dto.setValue(entity.getValue());
//
//        dto.setDeleted(entity.getDeleted());
//
//        return dto;
//    }

    public Consumer<PropertyValueEntity> dtoToModifier(final PropertyValuePersistDto dto) {
        return partialUpdate.dtoToModifier(dto, false);
    }

    public Consumer<PropertyValueEntity> dtoToPartialModifier(final PropertyValuePersistDto dto) {
        return partialUpdate.dtoToModifier(dto, true);
    }
}
