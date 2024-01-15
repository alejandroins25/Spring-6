package cat.institutmarianao.shipmentsws.model.dto;

import java.io.Serializable;

import cat.institutmarianao.shipmentsws.validation.groups.OnUserCreate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

/* Lombok */
@Data
@EqualsAndHashCode(callSuper = true)
public class ReceptionistDto extends UserDto implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int MAX_PLACE = 100;

	@NotNull(groups = OnUserCreate.class)
	private Long officeId;

	/* JSON */
	// @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	/* Validation */
	@Size(max = MAX_PLACE)
	@NotBlank(groups = OnUserCreate.class)
	private String place;

}
