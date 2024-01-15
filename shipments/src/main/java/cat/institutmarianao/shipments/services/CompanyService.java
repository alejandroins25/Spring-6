package cat.institutmarianao.shipments.services;

import java.util.List;

import cat.institutmarianao.shipments.model.Company;

public interface CompanyService {
	List<Company> getAllCompanies();

	Company getById(Long id);
}
