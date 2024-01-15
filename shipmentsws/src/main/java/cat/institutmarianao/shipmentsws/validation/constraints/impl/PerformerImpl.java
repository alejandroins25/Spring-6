package cat.institutmarianao.shipmentsws.validation.constraints.impl;

import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import cat.institutmarianao.shipmentsws.model.Action.Type;
import cat.institutmarianao.shipmentsws.model.Assignment;
import cat.institutmarianao.shipmentsws.model.Delivery;
import cat.institutmarianao.shipmentsws.model.Reception;
import cat.institutmarianao.shipmentsws.model.User;
import cat.institutmarianao.shipmentsws.model.User.Role;
import cat.institutmarianao.shipmentsws.model.dto.AssignmentDto;
import cat.institutmarianao.shipmentsws.model.dto.DeliveryDto;
import cat.institutmarianao.shipmentsws.model.dto.ReceptionDto;
import cat.institutmarianao.shipmentsws.services.UserService;
import cat.institutmarianao.shipmentsws.validation.constraints.Performer;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PerformerImpl implements ConstraintValidator<Performer, Object> {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private UserService userService;

	private String property;
	private Role[] allowedRoles;

	@Override
	public void initialize(Performer constraintAnnotation) {
		property = constraintAnnotation.property();
		allowedRoles = constraintAnnotation.value();
	}

	@Override
	public boolean isValid(Object object, ConstraintValidatorContext context) {
		if (messageSource == null || userService == null) {
			return true; // We have no context, so is not under @Valid annotation
		}

		Object performerValue = new BeanWrapperImpl(object).getPropertyValue(property);

		String username;
		if (performerValue instanceof String usernameProperty) {
			username = usernameProperty;
		} else if (performerValue instanceof User user) {
			username = user.getUsername();
		} else {
			addError(context, "error.Performer.property.is.not.instance.of.user.or.string", property);
			return false;
		}

		if (username == null) {
			return true; // Depends on @NotNull annotation. If allows nulls, performer is OK being null
		}

		Type type;
		if (object instanceof Reception || object instanceof ReceptionDto) {
			type = Type.RECEPTION;
		} else if (object instanceof Assignment || object instanceof AssignmentDto) {
			type = Type.ASSIGNMENT;
		} else if (object instanceof Delivery || object instanceof DeliveryDto) {
			type = Type.DELIVERY;
		} else {
			addError(context, "error.Performer.annotation.should.be.attached.to.Action.or.ActionDto.subtypes",
					property);
			return false;
		}

		User performer = userService.getByUsername(username);

		return validate(context, type, performer, allowedRoles);
	}

	private boolean validate(ConstraintValidatorContext context, Type type, User performer, Role[] roles) {
		if (ArrayUtils.contains(roles, (performer.getRole()))) {
			return true;
		}
		addError(context, "error.Performer.role.invalid.for.action", type, Arrays.toString(roles), performer.getRole());
		return false;
	}

	private void addError(ConstraintValidatorContext context, String code, Object... args) {
		context.buildConstraintViolationWithTemplate(
				messageSource.getMessage(code, args, LocaleContextHolder.getLocale())).addPropertyNode(property)
				.addConstraintViolation();
	}
}
