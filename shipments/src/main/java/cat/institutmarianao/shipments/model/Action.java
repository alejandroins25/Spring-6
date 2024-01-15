package cat.institutmarianao.shipments.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import cat.institutmarianao.shipments.ShipmentsApplication;
import cat.institutmarianao.shipments.validation.groups.OnActionCreate;
import cat.institutmarianao.shipments.validation.groups.OnShipmentCreate;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;
import lombok.EqualsAndHashCode;

/* JSON annotations */
/*
 * Maps JSON data to OpeningDto, AssignmentDto, InterventionDto or CloseDto instance
 * depending on property type
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type", visible = true)
@JsonSubTypes({ @Type(value = Reception.class, name = Action.RECEPTION),
		@Type(value = Assignment.class, name = Action.ASSIGNMENT),
		@Type(value = Delivery.class, name = Action.DELIVERY) })
/* Validation */
/* Lombok */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Action implements Serializable {

	private static final long serialVersionUID = 1L;

	/* Values for type - MUST be constants */
	public static final String RECEPTION = "RECEPTION";
	public static final String ASSIGNMENT = "ASSIGNMENT";
	public static final String DELIVERY = "DELIVERY";

	public enum Type {
		RECEPTION, ASSIGNMENT, DELIVERY
	}

	/* Validation */
	@Null(groups = { OnShipmentCreate.class, OnActionCreate.class }) // Must be null on inserts
	// @NotNull(groups = OnActionUpdate.class) // Must be not null on updates
	/* Lombok */
	@EqualsAndHashCode.Include
	protected Long id;

	/* Validation */
	@NotNull
	protected Action.Type type;

	/* Validation */
	@NotEmpty
	protected String performer;

	/* JSON */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ShipmentsApplication.DATE_PATTERN)
	protected Date date = new Date();

	/* JSON */
	@NotNull
	protected Long shipmentId;

}
