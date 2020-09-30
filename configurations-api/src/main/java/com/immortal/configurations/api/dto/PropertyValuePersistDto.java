package com.immortal.configurations.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.immortal.configurations.api.constants.ConfigurationsMessages;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PropertyValuePersistDto implements Serializable {
    @Size(max = 255, message = ConfigurationsMessages.MAX_LENGTH)
    private String name;

    private String value;

    public PropertyValuePersistDto() {
    }

    public PropertyValuePersistDto(@NotBlank @Size(max = 255,
        message = ConfigurationsMessages.MAX_LENGTH) final String name, final String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public PropertyValuePersistDto setName(final String name) {
        this.name = name;
        return this;
    }

    public String getValue() {
        return value;
    }

    public PropertyValuePersistDto setValue(final String value) {
        this.value = value;
        return this;
    }
}
