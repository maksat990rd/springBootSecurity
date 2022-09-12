package peaksoft.springbootsecurity.serviceImple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.springbootsecurity.model.Company;
import peaksoft.springbootsecurity.repositoryImpl.repository.CompanyRepository;
import peaksoft.springbootsecurity.serviceImple.service.CompanyService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository repository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveCompany(Company company) {
        repository.saveCompany(company);
    }

    @Override
    public List<Company> getAllCompanies() {
        return repository.getAllCompanies();
    }

    @Override
    public Company getCompanyById(int id) {
        return repository.getCompanyById(id);
    }

    @Override
    public void updateCompany(int id,Company company) {
        repository.updateCompany(id,company);
    }


    @Override
    public void deleteCompanyById(int id) {
        repository.deleteCompanyById(id);
    }
}
