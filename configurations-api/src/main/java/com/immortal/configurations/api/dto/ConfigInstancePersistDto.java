package com.immortal.configurations.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.immortal.configurations.api.constants.ConfigurationsMessages;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.immortal.configurations.api.dto.ConfigInstancePersistDto.Fields.*;

//TODO add  smart   @NotBlank validator which checks received fields
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@XmlAccessorType(XmlAccessType.PROPERTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfigInstancePersistDto implements PartialUpdateDto, Serializable {
    public interface Fields {
        String NAME = "name";
        String CONFIG_METADATA_ID = "configMetadataId";
        String PROPERTY_VALUES = "propertyValues";
    }

    @JsonIgnore
    protected final Set<String> receivedFields = new HashSet<>();

    @Size(max = 255, message = ConfigurationsMessages.MAX_LENGTH)
    private String name;

    private String configMetadataId;

    private List<PropertyValuePersistDto> propertyValues;

    public ConfigInstancePersistDto() {
    }

    public String getName() {
        return name;
    }

    public ConfigInstancePersistDto setName(final String name) {
        receivedFields.add(NAME);
        this.name = name;
        return this;
    }

    public String getConfigMetadataId() {
        return configMetadataId;
    }

    public ConfigInstancePersistDto setConfigMetadataId(final String configMetadataId) {
        receivedFields.add(CONFIG_METADATA_ID);
        this.configMetadataId = configMetadataId;
        return this;
    }

    public List<PropertyValuePersistDto> getPropertyValues() {
        return propertyValues;
    }

    public ConfigInstancePersistDto setPropertyValues(final List<PropertyValuePersistDto> propertyValues) {
        receivedFields.add(PROPERTY_VALUES);
        this.propertyValues = propertyValues;
        return this;
    }

    public Set<String> getReceivedFields() {
        return receivedFields;
    }

}
