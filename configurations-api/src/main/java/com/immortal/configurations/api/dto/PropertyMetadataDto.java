package com.immortal.configurations.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PropertyMetadataDto implements Serializable {
    private UUID id;

    private ZonedDateTime createDate;

    private ZonedDateTime updateDate;

    private String configMetadataId;

    private String name;

    private String group;

    private String type;

    private String defaultValue;

    private List<String> possibleValues;

    private List<String> tags;

    public PropertyMetadataDto() {
    }

    public UUID getId() {
        return id;
    }

    public PropertyMetadataDto setId(final UUID id) {
        this.id = id;
        return this;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public PropertyMetadataDto setCreateDate(final ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public ZonedDateTime getUpdateDate() {
        return updateDate;
    }

    public PropertyMetadataDto setUpdateDate(final ZonedDateTime updateDate) {
        this.updateDate = updateDate;
        return this;
    }

    public String getConfigMetadataId() {
        return configMetadataId;
    }

    public PropertyMetadataDto setConfigMetadataId(final String configMetadataId) {
        this.configMetadataId = configMetadataId;
        return this;
    }

    public String getName() {
        return name;
    }

    public PropertyMetadataDto setName(final String name) {
        this.name = name;
        return this;
    }

    public String getGroup() {
        return group;
    }

    public PropertyMetadataDto setGroup(final String group) {
        this.group = group;
        return this;
    }

    public String getType() {
        return type;
    }

    public PropertyMetadataDto setType(final String type) {
        this.type = type;
        return this;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public PropertyMetadataDto setDefaultValue(final String defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public List<String> getPossibleValues() {
        return possibleValues;
    }

    public PropertyMetadataDto setPossibleValues(final List<String> possibleValues) {
        this.possibleValues = possibleValues;
        return this;
    }

    public List<String> getTags() {
        return tags;
    }

    public PropertyMetadataDto setTags(final List<String> tags) {
        this.tags = tags;
        return this;
    }
}
