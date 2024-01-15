package cat.institutmarianao.shipments.model;

import java.io.Serializable;

import cat.institutmarianao.shipments.validation.groups.OnUserCreate;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/* Lombok */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Courier extends User implements Serializable {

	private static final long serialVersionUID = 1L;

	/* Validation */
	@NotNull(groups = OnUserCreate.class)
	/* Lombok */
	@NonNull
	private Long companyId;

	public Courier(String username, String password, String fullName, Integer extension, Long companyId) {
		super(username, password, fullName, extension);
		this.companyId = companyId;
		super.role = Role.COURIER;
	}

}
