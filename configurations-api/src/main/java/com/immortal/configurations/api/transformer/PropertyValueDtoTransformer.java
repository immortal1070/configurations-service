package com.immortal.configurations.api.transformer;

import com.immortal.configurations.api.dto.PropertyValueDto;
import com.immortal.configurations.api.dto.PropertyValuePersistDto;

public class PropertyValueDtoTransformer {
    //To read dto! With id and dates, etc.
    public PropertyValuePersistDto dtoToPersistDto(final PropertyValueDto dto) {
        if (dto == null) {
            return null;
        }

        PropertyValuePersistDto persistDto = new PropertyValuePersistDto();
        persistDto.setConfigurationInstance(dto.getConfigInstance());

        persistDto.setName(dto.getName());

        persistDto.setValue(dto.getValue());

        return persistDto;
    }
}
