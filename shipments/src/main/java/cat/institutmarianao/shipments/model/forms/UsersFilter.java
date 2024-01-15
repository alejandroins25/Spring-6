package cat.institutmarianao.shipments.model.forms;

import cat.institutmarianao.shipments.model.User.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersFilter {
	private Role role;
	private String fullName;

	/**
	 * <p>
	 * Reset the filter
	 * </p>
	 */
	public void clear() {
		role = null;
		fullName = null;
	}
}
