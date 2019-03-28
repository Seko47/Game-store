package com.example.projekt.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class IllegalNameValidator implements ConstraintValidator<IllegalName, String>
{
    public IllegalName constraint;

    public void initialize(IllegalName constraint) {
        this.constraint = constraint;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {

        if (value != null) {
            return Arrays.stream(constraint.values()).noneMatch(x -> x.toLowerCase().equals(value.toLowerCase()));
        }

        return true;
    }
}
