package com.immortal.configurations.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;

/**
 * Available at
 * http://localhost:9990/health/live
 */
@Liveness
@ApplicationScoped
public class ServiceLiveHealthCheck implements HealthCheck {
    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.named(ServiceLiveHealthCheck.class.getSimpleName()).withData("live", true).up().build();
    }
}
