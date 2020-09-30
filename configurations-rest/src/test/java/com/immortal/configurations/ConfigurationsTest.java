package com.immortal.configurations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.immortal.configurations.util.ObjectMapperFactory;
import com.immortal.configurations.util.TestRestClient;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ConfigurationsTest {
    protected static final ObjectMapper mapper = ObjectMapperFactory.createObjectMapper();

    protected static TestRestClient restClient = new TestRestClient();

    public void assertJsonEquals(final Object expected, final Object actual) {
        assertEquals(mapper.valueToTree(expected), mapper.valueToTree(actual));
    }

    /**
     * Collections input could be both null and empty collection, but result always returns empty collection.
     * This method tests this behaviour.
     * <p>
     * For not empty input assertIterableEquals is applied.
     */
    protected void assertCollectionEquals(final List<?> expected, final List<?> actual) {
        if (CollectionUtils.isEmpty(expected)) {
            assertEquals(0, actual.size());
        } else {
            assertIterableEquals(expected, actual);
        }
    }

    /**
     * Compares objects with reflectionEquals.
     * Asserts the readable message with expected and actual objects
     */
    protected void assertReflectionEquals(final Object expected, final Object actual, final String... excludeFields) {
        assertTrue(EqualsBuilder.reflectionEquals(expected, actual, excludeFields),
            () -> {
                try {
                    return "Reflection equals failed! expected=" + mapper.writeValueAsString(expected)
                        + " actual=" + mapper.writeValueAsString(actual);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            });
    }
}
