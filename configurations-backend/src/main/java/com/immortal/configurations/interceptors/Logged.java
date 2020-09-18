package com.immortal.configurations.interceptors;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.interceptor.InterceptorBinding;


/**
 * Logging method calls with JSON parse of the parameters.
 * controlled by level param.
 * <p>
 * Logs calls of errors which got an exception to INFO.
 */
@InterceptorBinding
@Target({ METHOD, TYPE })
@Retention(RUNTIME)
public @interface Logged
{
    boolean logClientErrorException() default true;

    LoggedLevel level() default LoggedLevel.DEBUG;
}