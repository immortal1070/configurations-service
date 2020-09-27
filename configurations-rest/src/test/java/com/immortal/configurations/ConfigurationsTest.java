package com.immortal.configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.immortal.configurations.util.ObjectMapperFactory;
import com.immortal.configurations.util.TestRestClient;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConfigurationsTest {
    protected static final ObjectMapper mapper = ObjectMapperFactory.createObjectMapper();

    protected static TestRestClient restClient = new TestRestClient();

    public void assertJsonEquals(final Object expected, final Object actual) {
//        assertTrue(EqualsBuilder.reflectionEquals(expected, actual));//, UPDATE_DATE_FIELD
        assertEquals(mapper.valueToTree(expected), mapper.valueToTree(actual));
    }

    public String randomString() {
        return UUID.randomUUID().toString();
    }
}
