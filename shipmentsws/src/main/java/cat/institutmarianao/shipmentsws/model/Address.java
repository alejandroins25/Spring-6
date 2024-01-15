package cat.institutmarianao.shipmentsws.model;

import java.io.Serializable;

import cat.institutmarianao.shipmentsws.validation.groups.OnActionCreate;
import cat.institutmarianao.shipmentsws.validation.groups.OnActionUpdate;
import cat.institutmarianao.shipmentsws.validation.groups.OnShipmentCreate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;
import lombok.EqualsAndHashCode;

/* JPA annotations */
@Entity
@Table(name = "addresses")
/* Lombok */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;

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

	private String name;

	private String street;
	private String number;
	private String floor;
	private String door;

	private String city;
	private String province;

	private String postalCode;

	private String country;
}
