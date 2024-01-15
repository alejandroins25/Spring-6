/**
 *
 */
package cat.institutmarianao.shipments.model;

import java.io.Serializable;

import cat.institutmarianao.shipments.validation.groups.OnUserCreate;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

/* Lombok */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Company implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int MAX_NAME = 100;

	/* Validation */
	@Positive(groups = OnUserCreate.class)
	/* Lombok */
	@EqualsAndHashCode.Include
	private Long id;

	/* Validation */
	@Size(max = MAX_NAME)
	@NotEmpty(groups = OnUserCreate.class)
	private String name;
}
