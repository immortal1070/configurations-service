package com.immortal.configurations;

import com.immortal.configurations.api.ConfigInstanceResource;
import com.immortal.configurations.api.dto.ConfigInstanceDto;
import com.immortal.configurations.api.dto.ConfigInstancePersistDto;
import com.immortal.configurations.api.dto.PropertyValueDto;
import com.immortal.configurations.api.dto.PropertyValuePersistDto;
import com.immortal.configurations.prepared.ConfigInstancePreparedData;
import com.immortal.configurations.prepared.ConfigInstancePreparedDataResolver;
import com.immortal.configurations.prepared.ConfigMetadataPreparedData;
import com.immortal.configurations.util.LogClientErrorExceptionExtension;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.ws.rs.ClientErrorException;
import java.net.HttpURLConnection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static com.immortal.configurations.prepared.ConfigMetadataPreparedData.TestData.CONFIG_METADATA_TEST_ID;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for ConfigInstanceResource endpoints.
 * <p>
 * Calling real REST service enpoints.
 * <p>
 * ConfigInstancePreparedDataResolver creates
 */
@ExtendWith({LogClientErrorExceptionExtension.class, ConfigInstancePreparedDataResolver.class})
public class ConfigInstanceResourceIT extends ConfigurationsTest {
    private final ConfigInstanceResource resource = restClient.getRestProxy(ConfigInstanceResource.class);

    ConfigInstancePreparedData preparedData = new ConfigInstancePreparedData();

    @Test
    public void testCreateConfigInstance() {
        ConfigInstanceDto created = resource.create(new ConfigInstancePreparedData().persistDto());
        testGetConfigInstance(created);
        validateConfigInstance(preparedData.expectedDto(), created);
    }

    @Test
    public void testGetConfigInstance(final ConfigInstanceDto created) {
        ConfigInstanceDto found = resource.findById(created.getId());
        assertJsonEquals(created, found);
        validateConfigInstance(preparedData.expectedDto(), found);
    }

    @Test
    public void testUpdateOneProperty(final ConfigInstanceDto created) {
        String updatedPropName = ConfigMetadataPreparedData.TestData.USER_PROP_NAME_LANGUAGE;
        String updatedPropValue = "it-IT";
        ConfigInstanceDto updated = resource.partialUpdate(created.getId(), new ConfigInstancePersistDto()
            .setPropertyValues(Collections.singletonList(
                new PropertyValuePersistDto(
                    updatedPropName,
                    updatedPropValue
                )
            )));
        ConfigInstanceDto expected = preparedData.expectedDto();
        expected.getPropertyValues().stream().filter(prop -> prop.getName().equals(updatedPropName))
            .findFirst().orElseGet(Assertions::fail).setValue(updatedPropValue);
        validateConfigInstance(expected, updated);
    }

    @Test
    public void testUpdateWithReplacement(final ConfigInstanceDto created) {
        String updatedPropName = ConfigMetadataPreparedData.TestData.USER_PROP_NAME_LANGUAGE;
        String updatedPropValue = "it-IT";
        ConfigInstanceDto updated = resource.update(created.getId(),
            new ConfigInstancePersistDto()
                .setName(created.getName())
                .setConfigMetadataId(created.getConfigMetadataId())
                .setPropertyValues(Collections.singletonList(
                    new PropertyValuePersistDto(
                        updatedPropName,
                        updatedPropValue
                    )
                )));
        ConfigInstanceDto expected = preparedData.expectedDto();
        expected.setPropertyValues(Collections.singletonList(new PropertyValueDto()
            .setName(updatedPropName).setValue(updatedPropValue)));
        validateConfigInstance(expected, updated);
    }

    @Test
    public void testGetAllConfigInstance(final ConfigInstanceDto created) {
        final List<ConfigInstanceDto> allBeforeCreate = resource.find();
        assertTrue(allBeforeCreate.size() > 0);
        assertTrue(allBeforeCreate.stream().anyMatch(dto -> dto.getId().equals(created.getId())));
        final ConfigInstanceDto
            anotherCreated =
            resource.create(new ConfigInstancePersistDto().setConfigMetadataId(CONFIG_METADATA_TEST_ID)
                .setName("dummy name"));

        final List<ConfigInstanceDto> allAfterCreate = resource.find();
        assertTrue(allAfterCreate.stream().anyMatch(dto -> dto.getId().equals(anotherCreated.getId())));
        assertTrue(allAfterCreate.stream().anyMatch(dto -> dto.getId().equals(created.getId())));
    }

    @Test
    public void testDeleteConfigInstance() {
        final ConfigInstanceDto created = resource.create(preparedData.persistDto());
        resource.delete(created.getId());
        assertNull(resource.findById(created.getId()));
    }

    @Test
    public void testDeleteWrongConfigInstance() {
        ClientErrorException thrown = assertThrows(ClientErrorException.class, () -> resource.delete(
            UUID.randomUUID()
            ),
            "Expected 400 code when wrong id is passed");
        assertEquals(HttpURLConnection.HTTP_BAD_REQUEST, thrown.getResponse().getStatus());
    }

    private void validateConfigInstance(ConfigInstanceDto expected, ConfigInstanceDto actual) {
        assertNotNull(actual.getId());
        assertNotNull(actual.getCreateDate());
        assertFalse(CollectionUtils.isEmpty(expected.getPropertyValues()),
            "property values collection must not be empty!");
        expected.getPropertyValues().forEach(expectedProp -> {
            PropertyValueDto
                foundActualProp =
                actual.getPropertyValues()
                    .stream()
                    .filter(actualProp -> actualProp.getName().equals(
                        expectedProp.getName()
                    ))
                    .findAny()
                    .orElseGet(() -> fail("Property value " + expectedProp.getName() + " is expected, but missing"));

            assertReflectionEquals(expectedProp, foundActualProp, "id", "createDate", "updateDate", "configInstanceId");
            assertEquals(actual.getId(), foundActualProp.getConfigInstanceId());
            assertNotNull(actual.getCreateDate());
        });
    }
}
