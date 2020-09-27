package com.immortal.configurations.validation.validators;

import com.immortal.configurations.dao.PropertyMetadataDao;
import com.immortal.configurations.validation.ExistPropertyMetadata;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.ws.rs.core.Response;
import java.util.UUID;

public class ExistPropertyMetadataValidator implements ConstraintValidator<ExistPropertyMetadata, UUID> {
    @Inject
    private PropertyMetadataDao dao;

    private String defaultMessage;

    @Override
    public void initialize(final ExistPropertyMetadata constraintAnnotation) {
        defaultMessage = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(final UUID id, final ConstraintValidatorContext context) {
        if (dao.exists(id)) {
            return true;
        }

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(defaultMessage).addPropertyNode(Response.Status.NOT_FOUND.name())
            .addConstraintViolation();

        return false;
    }
}
