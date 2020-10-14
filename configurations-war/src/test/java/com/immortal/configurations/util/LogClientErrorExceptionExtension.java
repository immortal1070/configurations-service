package com.immortal.configurations.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

import javax.ws.rs.ClientErrorException;
import java.io.IOException;
import java.util.logging.Logger;

public class LogClientErrorExceptionExtension implements TestExecutionExceptionHandler {
    public static final Logger logger = Logger.getLogger(LogClientErrorExceptionExtension.class.getName());

    private static final ObjectMapper mapper = ObjectMapperFactory.createObjectMapper();

    @Override
    public void handleTestExecutionException(final ExtensionContext extensionContext, final Throwable throwable)
        throws Throwable {
        if (ClientErrorException.class.isAssignableFrom(throwable.getClass())) {
            final String entityBody = ((ClientErrorException) throwable).getResponse().readEntity(String.class);
            final String error;
            try {
                error = mapper.readValue(entityBody, String.class);
                logger.info(error);
            } catch (IOException ex) {
                logger.info(entityBody);
            }
        }
        throw throwable;
    }
}
