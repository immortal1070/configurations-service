package com.immortal.configurations.util;

import com.immortal.configurations.DateTimeModuleProvider;
import com.immortal.configurations.api.constants.ConfigurationsConstants;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.ws.rs.core.UriBuilder;

public class TestRestClient {
    public <T> T getRestProxy(final Class<T> resourceClazz) {
        final String path = getHost() + ":" + getPort() + ConfigurationsConstants.Rest.RootContexts.CONFIGS;

        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target(UriBuilder.fromPath(path));
        target.register(new DateTimeModuleProvider());
        target.register(ResponseLogInterceptor.class);
        target.register(RequestLogInterceptor.class);
        return target.proxy(resourceClazz);
    }

    private String getHost() {
        return System.getProperty("immortal.host", "http://127.0.0.1");
    }

    private String getPort() {
        return System.getProperty("immortal.port", "8080");
    }

}
