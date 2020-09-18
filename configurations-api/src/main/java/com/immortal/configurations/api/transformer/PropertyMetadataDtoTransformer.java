package com.immortal.configurations.api.transformer;

import com.immortal.configurations.api.dto.PropertyMetadataDto;
import com.immortal.configurations.api.dto.PropertyMetadataPersistDto;

public class PropertyMetadataDtoTransformer
{
    //To read dto! With id and dates, etc.
    public PropertyMetadataPersistDto dtoToPersistDto(final PropertyMetadataDto dto)
    {
        if (dto == null)
        {
            return null;
        }

        PropertyMetadataPersistDto persistDto = new PropertyMetadataPersistDto();

        persistDto.setConfigMetadataId(dto.getConfigMetadataId());

        persistDto.setName(dto.getName());

        persistDto.setGroup(dto.getGroup());

        persistDto.setType(dto.getType());

        persistDto.setDefaultValue(dto.getDefaultValue());

        persistDto.setPossibleValues(dto.getPossibleValues());

        persistDto.setTags(dto.getTags());

        return persistDto;
    }
}
