package com.immortal.configurations;

import com.immortal.configurations.api.ConfigMetadataResource;
import com.immortal.configurations.api.dto.ConfigMetadataDto;
import com.immortal.configurations.api.dto.ConfigMetadataPersistDto;
import com.immortal.configurations.api.dto.PropertyMetadataDto;
import com.immortal.configurations.prepared.ConfigMetadataPreparedData;
import com.immortal.configurations.prepared.ConfigMetadataPreparedDataResolver;
import com.immortal.configurations.util.LogClientErrorExceptionExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.ws.rs.ClientErrorException;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for ConfigMetadataResource endpoints.
 *
 * Calling real REST service enpoints.
 *
 * ConfigMetadataPreparedDataResolver creates
 */
@ExtendWith({LogClientErrorExceptionExtension.class, ConfigMetadataPreparedDataResolver.class})
public class ConfigMetadataResourceIT extends ConfigurationsTest {
    private final ConfigMetadataResource resource = restClient.getRestProxy(ConfigMetadataResource.class);

    public static final String WRONG_ID = "dummy";

    ConfigMetadataPreparedData preparedData = new ConfigMetadataPreparedData();

    @Test
    public void testRegisterConfigMetadata() {
        ConfigMetadataDto registered = resource.register(new ConfigMetadataPreparedData().persistDto());
        testGetConfigMetadata(registered);
        validateConfigMetadata(preparedData.expectedDto(), registered);
    }

    @Test
    public void testGetConfigMetadata(final ConfigMetadataDto created) {
        ConfigMetadataDto found = resource.findById(created.getId());
        assertJsonEquals(created, found);
        validateConfigMetadata(preparedData.expectedDto(), found);
    }

    @Test
    public void testGetAllConfigMetadata(final ConfigMetadataDto created) {
        final List<ConfigMetadataDto> allBeforeCreate = resource.find();
        assertTrue(allBeforeCreate.size() > 0);
        assertTrue(allBeforeCreate.stream().anyMatch(dto -> dto.getId().equals(created.getId())));
        final ConfigMetadataDto anotherCreated = resource.register(new ConfigMetadataPersistDto(UUID.randomUUID().toString()));

        final List<ConfigMetadataDto> allAfterCreate = resource.find();
        assertTrue(allAfterCreate.stream().anyMatch(dto -> dto.getId().equals(anotherCreated.getId())));
        assertTrue(allAfterCreate.stream().anyMatch(dto -> dto.getId().equals(created.getId())));
    }

    @Test
    public void testDeleteConfigMetadata() {
        final ConfigMetadataDto created = resource.register(preparedData.persistDto());
        resource.delete(created.getId());
        assertNull(resource.findById(created.getId()));
    }

    @Test
    public void testDeleteWrongConfigMetadata() {
        ClientErrorException thrown = assertThrows(ClientErrorException.class, () -> resource.delete(WRONG_ID),
            "Expected 400 code when wrong id is passed");
        assertEquals(HttpURLConnection.HTTP_BAD_REQUEST, thrown.getResponse().getStatus());
    }

    private void validateConfigMetadata(ConfigMetadataDto expected, ConfigMetadataDto actual) {
        assertEquals(expected.getId(), actual.getId());
        assertNotNull(actual.getCreateDate());
        expected.getPropertyMetadatas().forEach(expectedProp -> {
            PropertyMetadataDto foundActualProp = actual.getPropertyMetadatas().stream().filter(actualProp -> actualProp.getName().equals(
                expectedProp.getName()
            )).findAny().orElseThrow(() -> new RuntimeException("Property " + expectedProp.getName() + " is expected, but missing"));

            assertReflectionEquals(expectedProp, foundActualProp, "id", "createDate", "updateDate",
                "tags", "possibleValues");

            assertCollectionEquals(expectedProp.getPossibleValues(), foundActualProp.getPossibleValues());
            assertCollectionEquals(expectedProp.getTags(), foundActualProp.getTags());
        });
    }
}
