package com.immortal.configurations;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.immortal.configurations.api.constants.ConfigurationsConstants;
import com.immortal.configurations.rest.ConfigMetadataController;

@ApplicationPath(ConfigurationsConstants.Rest.RootContexts.CONFIGS)
public class ConfigurationsRestApplication extends Application
{
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(ConfigMetadataController.class);
        classes.add(DateTimeModuleProvider.class);
        return classes;
    }
}
