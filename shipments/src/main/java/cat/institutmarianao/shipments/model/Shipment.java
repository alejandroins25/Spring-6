package cat.institutmarianao.shipments.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import cat.institutmarianao.shipments.ShipmentsApplication;
import cat.institutmarianao.shipments.validation.groups.OnShipmentCreate;
import cat.institutmarianao.shipments.validation.groups.OnShipmentUpdate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;
import lombok.EqualsAndHashCode;

/* Lombok */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Shipment implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int MAX_DESCRIPTION = 500;

	public enum Category {
		PARTICULAR, COMPANY, GOVERNMENT
	}

	public static final String PENDING = "PENDING";
	public static final String IN_PROCESS = "IN_PROCESS";
	public static final String DELIVERED = "DELIVERED";

	public enum Status {
		PENDING, IN_PROCESS, DELIVERED
	}

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
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ShipmentsApplication.DATE_PATTERN)
	protected Date receptionDate = new Date();

	/* Validation */
	@NotNull
	private Shipment.Category category;

	/* Validation */
	@NotNull
	@Valid
	private Address sender;

	/* Validation */
	@NotNull
	@Valid
	private Address recipient;

	private Float weight;
	private Float height;
	private Float width;
	private Float length;

	private Boolean express;
	private Boolean fragile;

	private String note;
}
