package com.immortal.configurations.api.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PropertyValueDto implements Serializable
{
    private UUID id;

    private ZonedDateTime createDate;

    private ZonedDateTime updateDate;

    private ConfigInstanceDto configInstance;

    private String name;

    private String value;

    public PropertyValueDto()
    {
    }

    public UUID getId()
    {
        return id;
    }

    public PropertyValueDto setId(final UUID id)
    {
        this.id = id;
        return this;
    }

    public ZonedDateTime getCreateDate()
    {
        return createDate;
    }

    public PropertyValueDto setCreateDate(final ZonedDateTime createDate)
    {
        this.createDate = createDate;
        return this;
    }

    public ZonedDateTime getUpdateDate()
    {
        return updateDate;
    }

    public PropertyValueDto setUpdateDate(final ZonedDateTime updateDate)
    {
        this.updateDate = updateDate;
        return this;
    }

    public ConfigInstanceDto getConfigInstance()
    {
        return configInstance;
    }

    public PropertyValueDto setConfigInstance(final ConfigInstanceDto configInstance)
    {
        this.configInstance = configInstance;
        return this;
    }

    public String getName()
    {
        return name;
    }

    public PropertyValueDto setName(final String name)
    {
        this.name = name;
        return this;
    }

    public String getValue()
    {
        return value;
    }

    public PropertyValueDto setValue(final String value)
    {
        this.value = value;
        return this;
    }
}
