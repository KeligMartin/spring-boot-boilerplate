package fr.kelig.springboot.boilerplate.infrastructure.common.annotation;

import fr.kelig.springboot.boilerplate.infrastructure.common.validator.StrongPasswordConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = StrongPasswordConstraintValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface StrongPassword {
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String message() default "Your password isn't strong enough";
}
