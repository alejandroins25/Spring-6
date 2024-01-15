package cat.institutmarianao.shipmentsws.model.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import cat.institutmarianao.shipmentsws.ShipmentswsApplication;
import cat.institutmarianao.shipmentsws.model.Address;
import cat.institutmarianao.shipmentsws.model.Assignment;
import cat.institutmarianao.shipmentsws.model.Shipment;
import cat.institutmarianao.shipmentsws.validation.groups.OnShipmentCreate;
import cat.institutmarianao.shipmentsws.validation.groups.OnShipmentUpdate;
import cat.institutmarianao.shipmentsws.validation.groups.OnUserCreate;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

/* Lombok */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ShipmentDto implements Serializable {

	private static final long serialVersionUID = 1L;

	/* Validation */
	@Null(groups = OnShipmentCreate.class) // Must be null on inserts
	@NotNull(groups = OnShipmentUpdate.class) // Must be not null on updates
	/* Lombok */
	@EqualsAndHashCode.Include
	private Long id;

	/* Validation */
	@NotNull
	private Integer trackingNumber;

	/* Validation */
	@NotEmpty
	protected String receptionist;

	/* Validation */
	@Min(Assignment.MIN_PRIORITAT)
	@Max(Assignment.MAX_PRIORITAT)
	protected Integer priority;

	/* Validation */
	protected String courier;

	/* JSON */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ShipmentswsApplication.DATE_PATTERN)
	protected Date receptionDate = new Date();

	/* Validation */
	@NotNull
	private Shipment.Category category;

	/* Validation */
	@PositiveOrZero(groups = OnUserCreate.class)
	private Address sender;

	/* Validation */
	@PositiveOrZero(groups = OnUserCreate.class)
	private Address recipient;

	private Float weight;
	private Float height;
	private Float width;
	private Float length;

	private Boolean express;
	private Boolean fragile;

	/* Validation */
	@Size(max = Shipment.MAX_DESCRIPTION)
	private String note;

}
