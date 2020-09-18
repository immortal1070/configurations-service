package com.immortal.configurations.api.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfigInstanceDto implements Serializable
{
    private UUID id;

    private ZonedDateTime createDate;

    private ZonedDateTime updateDate;

    private String name;

    private ConfigMetadataDto configMetadata;

    private List<PropertyValueDto> propertyValues;

    public ConfigInstanceDto()
    {
    }

    public UUID getId()
    {
        return id;
    }

    public ConfigInstanceDto setId(final UUID id)
    {
        this.id = id;
        return this;
    }

    public ZonedDateTime getCreateDate()
    {
        return createDate;
    }

    public ConfigInstanceDto setCreateDate(final ZonedDateTime createDate)
    {
        this.createDate = createDate;
        return this;
    }

    public ZonedDateTime getUpdateDate()
    {
        return updateDate;
    }

    public ConfigInstanceDto setUpdateDate(final ZonedDateTime updateDate)
    {
        this.updateDate = updateDate;
        return this;
    }

    public String getName()
    {
        return name;
    }

    public ConfigInstanceDto setName(final String name)
    {
        this.name = name;
        return this;
    }

    public ConfigMetadataDto getConfigMetadata()
    {
        return configMetadata;
    }

    public ConfigInstanceDto setConfigMetadata(final ConfigMetadataDto configMetadata)
    {
        this.configMetadata = configMetadata;
        return this;
    }

    public List<PropertyValueDto> getPropertyValues()
    {
        return propertyValues;
    }

    public ConfigInstanceDto setPropertyValues(final List<PropertyValueDto> propertyValues)
    {
        this.propertyValues = propertyValues;
        return this;
    }
}
