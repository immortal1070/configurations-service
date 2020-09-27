package com.immortal.configurations.validation.validators;

import com.immortal.configurations.dao.ConfigMetadataDao;
import com.immortal.configurations.validation.ExistConfigMetadata;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.ws.rs.core.Response;

public class ExistConfigMetadataValidator implements ConstraintValidator<ExistConfigMetadata, String> {
    @Inject
    private ConfigMetadataDao dao;

    private String defaultMessage;

    @Override
    public void initialize(final ExistConfigMetadata constraintAnnotation) {
        defaultMessage = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(final String id, final ConstraintValidatorContext context) {
        if (dao.exists(id)) {
            return true;
        }

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(defaultMessage).addPropertyNode(Response.Status.NOT_FOUND.name())
            .addConstraintViolation();

        return false;
    }
}
