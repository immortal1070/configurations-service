package com.immortal.configurations.transformer;

import static com.immortal.configurations.api.dto.ConfigInstancePersistDto.Fields.NAME;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.immortal.configurations.api.dto.ConfigInstanceDto;
import com.immortal.configurations.api.dto.ConfigInstancePersistDto;
import com.immortal.configurations.entity.ConfigInstanceEntity;

public class ConfigInstanceTransformer
{
    private ModifiersMap<ConfigInstanceEntity, ConfigInstancePersistDto> partialUpdate = new ModifiersMap<ConfigInstanceEntity, ConfigInstancePersistDto>()
    {
        {
            put(NAME, (entity, dto) -> entity.setName(dto.getName()));

            //TODO
//            put(CONFIG_METADATA, (entity, dto) -> entity.setConfigMetadata(dto.getConfigMetadata()));
//
//            put(PROPERTY_VALUES, (entity, dto) -> entity.setPropertyValues(dto.getPropertyValues()));

        }
    };

    //To read dto! With id and dates, etc.
    public ConfigInstanceDto entityToDto(final ConfigInstanceEntity entity)
    {
        if (entity == null)
        {
            return null;
        }

        ConfigInstanceDto dto = new ConfigInstanceDto();

        dto.setId(entity.getId());

        dto.setCreateDate(entity.getCreateDate());

        dto.setUpdateDate(entity.getUpdateDate());

        dto.setName(entity.getName());
        //TODO
//        dto.setConfigMetadata(entity.getConfigMetadata());
//
//        dto.setPropertyValues(entity.getPropertyValues());

        return dto;
    }

    public List<ConfigInstanceDto> entitiesToDtos(final List<ConfigInstanceEntity> entities)
    {
        if (CollectionUtils.isEmpty(entities))
        {
            return Collections.emptyList();
        }

        return entities.stream().map(this::entityToDto).collect(Collectors.toList());
    }

//    public ConfigInstanceEventDto entityToJmsDto(final ConfigInstanceEntity entity)
//    {
//        return entityToJmsDto(entity, new ConfigInstanceEventDto());
//    }
//
//    public <T extends ConfigInstanceEventDto> T entityToJmsDto(final ConfigInstanceEntity entity, final T dto)
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
//        dto.setName(entity.getName());
//
//        dto.setConfigMetadata(entity.getConfigMetadata());
//
//        dto.setPropertyValues(entity.getPropertyValues());
//
//        dto.setDeleted(entity.getDeleted());
//
//        return dto;
//    }

    public Consumer<ConfigInstanceEntity> dtoToModifier(final ConfigInstancePersistDto dto)
    {
        return partialUpdate.dtoToModifier(dto, false);
    }

    public Consumer<ConfigInstanceEntity> dtoToPartialModifier(final ConfigInstancePersistDto dto)
    {
        return partialUpdate.dtoToModifier(dto, true);
    }
}
