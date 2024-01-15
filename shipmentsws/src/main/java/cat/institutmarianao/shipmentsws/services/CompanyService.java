package cat.institutmarianao.shipmentsws.services;

import java.util.List;

import cat.institutmarianao.shipmentsws.model.Company;
import jakarta.validation.constraints.Positive;

public interface CompanyService {

	List<Company> findAll();

	Company getById(@Positive Long id);
}
