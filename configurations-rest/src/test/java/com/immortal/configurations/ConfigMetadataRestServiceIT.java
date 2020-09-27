package com.immortal.configurations;

import com.immortal.configurations.api.ConfigMetadataResource;
import com.immortal.configurations.api.dto.ConfigMetadataDto;
import com.immortal.configurations.api.dto.ConfigMetadataPersistDto;
import com.immortal.configurations.prepared.ConfigMetadataPreparedDataResolver;
import com.immortal.configurations.util.LogClientErrorExceptionExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.ws.rs.ClientErrorException;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.UUID;

import static com.immortal.configurations.prepared.ConfigMetadataPreparedDataResolver.prepareConfigMetadata;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({LogClientErrorExceptionExtension.class, ConfigMetadataPreparedDataResolver.class})
public class ConfigMetadataRestServiceIT extends ConfigurationsTest {
    public static final String WRONG_ID = "dummy";
    private final ConfigMetadataResource resource = restClient.getRestProxy(ConfigMetadataResource.class);

    @Test
    public void testCreateAndGetConfigMetadata(final ConfigMetadataDto registered) {
        ConfigMetadataDto existing = resource.findById(registered.getId());
//        validateResult(existing);
        assertJsonEquals(registered, resource.findById(registered.getId()));
    }

    @Test
    public void testRegisterConfigMetadata(final ConfigMetadataDto registered) {
        ConfigMetadataDto existing = resource.findById(registered.getId());
        assertJsonEquals(registered, existing);
        assertJsonEquals(ConfigMetadataPreparedDataResolver.prepareConfigMetadata(), existing);
    }

    /**
     * TODO note! should we rewritten in case of parallel test execution
     */
    @Test
    public void testGetAllConfigMetadata(final ConfigMetadataDto registered) {
        final List<ConfigMetadataDto> allBeforeCreate = resource.find();
        assertTrue(allBeforeCreate.size() > 0);
        final ConfigMetadataDto anotherregistered = resource.register(new ConfigMetadataPersistDto(UUID.randomUUID().toString()));

        final List<ConfigMetadataDto> allAfterCreate = resource.find();
        assertTrue(allAfterCreate.stream().anyMatch(dto -> dto.getId().equals(anotherregistered.getId())));
        resource.delete(anotherregistered.getId());
    }

    @Test
    public void testDeleteConfigMetadata() {
        final ConfigMetadataDto registered = resource.register(prepareConfigMetadata());
        resource.delete(registered.getId());
        assertNull(resource.findById(registered.getId()));
    }

    @Test
    public void testDeleteWrongConfigMetadata() {
        ClientErrorException thrown = assertThrows(ClientErrorException.class, () -> resource.delete(WRONG_ID),
            "Expected 400 code when wrong id is passed");
        assertEquals(HttpURLConnection.HTTP_BAD_REQUEST, thrown.getResponse().getStatus());
    }
}
