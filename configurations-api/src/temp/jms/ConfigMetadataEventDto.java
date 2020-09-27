package com.immortal.configurations.api.jms;

import java.io.Serializable;

public class ConfigMetadataEventDto implements Serializable {
    private UUID id;

    private ZonedDateTime createDate;

    private ZonedDateTime updateDate;

    private String id;

    private List<PropertyMetadataEntity> propertyMetadatas;

    private boolean deleted;

    public UUID getId() {
        return id;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(final ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public ZonedDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(final ZonedDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public String getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public List<PropertyMetadataEntity> getPropertyMetadatas() {
        return propertyMetadatas;
    }

    public void setPropertyMetadatas(final List<PropertyMetadataEntity> propertyMetadatas) {
        this.propertyMetadatas = propertyMetadatas;
    }

    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(final boolean deleted) {
        this.deleted = deleted;
    }

}
