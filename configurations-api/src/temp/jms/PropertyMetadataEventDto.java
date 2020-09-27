package com.immortal.configurations.api.jms;

import java.io.Serializable;

public class PropertyMetadataEventDto implements Serializable {
    private UUID id;

    private ZonedDateTime createDate;

    private ZonedDateTime updateDate;

    private ConfigMetadataEntity configMetadata;

    private String name;

    private String group;

    private String type;

    private String defaultValue;

    private List<String> possibleValues;

    private List<String> tags;

    private boolean deleted;

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
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

    public ConfigMetadataEntity getConfigMetadata() {
        return configMetadata;
    }

    public void setConfigMetadata(final ConfigMetadataEntity configMetadata) {
        this.configMetadata = configMetadata;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(final String group) {
        this.group = group;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(final String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public List<String> getPossibleValues() {
        return possibleValues;
    }

    public void setPossibleValues(final List<String> possibleValues) {
        this.possibleValues = possibleValues;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(final List<String> tags) {
        this.tags = tags;
    }

    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(final boolean deleted) {
        this.deleted = deleted;
    }

}
