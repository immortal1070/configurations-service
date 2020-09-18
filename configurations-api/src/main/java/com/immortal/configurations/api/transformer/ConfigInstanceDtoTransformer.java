package com.immortal.configurations.api.transformer;

import com.immortal.configurations.api.dto.ConfigInstanceDto;
import com.immortal.configurations.api.dto.ConfigInstancePersistDto;

public class ConfigInstanceDtoTransformer
{
    //To read dto! With id and dates, etc.
    public ConfigInstancePersistDto dtoToPersistDto(final ConfigInstanceDto dto)
    {
        if (dto == null)
        {
            return null;
        }

        ConfigInstancePersistDto persistDto = new ConfigInstancePersistDto();

        persistDto.setName(dto.getName());

        persistDto.setConfigMetadata(dto.getConfigMetadata());

        persistDto.setPropertyValues(dto.getPropertyValues());

        return persistDto;
    }
}
