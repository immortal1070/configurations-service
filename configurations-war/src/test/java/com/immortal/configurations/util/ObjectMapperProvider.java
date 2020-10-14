package com.immortal.configurations.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Provider
public class ObjectMapperProvider implements ContextResolver<ObjectMapper> {
    private final ObjectMapper objectMapper;

    public ObjectMapperProvider() {
        objectMapper = ObjectMapperFactory.createObjectMapper();
    }

    @Override
    public ObjectMapper getContext(Class<?> args) {
        return objectMapper;
    }
}
