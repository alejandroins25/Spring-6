package cat.institutmarianao.shipmentsws.model;

import java.io.Serializable;
import java.util.Date;

import cat.institutmarianao.shipmentsws.validation.groups.OnActionCreate;
import cat.institutmarianao.shipmentsws.validation.groups.OnActionUpdate;
import cat.institutmarianao.shipmentsws.validation.groups.OnShipmentCreate;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;
import lombok.EqualsAndHashCode;

/* JPA annotations */
@Entity
/* Mapping JPA Indexes */
@Table(name = "actions", indexes = { @Index(name = "type", columnList = "type", unique = false),
		@Index(name = "shipment_x_date", columnList = "shipment_id, date DESC", unique = true) })
/* JPA Inheritance strategy is single table */
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
/*
 * Maps different JPA objects depending on his type attribute (Opening,
 * Assignment, Intervention or Close)
 */
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
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
	@NotNull(groups = OnActionUpdate.class) // Must be not null on updates
	/* JPA */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	/* Lombok */
	@EqualsAndHashCode.Include
	protected Long id;

	/* Validation */ @NotNull
	/* JPA */
	@Enumerated(EnumType.STRING) // Stored as string
	@Column(name = "type", insertable = false, updatable = false, nullable = false)
	protected Type type;

	/* Validation */
	@NotNull
	@Valid
	/* JPA */
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	protected User performer;

	/* JPA */
	@Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	protected Date date = new Date();

	/* Validation */
	@Null(groups = OnShipmentCreate.class) // The JSON do not have the shipment reference (shipment has no id yet)
	@NotNull(groups = { OnActionCreate.class })
//	@NotNull
	/* JPA */
	@ManyToOne(optional = false)
	@JoinColumn(name = "shipment_id", nullable = false)
	protected Shipment shipment;
}
