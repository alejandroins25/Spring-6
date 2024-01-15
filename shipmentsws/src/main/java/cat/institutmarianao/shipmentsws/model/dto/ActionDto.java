package cat.institutmarianao.shipmentsws.model.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import cat.institutmarianao.shipmentsws.ShipmentswsApplication;
import cat.institutmarianao.shipmentsws.model.Action;
import cat.institutmarianao.shipmentsws.validation.groups.OnActionCreate;
import cat.institutmarianao.shipmentsws.validation.groups.OnShipmentCreate;
import io.swagger.v3.oas.annotations.media.Schema;
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
@JsonSubTypes({ @Type(value = ReceptionDto.class, name = Action.RECEPTION),
		@Type(value = AssignmentDto.class, name = Action.ASSIGNMENT),
		@Type(value = DeliveryDto.class, name = Action.DELIVERY) })
/* Validation */
/* Swagger */
@Schema(oneOf = { ReceptionDto.class, AssignmentDto.class, DeliveryDto.class }, discriminatorProperty = "type")
/* Lombok */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class ActionDto implements Serializable {

	private static final long serialVersionUID = 1L;

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

	/* Swagger */
	@Schema(pattern = ShipmentswsApplication.DATE_PATTERN)
	/* JSON */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ShipmentswsApplication.DATE_PATTERN)
	protected Date date = new Date();

	/* JSON */
	@NotNull
	protected Long shipmentId;
}
