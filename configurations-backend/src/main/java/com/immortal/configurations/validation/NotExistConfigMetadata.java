package com.immortal.configurations.validation;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.immortal.configurations.api.constants.ConfigurationsMessages;
import com.immortal.configurations.validation.validators.ExistConfigMetadataValidator;
import com.immortal.configurations.validation.validators.NotExistConfigMetadataValidator;

/**
 * The system id should exist.
 * Accepts any type.
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, PARAMETER, CONSTRUCTOR })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = { NotExistConfigMetadataValidator.class })
public @interface NotExistConfigMetadata
{
    String message() default ConfigurationsMessages.ALREADY_EXISTS;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}