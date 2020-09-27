package com.immortal.configurations.validation.validators;

import com.immortal.configurations.dao.PropertyValueDao;
import com.immortal.configurations.validation.ExistPropertyValue;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.ws.rs.core.Response;
import java.util.UUID;

public class ExistPropertyValueValidator implements ConstraintValidator<ExistPropertyValue, UUID> {
    @Inject
    private PropertyValueDao dao;

    private String defaultMessage;

    @Override
    public void initialize(final ExistPropertyValue constraintAnnotation) {
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
