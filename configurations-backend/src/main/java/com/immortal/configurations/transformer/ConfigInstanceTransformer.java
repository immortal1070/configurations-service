package com.immortal.configurations.transformer;

import com.immortal.configurations.api.dto.ConfigInstanceDto;
import com.immortal.configurations.api.dto.ConfigInstancePersistDto;
import com.immortal.configurations.dao.ConfigMetadataDao;
import com.immortal.configurations.entity.ConfigInstanceEntity;
import org.apache.commons.collections4.CollectionUtils;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static com.immortal.configurations.api.dto.ConfigInstancePersistDto.Fields.*;

public class ConfigInstanceTransformer {

    @Inject
    private ConfigMetadataDao configMetadataDao;

    @Inject
    private PropertyValueTransformer valueTransformer;

    private ModifiersMap<ConfigInstanceEntity, ConfigInstancePersistDto> modifiersMap = new ModifiersMap<ConfigInstanceEntity, ConfigInstancePersistDto>() {
        {
            put(NAME, (entity, dto) -> entity.setName(dto.getName()));
            put(CONFIG_METADATA_ID, (entity, dto) ->
                entity.setConfigMetadata(configMetadataDao.findById(dto.getConfigMetadataId())));
        }
    };

    public ConfigInstanceDto entityToDto(final ConfigInstanceEntity entity) {
        if (entity == null) {
            return null;
        }

        ConfigInstanceDto dto = new ConfigInstanceDto();
        dto.setId(entity.getId());
        dto.setCreateDate(entity.getCreateDate());
        dto.setUpdateDate(entity.getUpdateDate());
        dto.setName(entity.getName());
        dto.setConfigMetadataId(entity.getConfigMetadataId());
        dto.setPropertyValues(valueTransformer.entitiesToDtos(entity.getPropertyValues()));
        return dto;
    }

    public List<ConfigInstanceDto> entitiesToDtos(final List<ConfigInstanceEntity> entities) {
        if (CollectionUtils.isEmpty(entities)) {
            return Collections.emptyList();
        }

        return entities.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public Consumer<ConfigInstanceEntity> dtoToModifier(final ConfigInstancePersistDto dto) {
        return modifiersMap.dtoToModifier(dto, false);
    }

    public Consumer<ConfigInstanceEntity> dtoToPartialModifier(final ConfigInstancePersistDto dto) {
        return modifiersMap.dtoToModifier(dto, true);
    }
}
