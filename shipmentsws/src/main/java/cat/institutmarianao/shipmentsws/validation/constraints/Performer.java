package cat.institutmarianao.shipmentsws.validation.constraints;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import cat.institutmarianao.shipmentsws.model.User.Role;
import cat.institutmarianao.shipmentsws.validation.constraints.impl.PerformerImpl;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = PerformerImpl.class)
@Target({ TYPE, TYPE_USE })
@Retention(RUNTIME)
public @interface Performer {
	String message() default "{error.Performer.is.not.valid}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String property() default "performer";

	Role[] value();
}
