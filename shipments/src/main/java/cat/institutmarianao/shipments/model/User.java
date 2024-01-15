package cat.institutmarianao.shipments.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/* JSON annotations */
/*
 * Maps JSON data to Employee, Technician or Supervisor instance depending on
 * property role
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "role", visible = true)
@JsonSubTypes({ @Type(value = Receptionist.class, name = User.RECEPTIONIST),
		@Type(value = LogisticsManager.class, name = User.LOGISTICS_MANAGER),
		@Type(value = Courier.class, name = User.COURIER) })
/* Lombok */
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class User implements UserDetails, Serializable {

	private static final long serialVersionUID = 1L;

	/* Values for role - MUST be constants (can not be enums) */
	public static final String RECEPTIONIST = "RECEPTIONIST";
	public static final String LOGISTICS_MANAGER = "LOGISTICS_MANAGER";
	public static final String COURIER = "COURIER";

	public enum Role {
		RECEPTIONIST, LOGISTICS_MANAGER, COURIER
	}

	public static final int MIN_USERNAME = 2;
	public static final int MAX_USERNAME = 25;
	public static final int MIN_PASSWORD = 10;
	public static final int MIN_FULL_NAME = 3;
	public static final int MAX_FULL_NAME = 100;
	public static final int MAX_EXTENSION = 9999;

	/* Validation */
	@NotBlank
	@Size(min = MIN_USERNAME, max = MAX_USERNAME)
	/* Lombok */
	@NonNull
	@EqualsAndHashCode.Include
	protected String username;

	/* Validation */
	@NotNull
	protected Role role;

	/* JSON */
	@JsonInclude(Include.NON_NULL)
	/* Validation */
	@NotBlank
	@Size(min = MIN_PASSWORD)
	protected String password;

	/* Validation */
	@NotBlank
	@Size(min = MIN_FULL_NAME, max = MAX_FULL_NAME)
	/* Lombok */
	@NonNull
	protected String fullName;

	/* Validation */
	@NotNull
	@Min(0)
	@Max(MAX_EXTENSION)
	/* Lombok */
	@NonNull
	protected Integer extension;

	protected String location;

	protected User(String username, String password, String fullName, Integer extension) {
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.extension = extension;
	}

	@Override /* Spring Security getter */
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> list = new ArrayList<>();
		list.add(new SimpleGrantedAuthority("ROLE_" + getRole()));
		return list;
	}

	@Override /* Spring Security getter */
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override /* Spring Security getter */
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override /* Spring Security getter */
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override /* Spring Security getter */
	public boolean isEnabled() {
		return true;
	}
}
