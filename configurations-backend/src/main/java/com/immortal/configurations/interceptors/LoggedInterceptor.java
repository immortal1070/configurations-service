package com.immortal.configurations.interceptors;

import java.util.Arrays;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.ws.rs.ClientErrorException;

import org.jboss.logging.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.immortal.configurations.util.ObjectMapperFactory;

/**
 * Logging method calls with JSON parse of the parameters.
 * controlled by level param.
 * <p>
 * Logs calls of errors which got an exception to INFO.
 */
@Interceptor
@Logged(logClientErrorException = false)
@Priority(Interceptor.Priority.LIBRARY_BEFORE)
public class LoggedInterceptor
{
    private static final ObjectMapper mapper = ObjectMapperFactory.createObjectMapper();
    private final static String METHOD_CALLED = "Method Called: ";

    @AroundInvoke
    public Object log(InvocationContext context) throws Exception
    {
        Logger logger = Logger.getLogger(context.getTarget().getClass());

        Logged loggedAnnotation = getLoggerAnnotation(context);

        boolean methodWasLogged = logMethod(loggedAnnotation, logger, context);

        try
        {
            return context.proceed();
        }
        catch (Exception e)
        {
            if (!methodWasLogged && logger.isInfoEnabled() && (loggedAnnotation == null || loggedAnnotation
                    .logClientErrorException() || !ClientErrorException.class.isAssignableFrom(e.getClass())))
            {
                logger.info(buildMethodLog(context, "Exception " + e.getClass().getName() + " was caused by method: "));
            }

            throw e;
        }
    }

    private boolean logMethod(final Logged loggedAnnotation, final Logger logger, final InvocationContext context)
    {
        boolean methodWasLogged = false;
        LoggedLevel infoLevel = loggedAnnotation != null ? loggedAnnotation.level() : LoggedLevel.DEBUG;
        switch (infoLevel)
        {
            case INFO:
            {
                if (logger.isInfoEnabled())
                {
                    logger.info(buildMethodLog(context, METHOD_CALLED));
                    methodWasLogged = true;
                }
            }
            break;
            case DEBUG:
            default:
            {
                if (logger.isDebugEnabled())
                {
                    logger.debug(buildMethodLog(context, METHOD_CALLED));
                    methodWasLogged = true;
                }
            }
            break;
        }
        return methodWasLogged;
    }

    private Logged getLoggerAnnotation(final InvocationContext context)
    {
        Logged loggedAnnotation = context.getMethod().getAnnotation(Logged.class);

        if (loggedAnnotation == null)
        {
            loggedAnnotation = context.getTarget().getClass().getSuperclass().getAnnotation(Logged.class);
        }
        if (loggedAnnotation == null)
        {
            loggedAnnotation = context.getTarget().getClass().getAnnotation(Logged.class);
        }
        return loggedAnnotation;
    }

    private String buildMethodLog(final InvocationContext context, final String prefix)
    {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(prefix + " ");
        stringBuilder.append(context.getMethod().getName());
        stringBuilder.append("\n");
        stringBuilder.append("Parameters: ");
        stringBuilder.append(paramsToString(context.getParameters()));
        return stringBuilder.toString();
    }

    private String paramsToString(final Object[] parameters)
    {
        if (parameters != null && parameters.length != 0)
        {
            StringBuilder stringBuilder = new StringBuilder();
            Arrays.asList(parameters).forEach(param -> {
                try
                {
                    stringBuilder.append(mapper.writeValueAsString(param));
                }
                catch (JsonProcessingException e)
                {
                    stringBuilder.append(param);
                }
                stringBuilder.append("\n");
            });
            return stringBuilder.toString();
        }
        return "";
    }
}