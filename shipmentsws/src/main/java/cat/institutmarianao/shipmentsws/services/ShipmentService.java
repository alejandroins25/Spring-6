package cat.institutmarianao.shipmentsws.services;

import java.util.Date;
import java.util.List;

import cat.institutmarianao.shipmentsws.model.Shipment;
import cat.institutmarianao.shipmentsws.model.Shipment.Category;
import cat.institutmarianao.shipmentsws.model.Shipment.Status;
import cat.institutmarianao.shipmentsws.model.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public interface ShipmentService {
	List<Shipment> findAll(Status shipmentStatus, User receptionist, User courier, Category category, Date from,
			Date to);

	Shipment getById(@Positive Long id);

	Shipment save(@NotNull @Valid Shipment shipment);

	void deleteById(@Positive Long shipmentId);

}