package com.immortal.configurations.prepared;

import com.immortal.configurations.api.dto.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.immortal.configurations.prepared.ConfigMetadataPreparedData.TestData.*;

/**
 * If parameter "ConfigMetadataDto" is used in tests, then this resolver will create the entity, will
 * inject it into the parameter and will delete the entity after test is executed.
 */
public class ConfigMetadataPreparedData {
    public interface TestData {
        String USER_PROPERTIES_GROUP = "userProperties";
        String THEME_PROPERTIES_GROUP = "themeProperties";
        String CONFIG_METADATA_TEST_ID = "someConfigMetadataId";
        String THEME_PROP_NAME_COLOR_PRIMARY = "theme.primary.color";
        String THEME_PROP_NAME_COLOR_SECONDARY = "theme.secondary.color";
        String USER_PROP_NAME_LANGUAGE = "user.default.language";
        String USER_PROP_NAME_EMAILS_ENABLE = "user.default.emails.enable";
    }

    public ConfigMetadataPersistDto persistDto() {
        ConfigMetadataPersistDto configMetadataPersistDto = new ConfigMetadataPersistDto(CONFIG_METADATA_TEST_ID);
        PropertyMetadataGroupDto userProperiesGroup =
            new PropertyMetadataGroupDto(USER_PROPERTIES_GROUP).setPropertiesMetadatas(
                prepareUserPropertiesMetadata()
            );
        PropertyMetadataGroupDto themeProperiesGroup = new PropertyMetadataGroupDto(THEME_PROPERTIES_GROUP)
            .setPropertiesMetadatas(prepareThemePropertiesMetadata());

        configMetadataPersistDto.setPropertyMetadataGroups(Arrays.asList(userProperiesGroup,
            themeProperiesGroup));
        return configMetadataPersistDto;
    }

    public List<PropertyMetadataRegisterDto> prepareThemePropertiesMetadata() {
        return Arrays.asList(
            new PropertyMetadataRegisterDto().setName(THEME_PROP_NAME_COLOR_PRIMARY)
                .setDefaultValue("#000000")
                .setType("string"),
            new PropertyMetadataRegisterDto().setName(THEME_PROP_NAME_COLOR_SECONDARY)
                .setDefaultValue("#FFFFFF")
                .setType("string")
        );
    }

    public List<PropertyMetadataRegisterDto> prepareUserPropertiesMetadata() {
        return Arrays.asList(
            new PropertyMetadataRegisterDto().setName(USER_PROP_NAME_LANGUAGE)
                .setDefaultValue("en-US")
                .setType("string"),
            new PropertyMetadataRegisterDto().setName(USER_PROP_NAME_EMAILS_ENABLE)
                .setDefaultValue("true")
                .setType("boolean")
                .setTags(Arrays.asList("i18n",
                    "localizations"))
                .setPossibleValues(Arrays.asList("en-US",
                    "ru-RU",
                    "de-DE"))
        );
    }

    public ConfigMetadataDto expectedDto() {
        ConfigMetadataDto expected = new ConfigMetadataDto().setId(CONFIG_METADATA_TEST_ID);
        List<PropertyMetadataDto> propertyMetadataDtos = new ArrayList<>();
        fillExpectedPropMetaDtos(propertyMetadataDtos,
            prepareUserPropertiesMetadata(),
            USER_PROPERTIES_GROUP);
        fillExpectedPropMetaDtos(propertyMetadataDtos,
            prepareThemePropertiesMetadata(),
            THEME_PROPERTIES_GROUP);
        expected.setPropertyMetadatas(propertyMetadataDtos);
        return expected;
    }

    private void fillExpectedPropMetaDtos(final List<PropertyMetadataDto> expectedList,
                                          final List<PropertyMetadataRegisterDto> registerDtos,
                                          final String group) {
        registerDtos.forEach(persistMetadata -> {
            PropertyMetadataDto expectedPropDto = new PropertyMetadataDto();
            copyProperties(expectedPropDto,
                persistMetadata);
            expectedList.add(expectedPropDto.setGroup(group)
                .setConfigMetadataId(CONFIG_METADATA_TEST_ID));
        });
    }

    private void copyProperties(PropertyMetadataDto expectedPropDto,
                                PropertyMetadataRegisterDto persistMetadata) {
        Arrays.stream(PropertyMetadataRegisterDto.class.getDeclaredFields())
            .forEach(persistField -> {
                    Field expectedField = null;
                    try {
                        expectedField = PropertyMetadataDto.class.getDeclaredField(persistField.getName());
                        persistField.setAccessible(true);
                        Object persistValue = persistField.get(persistMetadata);
                        expectedField.setAccessible(true);
                        expectedField.set(expectedPropDto,
                            persistValue);
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        return;
                    }
                }
            );
    }

}
