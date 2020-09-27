package com.immortal.configurations.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfigMetadataDto implements Serializable {
    private ZonedDateTime createDate;

    private ZonedDateTime updateDate;

    private String id;

    private List<PropertyMetadataDto> propertyMetadatas;

    public ConfigMetadataDto() {
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public ConfigMetadataDto setCreateDate(final ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public ZonedDateTime getUpdateDate() {
        return updateDate;
    }

    public ConfigMetadataDto setUpdateDate(final ZonedDateTime updateDate) {
        this.updateDate = updateDate;
        return this;
    }

    public String getId() {
        return id;
    }

    public ConfigMetadataDto setId(final String id) {
        this.id = id;
        return this;
    }

    public List<PropertyMetadataDto> getPropertyMetadatas() {
        return propertyMetadatas;
    }

    public ConfigMetadataDto setPropertyMetadatas(final List<PropertyMetadataDto> propertyMetadatas) {
        this.propertyMetadatas = propertyMetadatas;
        return this;
    }
}
