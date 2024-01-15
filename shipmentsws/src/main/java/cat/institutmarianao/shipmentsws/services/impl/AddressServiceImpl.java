package cat.institutmarianao.shipmentsws.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import cat.institutmarianao.shipmentsws.exception.NotFoundException;
import cat.institutmarianao.shipmentsws.model.Address;
import cat.institutmarianao.shipmentsws.repositories.AddressRepository;
import cat.institutmarianao.shipmentsws.services.AddressService;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;

	@Override
	public List<Address> findAll() {
		return addressRepository.findAll();
	}

	@Override
	public Address getById(@Positive Long id) {
		return addressRepository.findById(id).orElseThrow(NotFoundException::new);
	}

}
