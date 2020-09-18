package com.immortal.configurations.util;

import java.util.logging.Logger;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class RequestLogInterceptor implements ClientRequestFilter
{
    public static final Logger logger = Logger.getLogger(RequestLogInterceptor.class.getName());

    @Override
    public void filter(final ClientRequestContext requestContext)
    {
        logger.info(requestContext.getMethod() + " request, " + requestContext.getUri());
    }
}
