package com.immortal.configurations.util;

import java.util.logging.Logger;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class ResponseLogInterceptor implements ClientResponseFilter
{
    public static final Logger logger = Logger.getLogger(ResponseLogInterceptor.class.getName());

    @Override
    public void filter(final ClientRequestContext requestContext, final ClientResponseContext responseContext)
    {
        logger.info(requestContext.getMethod() + " request, " + requestContext.getUri() + ". Response code: "
                + responseContext.getStatus());
    }
}
