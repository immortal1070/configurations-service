package com.immortal.configurations.api.jms;

import java.io.Serializable;

public class ConfigInstanceEventDto implements Serializable
{
    private UUID id;

    private ZonedDateTime createDate;

    private ZonedDateTime updateDate;

    private String name;

    private ConfigMetadataEntity configMetadata;

    private List<PropertyValueEntity> propertyValues;

    private boolean deleted;

    public UUID getId()
    {
        return id;
    }

    public void setId(final UUID id)
    {
        this.id = id;
    }

    public ZonedDateTime getCreateDate()
    {
        return createDate;
    }

    public void setCreateDate(final ZonedDateTime createDate)
    {
        this.createDate = createDate;
    }

    public ZonedDateTime getUpdateDate()
    {
        return updateDate;
    }

    public void setUpdateDate(final ZonedDateTime updateDate)
    {
        this.updateDate = updateDate;
    }

    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public ConfigMetadataEntity getConfigMetadata()
    {
        return configMetadata;
    }

    public void setConfigMetadata(final ConfigMetadataEntity configMetadata)
    {
        this.configMetadata = configMetadata;
    }

    public List<PropertyValueEntity> getPropertyValues()
    {
        return propertyValues;
    }

    public void setPropertyValues(final List<PropertyValueEntity> propertyValues)
    {
        this.propertyValues = propertyValues;
    }

    public boolean getDeleted()
    {
        return deleted;
    }

    public void setDeleted(final boolean deleted)
    {
        this.deleted = deleted;
    }

}