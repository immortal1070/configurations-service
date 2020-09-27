package com.immortal.configurations.api.jms;

import java.io.Serializable;

public class PropertyValueEventDto implements Serializable {
    private UUID id;

    private ZonedDateTime createDate;

    private ZonedDateTime updateDate;

    private ConfigInstanceEntity configInstance;

    private String name;

    private String value;

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

    public ConfigInstanceEntity getConfigurationInstance() {
        return configInstance;
    }

    public void setConfigurationInstance(final ConfigInstanceEntity configInstance) {
        this.configInstance = configInstance;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(final boolean deleted) {
        this.deleted = deleted;
    }

}
