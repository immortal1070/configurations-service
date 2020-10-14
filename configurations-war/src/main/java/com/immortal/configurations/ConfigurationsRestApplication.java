package com.immortal.configurations;

import com.immortal.configurations.api.constants.ConfigurationsConstants;
import com.immortal.configurations.rest.ConfigInstanceController;
import com.immortal.configurations.rest.ConfigMetadataController;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath(ConfigurationsConstants.Rest.RootContexts.CONFIGS)
public class ConfigurationsRestApplication extends Application {
//    public Set<Class<?>> getClasses() {
//        Set<Class<?>> classes = new HashSet<>();
//        classes.add(ConfigMetadataController.class);
//        classes.add(ConfigInstanceController.class);
//        classes.add(DateTimeModuleProvider.class);
//        return classes;
//    }
}
