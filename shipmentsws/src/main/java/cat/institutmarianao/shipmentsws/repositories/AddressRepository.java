package cat.institutmarianao.shipmentsws.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import cat.institutmarianao.shipmentsws.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
