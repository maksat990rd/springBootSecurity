package peaksoft.springbootsecurity.serviceImple.service;

import peaksoft.springbootsecurity.model.Company;

import java.util.List;

public interface CompanyService {

    void saveCompany(Company company);

    List<Company> getAllCompanies();

    Company getCompanyById(int id);

    void updateCompany(int id,Company company);

    void deleteCompanyById(int id);
}
