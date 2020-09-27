package com.immortal.configurations.validation;

import com.immortal.configurations.api.constants.ConfigurationsMessages;
import com.immortal.configurations.validation.validators.ExistConfigMetadataValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * The system id should exist.
 * Accepts any type.
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, PARAMETER, CONSTRUCTOR})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {ExistConfigMetadataValidator.class})
public @interface ExistConfigMetadata {
    String message() default ConfigurationsMessages.ID_NOT_FOUND;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
