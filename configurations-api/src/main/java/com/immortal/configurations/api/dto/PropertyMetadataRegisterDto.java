package com.immortal.configurations.api.dto;

import static com.immortal.configurations.api.dto.PropertyMetadataRegisterDto.Fields.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.immortal.configurations.api.constants.ConfigurationsMessages;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@XmlAccessorType(XmlAccessType.PROPERTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PropertyMetadataRegisterDto implements PartialUpdateDto, Serializable
{
    @JsonIgnore
    protected final Set<String> receivedFields = new HashSet<>();

    public interface Fields
    {
        String NAME = "name";

        String TYPE = "type";

        String DEFAULT_VALUE = "defaultValue";

        String POSSIBLE_VALUES = "possibleValues";

        String TAGS = "tags";
    }

    @Size(max = 255, message = ConfigurationsMessages.MAX_LENGTH)
    private String name;

    @Size(max = 255, message = ConfigurationsMessages.MAX_LENGTH)
    private String type;

    @Size(max = 255, message = ConfigurationsMessages.MAX_LENGTH)
    private String defaultValue;

    private List<String> possibleValues;

    private List<String> tags;

    public PropertyMetadataRegisterDto()
    {
    }

    public PropertyMetadataRegisterDto(final PropertyMetadataRegisterDto original)
    {
        if (original.getReceivedFields().contains(NAME))
        {
            setName(original.name);
        }
        if (original.getReceivedFields().contains(TYPE))
        {
            setType(original.type);
        }
        if (original.getReceivedFields().contains(DEFAULT_VALUE))
        {
            setDefaultValue(original.defaultValue);
        }
        if (original.getReceivedFields().contains(POSSIBLE_VALUES))
        {
            setPossibleValues(original.possibleValues);
        }
        if (original.getReceivedFields().contains(TAGS))
        {
            setTags(original.tags);
        }
    }

    public String getName()
    {
        return name;
    }

    public PropertyMetadataRegisterDto setName(final String name)
    {
        receivedFields.add(NAME);
        this.name = name;
        return this;
    }

    public String getType()
    {
        return type;
    }

    public PropertyMetadataRegisterDto setType(final String type)
    {
        receivedFields.add(TYPE);
        this.type = type;
        return this;
    }

    public String getDefaultValue()
    {
        return defaultValue;
    }

    public PropertyMetadataRegisterDto setDefaultValue(final String defaultValue)
    {
        receivedFields.add(DEFAULT_VALUE);
        this.defaultValue = defaultValue;
        return this;
    }

    public List<String> getPossibleValues()
    {
        return possibleValues;
    }

    public PropertyMetadataRegisterDto setPossibleValues(final List<String> possibleValues)
    {
        receivedFields.add(POSSIBLE_VALUES);
        this.possibleValues = possibleValues;
        return this;
    }

    public List<String> getTags()
    {
        return tags;
    }

    public PropertyMetadataRegisterDto setTags(final List<String> tags)
    {
        receivedFields.add(TAGS);
        this.tags = tags;
        return this;
    }

    public Set<String> getReceivedFields()
    {
        return receivedFields;
    }
}
