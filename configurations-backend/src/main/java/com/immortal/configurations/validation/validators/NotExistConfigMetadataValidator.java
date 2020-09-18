package com.immortal.configurations.validation.validators;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.ws.rs.core.Response;

import com.immortal.configurations.dao.ConfigMetadataDao;
import com.immortal.configurations.validation.NotExistConfigMetadata;

public class NotExistConfigMetadataValidator implements ConstraintValidator<NotExistConfigMetadata, String>
{
    @Inject
    private ConfigMetadataDao dao;

    private String defaultMessage;

    @Override
    public void initialize(final NotExistConfigMetadata constraintAnnotation)
    {
        defaultMessage = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(final String id, final ConstraintValidatorContext context)
    {
        if (!dao.exists(id))
        {
            return true;
        }

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(defaultMessage).addPropertyNode(Response.Status.NOT_FOUND.name())
                .addConstraintViolation();

        return false;
    }
}