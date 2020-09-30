package com.immortal.configurations.prepared;

import com.immortal.configurations.api.dto.*;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.immortal.configurations.prepared.ConfigInstancePreparedData.TestData.CONFIG_INSTANCE_NAME;
import static com.immortal.configurations.prepared.ConfigMetadataPreparedData.TestData.*;

/**
 * If parameter "ConfigMetadataDto" is used in tests, then this resolver will create the entity, will
 * inject it into the parameter and will delete the entity after test is executed.
 */
public class ConfigInstancePreparedData {

    public interface TestData {
        String CONFIG_INSTANCE_NAME = "immortal-system-config";
    }

    public ConfigInstancePersistDto persistDto() {
        ConfigInstancePersistDto configInstancePersistDto = new ConfigInstancePersistDto();
        configInstancePersistDto.setConfigMetadataId(CONFIG_METADATA_TEST_ID);
        configInstancePersistDto.setName(CONFIG_INSTANCE_NAME);
        configInstancePersistDto.setPropertyValues(getPreparedPropertyValues());
        return configInstancePersistDto;
    }

    private List<PropertyValuePersistDto> getPreparedPropertyValues() {
        return Arrays.asList(
            new PropertyValuePersistDto().setName(THEME_PROP_NAME_COLOR_PRIMARY).setValue("#ffee49"),
            new PropertyValuePersistDto().setName(USER_PROP_NAME_LANGUAGE).setValue("ru-RU"),
            new PropertyValuePersistDto().setName(USER_PROP_NAME_EMAILS_ENABLE).setValue("false")
        );
    }

    public ConfigInstanceDto expectedDto() {
        ConfigInstanceDto expected = new ConfigInstanceDto();
        expected.setName(CONFIG_INSTANCE_NAME);
        expected.setConfigMetadataId(CONFIG_METADATA_TEST_ID);
        expected.setPropertyValues(
            getPreparedPropertyValues().stream().map(persistDto -> {
                PropertyValueDto propertyValueDto = new PropertyValueDto();
                propertyValueDto.setName(persistDto.getName());
                propertyValueDto.setValue(persistDto.getValue());
                return propertyValueDto;
            }).collect(Collectors.toList()));
        return expected;
    }

    private void fillExpectedPropMetaDtos(final List<PropertyMetadataDto> expectedList,
                                                 final List<PropertyMetadataRegisterDto> registerDtos,
                                                 final String group) {
        registerDtos.forEach(persistMetadata -> {
            PropertyMetadataDto expectedPropDto = new PropertyMetadataDto();
            copyProperties(expectedPropDto, persistMetadata);
            expectedList.add(expectedPropDto.setGroup(group).setConfigMetadataId(CONFIG_METADATA_TEST_ID));
        });
    }

    private void copyProperties(PropertyMetadataDto expectedPropDto, PropertyMetadataRegisterDto persistMetadata) {
        Arrays.stream(PropertyMetadataRegisterDto.class.getDeclaredFields()).forEach(persistField ->{
                Field expectedField = null;
                try {
                    expectedField = PropertyMetadataDto.class.getDeclaredField(persistField.getName());
                    persistField.setAccessible(true);
                    Object persistValue = persistField.get(persistMetadata);
                    expectedField.setAccessible(true);
                    expectedField.set(expectedPropDto, persistValue);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    return;
                }
            }
            );
    }

}
