package com.immortal.configurations.api.dto;

import static com.immortal.configurations.api.dto.ConfigInstancePersistDto.Fields.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.immortal.configurations.api.constants.ConfigurationsMessages;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@XmlAccessorType(XmlAccessType.PROPERTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfigInstancePersistDto implements PartialUpdateDto, Serializable
{
    public interface Fields
    {
        String NAME = "name";

        String CONFIG_METADATA = "configMetadata";

        String PROPERTY_VALUES = "propertyValues";
    }

    @JsonIgnore
    protected final Set<String> receivedFields = new HashSet<>();
    
    @Size(max = 255, message = ConfigurationsMessages.MAX_LENGTH)
    private String name;

    private ConfigMetadataDto configMetadata;

    private List<PropertyValueDto> propertyValues;

    public ConfigInstancePersistDto()
    {
    }

    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        receivedFields.add(NAME);
        this.name = name;
    }

    public ConfigMetadataDto getConfigMetadata()
    {
        return configMetadata;
    }

    public void setConfigMetadata(final ConfigMetadataDto configMetadata)
    {
        receivedFields.add(CONFIG_METADATA);
        this.configMetadata = configMetadata;
    }

    public List<PropertyValueDto> getPropertyValues()
    {
        return propertyValues;
    }

    public void setPropertyValues(final List<PropertyValueDto> propertyValues)
    {
        receivedFields.add(PROPERTY_VALUES);
        this.propertyValues = propertyValues;
    }

    public Set<String> getReceivedFields()
    {
        return receivedFields;
    }

}
