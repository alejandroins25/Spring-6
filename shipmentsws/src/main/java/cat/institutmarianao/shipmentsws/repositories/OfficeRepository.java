package cat.institutmarianao.shipmentsws.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import cat.institutmarianao.shipmentsws.model.Office;

public interface OfficeRepository extends JpaRepository<Office, Long> {
}
