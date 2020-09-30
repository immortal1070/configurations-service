package com.immortal.configurations.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.immortal.configurations.api.constants.ConfigurationsMessages;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@XmlAccessorType(XmlAccessType.PROPERTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfigMetadataPersistDto implements PartialUpdateDto, Serializable {
    public interface Fields {
        String PROPERTY_ID = "id";
        String PROPERTY_METADATA_GROUPS = "propertyMetadataGroups";
    }

    @JsonIgnore
    protected final Set<String> receivedFields = new HashSet<>();

    @Size(max = 255, message = ConfigurationsMessages.MAX_LENGTH)
    private String id;

//    @Valid TODO test if needed
    private List<PropertyMetadataGroupDto> propertyMetadataGroups;

    public ConfigMetadataPersistDto() {
    }

    public ConfigMetadataPersistDto(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public ConfigMetadataPersistDto setId(final String id) {
        receivedFields.add(Fields.PROPERTY_ID);
        this.id = id;
        return this;
    }

    public List<PropertyMetadataGroupDto> getPropertyMetadataGroups() {
        return propertyMetadataGroups;
    }

    public ConfigMetadataPersistDto setPropertyMetadataGroups(
        final List<PropertyMetadataGroupDto> propertyMetadataGroups) {
        receivedFields.add(Fields.PROPERTY_METADATA_GROUPS);
        this.propertyMetadataGroups = propertyMetadataGroups;
        return this;
    }

    public Set<String> getReceivedFields() {
        return receivedFields;
    }
}
