package com.immortal.configurations.transformer;

import static com.immortal.configurations.api.dto.PropertyMetadataPersistDto.Fields.CONFIG_METADATA_ID;
import static com.immortal.configurations.api.dto.PropertyMetadataPersistDto.Fields.GROUP;
import static com.immortal.configurations.api.dto.PropertyMetadataRegisterDto.Fields.*;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.commons.collections4.CollectionUtils;

import com.immortal.configurations.api.dto.PropertyMetadataDto;
import com.immortal.configurations.api.dto.PropertyMetadataPersistDto;
import com.immortal.configurations.api.dto.PropertyMetadataRegisterDto;
import com.immortal.configurations.dao.ConfigMetadataDao;
import com.immortal.configurations.entity.PropertyMetadataEntity;

public class PropertyMetadataTransformer
{
    @Inject
    private ConfigMetadataDao configMetadataDao;

    private final ModifiersMap<PropertyMetadataEntity, PropertyMetadataPersistDto> modifiersMap = new ModifiersMap<PropertyMetadataEntity, PropertyMetadataPersistDto>()
    {
        {
            put(NAME, (entity, dto) -> entity.setName(dto.getName()));
            put(TYPE, (entity, dto) -> entity.setType(dto.getType()));
            put(DEFAULT_VALUE, (entity, dto) -> entity.setDefaultValue(dto.getDefaultValue()));
            put(POSSIBLE_VALUES, (entity, dto) -> entity.setPossibleValues(dto.getPossibleValues()));
            put(TAGS, (entity, dto) -> entity.setTags(dto.getTags()));
            put(CONFIG_METADATA_ID,
                    (entity, dto) -> entity.setConfigMetadata(configMetadataDao.findById(dto.getConfigMetadataId())));
            put(GROUP, (entity, dto) -> entity.setGroup(dto.getGroup()));
        }
    };

    public PropertyMetadataDto entityToDto(final PropertyMetadataEntity entity)
    {
        if (entity == null)
        {
            return null;
        }

        PropertyMetadataDto dto = new PropertyMetadataDto();

        dto.setId(entity.getId());
        dto.setCreateDate(entity.getCreateDate());
        dto.setUpdateDate(entity.getUpdateDate());
        dto.setConfigMetadataId(entity.getConfigMetadata().getId());
        dto.setName(entity.getName());
        dto.setGroup(entity.getGroup());
        dto.setType(entity.getType());
        dto.setDefaultValue(entity.getDefaultValue());
        dto.setPossibleValues(entity.getPossibleValues());
        dto.setTags(entity.getTags());
        return dto;
    }

    public List<PropertyMetadataDto> entitiesToDtos(final List<PropertyMetadataEntity> entities)
    {
        if (CollectionUtils.isEmpty(entities))
        {
            return Collections.emptyList();
        }

        return entities.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public Consumer<PropertyMetadataEntity> dtoToModifier(final PropertyMetadataPersistDto dto)
    {
        return modifiersMap.dtoToModifier(dto, false);
    }

    public Consumer<PropertyMetadataEntity> dtoToPartialModifier(final PropertyMetadataPersistDto dto)
    {
        return modifiersMap.dtoToModifier(dto, true);
    }

    public Consumer<PropertyMetadataEntity> dtoToModifier(final String configMetadataId,
            final String group,
            final PropertyMetadataRegisterDto propertyMetadata)
    {
        PropertyMetadataPersistDto persistDto = new PropertyMetadataPersistDto(propertyMetadata)
                .setGroup(group).setConfigMetadataId(configMetadataId);
        return modifiersMap.dtoToModifier(persistDto, true);
    }

    //    public PropertyMetadataEventDto entityToJmsDto(final PropertyMetadataEntity entity)
    //    {
    //        return entityToJmsDto(entity, new PropertyMetadataEventDto());
    //    }
    //
    //    public <T extends PropertyMetadataEventDto> T entityToJmsDto(final PropertyMetadataEntity entity, final T dto)
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
    //        dto.setConfigMetadata(entity.getConfigMetadata());
    //
    //        dto.setName(entity.getName());
    //
    //        dto.setGroup(entity.getGroup());
    //
    //        dto.setType(entity.getType());
    //
    //        dto.setDefaultValue(entity.getDefaultValue());
    //
    //        dto.setPossibleValues(entity.getPossibleValues());
    //
    //        dto.setTags(entity.getTags());
    //
    //        dto.setDeleted(entity.getDeleted());
    //
    //        return dto;
    //    }
}
