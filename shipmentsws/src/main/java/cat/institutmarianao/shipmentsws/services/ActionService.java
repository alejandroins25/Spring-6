package cat.institutmarianao.shipmentsws.services;

import java.util.List;

import cat.institutmarianao.shipmentsws.model.Action;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public interface ActionService {

	/**
	 * Tracking
	 */
	List<Action> findByShipmentId(@Positive Long shipmentId);

	Action save(@NotNull @Valid Action action);

}
