package tech.lab365.labmedical.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.Pattern;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {})
@Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$")
@ReportAsSingleViolation
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Cpf {
    String message() default "Invalid CPF. Send the CPF in the format: 000.000.000-00";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

