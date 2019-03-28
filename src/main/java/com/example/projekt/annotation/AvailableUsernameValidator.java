package com.example.projekt.annotation;

import com.example.projekt.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AvailableUsernameValidator implements ConstraintValidator< AvailableUsername, String >
{
    private AvailableUsername availableUsername;

    @Autowired
    private UserService userService;

    @Override
    public void initialize ( AvailableUsername constraintAnnotation )
    {
        this.availableUsername = constraintAnnotation;
    }

    @Override
    public boolean isValid ( String value, ConstraintValidatorContext context )
    {
        return this.userService == null || this.userService.isLoginAvailable ( value );
    }
}
