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
import java.util.Set;

import static com.immortal.configurations.api.dto.PropertyValuePersistDto.Fields.*;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@XmlAccessorType(XmlAccessType.PROPERTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PropertyValuePersistDto implements PartialUpdateDto, Serializable {
    @JsonIgnore
    protected final Set<String> receivedFields = new HashSet<>();
    private ConfigInstanceDto configInstance;
    @Size(max = 255, message = ConfigurationsMessages.MAX_LENGTH)
    private String name;
    @Size(max = 255, message = ConfigurationsMessages.MAX_LENGTH)
    private String value;

    public PropertyValuePersistDto() {
    }

    public ConfigInstanceDto getConfigurationInstance() {
        return configInstance;
    }

    public void setConfigurationInstance(final ConfigInstanceDto configInstance) {
        receivedFields.add(CONFIGURATION_INSTANCE);
        this.configInstance = configInstance;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        receivedFields.add(NAME);
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        receivedFields.add(VALUE);
        this.value = value;
    }

    public Set<String> getReceivedFields() {
        return receivedFields;
    }

    public interface Fields {
        String CONFIGURATION_INSTANCE = "configInstance";

        String NAME = "name";

        String VALUE = "value";
    }
}
