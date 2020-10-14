//package com.immortal.configurations.resource;
//
//import java.util.UUID;
//
//import com.immortal.configurations.api.ConfigMetadataResource;
//import com.immortal.configurations.api.dto.ConfigMetadataDto;
//import com.immortal.configurations.util.TestRestClient;
//
//public class ConfigMetadataClient
//{
//    private final static ConfigMetadataResource resource = new TestRestClient()
//            .getRestProxy(ConfigMetadataResource.class);
//
//    public ConfigMetadataDto create()
//    {
//        return resource.create(UUID.randomUUID().toString());
//    }
//
//    public void delete(final ConfigMetadataDto created)
//    {
//        resource.delete(created.getId());
//    }
//}