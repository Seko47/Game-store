package com.example.projekt.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint (validatedBy = IllegalNameValidator.class)
public @interface IllegalName {
    String message () default "Zabronione nazwy {names}";
    Class<?>[] groups () default {};
    Class<? extends Payload >[] payload () default {};
    String[] values () default {};
}
