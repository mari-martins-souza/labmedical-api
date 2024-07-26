package tech.lab365.labmedical.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.Pattern;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {})
@Pattern(regexp = "^\\(\\d{2}\\)\\d{5}-\\d{4}$")
@ReportAsSingleViolation
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumber {
    String message() default "Invalid phone number format. Send the phone in the format: (99)99999-9999";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

