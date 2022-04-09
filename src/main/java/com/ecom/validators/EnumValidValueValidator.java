package com.ecom.validators;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.ecom.types.Roles;

public class EnumValidValueValidator implements ConstraintValidator<ValidRoles, Roles>{

    private Roles[] subset;

    @Override
    public void initialize(ValidRoles constraint) {
        this.subset = constraint.anyOf();
    }

    @Override
    public boolean isValid(Roles value, ConstraintValidatorContext context) {
        return value == null || Arrays.asList(subset).contains(value);
    }

}
