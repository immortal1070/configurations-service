package com.immortal.configurations.util;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.ext.Provider;
import java.util.logging.Logger;

@Provider
public class RequestLogInterceptor implements ClientRequestFilter {
    public static final Logger logger = Logger.getLogger(RequestLogInterceptor.class.getName());

    @Override
    public void filter(final ClientRequestContext requestContext) {
        logger.info(requestContext.getMethod() + " request, " + requestContext.getUri());
    }
}
