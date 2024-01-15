package cat.institutmarianao.shipments.model;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

/* Lombok */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;

	/* Lombok */
	@EqualsAndHashCode.Include
	protected Long id;

	private String name;

	@NotBlank
	private String street;
	@NotBlank
	private String number;
	private String floor;
	private String door;

	@NotBlank
	private String city;
	@NotBlank
	private String province;

	@NotBlank
	private String postalCode;

	@NotBlank
	private String country;
}
