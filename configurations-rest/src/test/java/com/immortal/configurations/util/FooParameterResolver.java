//package com.immortal.configurations.util;
//
//import java.util.UUID;
//
//import org.junit.jupiter.api.extension.AfterAllCallback;
//import org.junit.jupiter.api.extension.AfterEachCallback;
//import org.junit.jupiter.api.extension.BeforeAllCallback;
//import org.junit.jupiter.api.extension.BeforeEachCallback;
//import org.junit.jupiter.api.extension.ExtensionContext;
//import org.junit.jupiter.api.extension.ParameterContext;
//import org.junit.jupiter.api.extension.ParameterResolutionException;
//import org.junit.jupiter.api.extension.ParameterResolver;
//
//import com.immortal.configurations.api.ConfigMetadataResource;
//import com.immortal.configurations.api.dto.ConfigMetadataDto;
//
//public class FooParameterResolver implements ParameterResolver,
//        BeforeAllCallback, AfterAllCallback, BeforeEachCallback,
//        AfterEachCallback
//{
//    @Override
//    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
//            throws ParameterResolutionException
//    {
//        return parameterContext.getParameter().getType() == ConfigMetadataDto.class;
//    }
//
//    @Override
//    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
//            throws ParameterResolutionException
//    {
//        return new TestRestClient().getRestProxy(ConfigMetadataResource.class).create(UUID.randomUUID().toString());
//    }
//}