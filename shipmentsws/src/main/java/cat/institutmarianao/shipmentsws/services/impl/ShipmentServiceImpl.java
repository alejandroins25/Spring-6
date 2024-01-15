package cat.institutmarianao.shipmentsws.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import cat.institutmarianao.shipmentsws.exception.NotFoundException;
import cat.institutmarianao.shipmentsws.model.Shipment;
import cat.institutmarianao.shipmentsws.model.Shipment.Category;
import cat.institutmarianao.shipmentsws.model.Shipment.Status;
import cat.institutmarianao.shipmentsws.model.User;
import cat.institutmarianao.shipmentsws.repositories.AddressRepository;
import cat.institutmarianao.shipmentsws.repositories.ShipmentRepository;
import cat.institutmarianao.shipmentsws.services.ShipmentService;
import cat.institutmarianao.shipmentsws.specifications.ShipmentAssignedToCourier;
import cat.institutmarianao.shipmentsws.specifications.ShipmentReceivedBy;
import cat.institutmarianao.shipmentsws.specifications.ShipmentReceivedFromDate;
import cat.institutmarianao.shipmentsws.specifications.ShipmentReceivedToDate;
import cat.institutmarianao.shipmentsws.specifications.ShipmentWithCategory;
import cat.institutmarianao.shipmentsws.specifications.ShipmentWithStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class ShipmentServiceImpl implements ShipmentService {
	@Autowired
	private ShipmentRepository shipmentRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Override
	public List<Shipment> findAll(Status shipmentStatus, User receptionist, User courier, Category category, Date from,
			Date to) {
		Specification<Shipment> spec = Specification.where(new ShipmentWithStatus(shipmentStatus))
				.and(new ShipmentReceivedBy(receptionist)).and(new ShipmentAssignedToCourier(courier))
				.and(new ShipmentWithCategory(category)).and(new ShipmentReceivedFromDate(from))
				.and(new ShipmentReceivedToDate(to));

		return shipmentRepository.findAll(spec);
	}

	@Override
	public Shipment getById(@Positive Long id) {
		return shipmentRepository.findById(id).orElseThrow(NotFoundException::new);
	}

	@Override
	public Shipment save(@NotNull @Valid Shipment shipment) {
		if (shipment.getSender().getId() != null) {
			shipment.setSender(addressRepository.save(shipment.getSender())); // Re-attach
		}
		if (shipment.getRecipient().getId() != null) {
			shipment.setRecipient(addressRepository.save(shipment.getRecipient())); // Re-attach
		}
		return shipmentRepository.save(shipment);
	}

	@Override
	public void deleteById(@Positive Long shipmentId) {
		shipmentRepository.deleteById(shipmentId);
	}
}
