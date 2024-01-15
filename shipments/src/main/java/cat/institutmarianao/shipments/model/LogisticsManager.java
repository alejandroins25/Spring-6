package cat.institutmarianao.shipments.model;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/* Lombok */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LogisticsManager extends Receptionist implements Serializable {

	private static final long serialVersionUID = 1L;

	public LogisticsManager(String username, String password, String fullName, Integer extension, Long officeId,
			String place) {
		super(username, password, fullName, extension, officeId, place);
		super.role = Role.LOGISTICS_MANAGER;
	}

}
