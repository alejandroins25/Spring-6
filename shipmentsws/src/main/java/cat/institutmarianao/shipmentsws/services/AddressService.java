package cat.institutmarianao.shipmentsws.services;

import java.util.List;

import cat.institutmarianao.shipmentsws.model.Address;
import jakarta.validation.constraints.Positive;

public interface AddressService {
	List<Address> findAll();

	Address getById(@Positive Long id);
}
