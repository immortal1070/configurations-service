package com.immortal.configurations.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

/**
 * Presents a group of property metadatas.
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class PropertyMetadataGroupDto implements Serializable {
    private String groupName;

    private List<PropertyMetadataRegisterDto> propertiesMetadatas;

    public PropertyMetadataGroupDto() {
    }

    public PropertyMetadataGroupDto(final String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public PropertyMetadataGroupDto setGroupName(final String groupName) {
        this.groupName = groupName;
        return this;
    }

    public List<PropertyMetadataRegisterDto> getPropertiesMetadatas() {
        return propertiesMetadatas;
    }

    public PropertyMetadataGroupDto setPropertiesMetadatas(final List<PropertyMetadataRegisterDto> propertiesMetadatas) {
        this.propertiesMetadatas = propertiesMetadatas;
        return this;
    }
}
