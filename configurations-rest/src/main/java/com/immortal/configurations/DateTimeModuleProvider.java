package com.immortal.configurations;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.immortal.configurations.util.ObjectMapperFactory;

@Provider
public class DateTimeModuleProvider implements ContextResolver<ObjectMapper>
{
    private final ObjectMapper objectMapper;

    public DateTimeModuleProvider()
    {
        objectMapper = ObjectMapperFactory.createObjectMapper();
    }

    @Override
    public ObjectMapper getContext(Class<?> args)
    {
        return objectMapper;
    }
}