package cat.institutmarianao.shipments.model;

import java.io.Serializable;

import cat.institutmarianao.shipments.validation.groups.OnUserCreate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/* Lombok */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Receptionist extends User implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final int MAX_PLACE = 100;

	/* Validation */
	@NotNull(groups = OnUserCreate.class)
	/* Lombok */
	@NonNull
	private Long officeId;

	/* JSON */
	// @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	/* Validation */
	@Size(max = MAX_PLACE)
	@NotBlank(groups = OnUserCreate.class)
	/* Lombok */
	@NonNull
	private String place;

	public Receptionist(String username, String password, String fullName, Integer extension, Long officeId,
			String place) {
		super(username, password, fullName, extension);
		this.officeId = officeId;
		this.place = place;
		super.role = Role.RECEPTIONIST;
	}
}
