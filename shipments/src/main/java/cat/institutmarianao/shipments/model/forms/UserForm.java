package cat.institutmarianao.shipments.model.forms;

import cat.institutmarianao.shipments.model.Courier;
import cat.institutmarianao.shipments.model.LogisticsManager;
import cat.institutmarianao.shipments.model.Receptionist;
import cat.institutmarianao.shipments.model.User;
import cat.institutmarianao.shipments.model.User.Role;
import cat.institutmarianao.shipments.validation.groups.OnUserCreate;
import cat.institutmarianao.shipments.validation.groups.OnUserUpdate;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserForm {

	@NotBlank(groups = { OnUserCreate.class, OnUserUpdate.class })
	@Size(min = User.MIN_USERNAME, max = User.MAX_USERNAME, groups = { OnUserCreate.class, OnUserUpdate.class })
	protected String username;
	@NotNull(groups = { OnUserCreate.class, OnUserUpdate.class })
	protected Role role;

	@NotBlank(groups = OnUserCreate.class)
	@Size(min = User.MIN_PASSWORD, groups = OnUserCreate.class)
	protected String password;

	@NotBlank(groups = { OnUserCreate.class, OnUserUpdate.class })
	@Size(min = User.MIN_FULL_NAME, max = User.MAX_FULL_NAME, groups = { OnUserCreate.class, OnUserUpdate.class })
	protected String fullName;

	@NotNull(groups = { OnUserCreate.class, OnUserUpdate.class })
	@Min(value = 0, groups = { OnUserCreate.class, OnUserUpdate.class })
	@Max(value = User.MAX_EXTENSION, groups = { OnUserCreate.class, OnUserUpdate.class })
	protected Integer extension;

	@NotBlank(groups = OnUserCreate.class)
	@Size(min = User.MIN_PASSWORD, groups = OnUserCreate.class)
	protected String verify;

	@Positive(groups = { OnUserCreate.class, OnUserUpdate.class })
	private Long officeId;

	private String place;

	@Positive(groups = { OnUserCreate.class, OnUserUpdate.class })
	private Long companyId;

	public UserForm(User user) {
		username = user.getUsername();
		role = user.getRole();
		password = null;
		fullName = user.getFullName();
		extension = user.getExtension();
		verify = null;

		if (user instanceof Receptionist employee) {
			officeId = employee.getOfficeId();
			place = employee.getPlace();
		} else if (user instanceof LogisticsManager technician) {
			officeId = technician.getOfficeId();
			place = technician.getPlace();
		} else if (user instanceof Courier courier) {
			companyId = courier.getCompanyId();
		}
	}

	public User getUser() {
		return switch (role) {
		case RECEPTIONIST -> new Receptionist(username, password, fullName, extension, officeId, place);
		case LOGISTICS_MANAGER -> new LogisticsManager(username, password, fullName, extension, officeId, place);
		case COURIER -> new Courier(username, password, fullName, extension, companyId);
		};
	}
}
