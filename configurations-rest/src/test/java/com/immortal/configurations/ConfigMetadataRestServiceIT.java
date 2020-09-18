package com.immortal.configurations;

import static org.junit.jupiter.api.Assertions.*;

import java.net.HttpURLConnection;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.ClientErrorException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.immortal.configurations.api.ConfigMetadataResource;
import com.immortal.configurations.api.dto.ConfigMetadataDto;
import com.immortal.configurations.api.dto.ConfigMetadataPersistDto;
import com.immortal.configurations.prepared.ConfigMetadataPreparedDataResolver;
import com.immortal.configurations.util.LogClientErrorExceptionExtension;

@ExtendWith({ LogClientErrorExceptionExtension.class, ConfigMetadataPreparedDataResolver.class })
public class ConfigMetadataRestServiceIT extends ConfigurationsTest
{
    private final ConfigMetadataResource resource = restClient.getRestProxy(ConfigMetadataResource.class);

    public static final String WRONG_ID = "dummy";

    @Test
    public void testCreateAndGetConfigMetadata(final ConfigMetadataDto created)
    {
        assertJsonEquals(created, resource.findById(created.getId()));
    }

    @Test
    public void testRegisterConfigMetadata(final ConfigMetadataDto created)
    {
        assertJsonEquals(created, resource.findById(created.getId()));
    }

    /**
     * TODO note! should we rewritten in case of parallel test execution
     */
    @Test
    public void testGetAllConfigMetadata(final ConfigMetadataDto created)
    {
        final List<ConfigMetadataDto> allBeforeCreate = resource.find();
        assertTrue(allBeforeCreate.size() > 0);
        final ConfigMetadataDto anotherCreated = resource.register(new ConfigMetadataPersistDto(UUID.randomUUID().toString()));

        final List<ConfigMetadataDto> allAfterCreate = resource.find();
        assertTrue(allAfterCreate.stream().anyMatch(dto -> dto.getId().equals(anotherCreated.getId())));
        resource.delete(anotherCreated.getId());
    }

    @Test
    public void testDeleteConfigMetadata(final ConfigMetadataDto created)
    {
        resource.delete(created.getId());
        assertNull(resource.findById(created.getId()));
    }

    @Test
    public void testDeleteWrongConfigMetadata()
    {
        ClientErrorException thrown = assertThrows(ClientErrorException.class, () -> resource.delete(WRONG_ID),
                "Expected 400 code when wrong id is passed");
        assertEquals(HttpURLConnection.HTTP_BAD_REQUEST, thrown.getResponse().getStatus());
    }
}
