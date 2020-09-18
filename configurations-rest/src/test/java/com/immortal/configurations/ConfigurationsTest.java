package com.immortal.configurations;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.immortal.configurations.util.ObjectMapperFactory;
import com.immortal.configurations.util.TestRestClient;

public class ConfigurationsTest
{
    protected static final ObjectMapper mapper = ObjectMapperFactory.createObjectMapper();

    protected static TestRestClient restClient = new TestRestClient();

    public void assertJsonEquals(final Object expected, final Object actual)
    {
        assertEquals(mapper.valueToTree(expected), mapper.valueToTree(actual));
    }

    public String randomString()
    {
        return UUID.randomUUID().toString();
    }
}
