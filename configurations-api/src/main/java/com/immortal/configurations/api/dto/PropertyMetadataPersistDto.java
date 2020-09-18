package com.immortal.configurations.api.dto;

import static com.immortal.configurations.api.dto.PropertyMetadataPersistDto.Fields.CONFIG_METADATA_ID;
import static com.immortal.configurations.api.dto.PropertyMetadataPersistDto.Fields.GROUP;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.immortal.configurations.api.constants.ConfigurationsMessages;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@XmlAccessorType(XmlAccessType.PROPERTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PropertyMetadataPersistDto extends PropertyMetadataRegisterDto
{
    public interface Fields
    {
        String CONFIG_METADATA_ID = "configMetadataId";
        String GROUP = "group";
    }

    private String configMetadataId;

    @Size(max = 255, message = ConfigurationsMessages.MAX_LENGTH)
    private String group;

    public PropertyMetadataPersistDto()
    {
    }

    public PropertyMetadataPersistDto(final PropertyMetadataRegisterDto registerDto)
    {
        super(registerDto);
    }

    public String getConfigMetadataId()
    {
        return configMetadataId;
    }

    public PropertyMetadataPersistDto setConfigMetadataId(final String configMetadataId)
    {
        receivedFields.add(CONFIG_METADATA_ID);
        this.configMetadataId = configMetadataId;
        return this;
    }

    public String getGroup()
    {
        return group;
    }

    public PropertyMetadataPersistDto setGroup(final String group)
    {
        receivedFields.add(GROUP);
        this.group = group;
        return this;
    }
}
