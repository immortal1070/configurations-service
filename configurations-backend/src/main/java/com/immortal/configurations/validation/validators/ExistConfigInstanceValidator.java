package com.immortal.configurations.validation.validators;

import com.immortal.configurations.dao.ConfigInstanceDao;
import com.immortal.configurations.validation.ExistConfigInstance;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.ws.rs.core.Response;
import java.util.UUID;

public class ExistConfigInstanceValidator implements ConstraintValidator<ExistConfigInstance, UUID> {
    @Inject
    private ConfigInstanceDao dao;

    private String defaultMessage;

    @Override
    public void initialize(final ExistConfigInstance constraintAnnotation) {
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
