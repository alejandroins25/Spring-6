package cat.institutmarianao.shipmentsws.model.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import cat.institutmarianao.shipmentsws.model.User;
import cat.institutmarianao.shipmentsws.validation.groups.OnUserCreate;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

/* JSON annotations */
/*
 * Maps JSON data to Employee, Technician or Supervisor instance depending on
 * property role
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "role", visible = true)
@JsonSubTypes({ @Type(value = ReceptionistDto.class, name = User.RECEPTIONIST),
		@Type(value = LogisticsManagerDto.class, name = User.LOGISTICS_MANAGER),
		@Type(value = CourierDto.class, name = User.COURIER) })
/* Swagger */
@Schema(oneOf = { ReceptionistDto.class, LogisticsManagerDto.class, CourierDto.class }, discriminatorProperty = "role")
/* Lombok */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class UserDto implements Serializable {

	private static final long serialVersionUID = 1L;

	/* Validation */
	@NotBlank
	@Size(min = User.MIN_USERNAME, max = User.MAX_USERNAME)
	/* Lombok */
	@EqualsAndHashCode.Include
	protected String username;

	/* Validation */
	@NotNull
	protected User.Role role;

	/* JSON */
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Not present in generated JSON
	/* Validation */
	@NotBlank(groups = OnUserCreate.class)
	@Size(min = User.MIN_PASSWORD)
	protected String password;

	/* Validation */
	@NotBlank(groups = OnUserCreate.class)
	@Size(min = User.MIN_FULL_NAME, max = User.MAX_FULL_NAME)
	protected String fullName;

	/* Validation */
	@NotNull(groups = OnUserCreate.class)
	@PositiveOrZero
	@Max(User.MAX_EXTENSION)
	protected Integer extension;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected String location;

}
