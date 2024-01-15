package cat.institutmarianao.shipmentsws.services.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import cat.institutmarianao.shipmentsws.model.Action;
import cat.institutmarianao.shipmentsws.model.Shipment;
import cat.institutmarianao.shipmentsws.repositories.ActionRepository;
import cat.institutmarianao.shipmentsws.services.ActionService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class ActionServiceImpl implements ActionService {

	@Autowired
	private Validator validator;

	@Autowired
	private ActionRepository actionRepository;

	/**
	 * Tracking
	 */
	@Override
	public List<Action> findByShipmentId(@Positive Long shipmentId) {
		return actionRepository.findByShipmentIdOrderByDateDesc(shipmentId);
	}

	@Override
	public Action save(@NotNull @Valid Action action) {
		Long shipmentId = action.getShipment().getId();

		List<Action> tracking = actionRepository.findByShipmentIdOrderByDateDesc(shipmentId);
		tracking.add(0, action);
		Shipment shipment = new Shipment();
		shipment.setTracking(tracking);

		Set<ConstraintViolation<Shipment>> errors = validator.validateProperty(shipment, "tracking");

		if (!errors.isEmpty()) {
			throw new ConstraintViolationException(errors);
		}
		return actionRepository.save(action);
	}

}
