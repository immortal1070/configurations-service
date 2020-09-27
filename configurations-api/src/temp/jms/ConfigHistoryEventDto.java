package com.immortal.configurations.api.jms;

import java.io.Serializable;

public class ConfigHistoryEventDto implements Serializable {
    private UUID id;

    private ZonedDateTime createDate;

    private ZonedDateTime updateDate;

    private String objectType;

    private String action;

    private String object_id;

    private String object_name;

    private String userId;

    private String object;

    private String objectBeforeChange;

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

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(final String objectType) {
        this.objectType = objectType;
    }

    public String getAction() {
        return action;
    }

    public void setAction(final String action) {
        this.action = action;
    }

    public String getObject_id() {
        return object_id;
    }

    public void setObject_id(final String object_id) {
        this.object_id = object_id;
    }

    public String getObject_name() {
        return object_name;
    }

    public void setObject_name(final String object_name) {
        this.object_name = object_name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(final String userId) {
        this.userId = userId;
    }

    public String getObject() {
        return object;
    }

    public void setObject(final String object) {
        this.object = object;
    }

    public String getObjectBeforeChange() {
        return objectBeforeChange;
    }

    public void setObjectBeforeChange(final String objectBeforeChange) {
        this.objectBeforeChange = objectBeforeChange;
    }

}
