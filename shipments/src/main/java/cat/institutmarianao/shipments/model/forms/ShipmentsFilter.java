package cat.institutmarianao.shipments.model.forms;

import java.util.Date;

import cat.institutmarianao.shipments.model.Shipment.Category;
import cat.institutmarianao.shipments.model.Shipment.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShipmentsFilter {
	private Status status;
	private String receptionist;
	private String courierAssigned;
	private Category category;
	private Date from;
	private Date to;

	/**
	 * <p>
	 * Reset the filter
	 * </p>
	 */
	public void clear() {
		status = null;
		receptionist = null;
		courierAssigned = null;
		category = null;
		from = null;
		to = null;
	}
}
