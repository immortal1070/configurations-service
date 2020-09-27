package com.immortal.configurations.prepared;

import com.immortal.configurations.api.ConfigMetadataResource;
import com.immortal.configurations.api.dto.ConfigMetadataDto;
import com.immortal.configurations.api.dto.ConfigMetadataPersistDto;
import com.immortal.configurations.api.dto.PropertyMetadataGroupDto;
import com.immortal.configurations.api.dto.PropertyMetadataRegisterDto;
import com.immortal.configurations.util.TestRestClient;
import org.jboss.logging.Logger;

import java.util.Arrays;
import java.util.UUID;

/**
 * If parameter "ConfigMetadataDto" is used in tests, then this resolver will create the entity, will
 * inject it into the parameter and will delete the entity after test is executed.
 */
public class ConfigMetadataPreparedDataResolver extends PreparedTestDataResolver<ConfigMetadataDto> {

    public static final String USER_PROPERTIES_GROUP = "userProperties";
    public static final String THEME_PROPERTIES_GROUP = "themeProperties";
    private final static Logger logger = Logger.getLogger(ConfigMetadataPreparedDataResolver.class);
    private final static ConfigMetadataResource resource = new TestRestClient()
        .getRestProxy(ConfigMetadataResource.class);

    public static ConfigMetadataPersistDto prepareConfigMetadata() {
        ConfigMetadataPersistDto configMetadataPersistDto = new ConfigMetadataPersistDto(UUID.randomUUID().toString());
        PropertyMetadataGroupDto userProperiesGroup = new PropertyMetadataGroupDto(USER_PROPERTIES_GROUP);
        userProperiesGroup.setPropertiesMetadatas(Arrays.asList(
            new PropertyMetadataRegisterDto().setName("user.default.language").setDefaultValue("en-US")
                .setType("string"),
            new PropertyMetadataRegisterDto().setName("user.default.emails.enable").setDefaultValue("true")
                .setType("boolean")
                .setTags(Arrays.asList("i18n", "localizations"))
                .setPossibleValues(Arrays.asList("en-US", "ru-RU", "de-DE"))
            )
        );
        PropertyMetadataGroupDto themeProperiesGroup = new PropertyMetadataGroupDto(THEME_PROPERTIES_GROUP)
            .setPropertiesMetadatas(Arrays.asList(
                new PropertyMetadataRegisterDto().setName("theme.primary.color").setDefaultValue("#000000")
                    .setType("string"),
                new PropertyMetadataRegisterDto().setName("theme.secondary.color").setDefaultValue("#FFFFFF")
                    .setType("string")));

        configMetadataPersistDto.setPropertyMetadataGroups(Arrays.asList(userProperiesGroup, themeProperiesGroup));

        return configMetadataPersistDto;
    }

    @Override
    public ConfigMetadataDto create() {
        return resource.register(prepareConfigMetadata());
    }

    @Override
    public void cleanup() {
//        resource.find().forEach(configMetadataDto -> resource.delete(configMetadataDto.getId()));
    }

    @Override
    public Class<ConfigMetadataDto> getClazz() {
        return ConfigMetadataDto.class;
    }

}
