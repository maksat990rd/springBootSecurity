package peaksoft.springbootsecurity.repositoryImpl;

import org.springframework.stereotype.Repository;
import peaksoft.springbootsecurity.model.Company;
import peaksoft.springbootsecurity.repositoryImpl.repository.CompanyRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;


@Repository
@Transactional
public class CompanyRepositoryImpl implements CompanyRepository {

    @PersistenceContext
    private EntityManager manager;


    @Override
    public void saveCompany(Company company) {
        manager.persist(company);
    }

    @Override
    public List<Company> getAllCompanies() {
        return manager.createQuery("select c from Company c",Company.class).getResultList();
    }

    @Override
    public Company getCompanyById(int id) {
        return manager.find(Company.class,id);
    }

    @Override
    public void updateCompany(int id,Company company) {
        Company company1 = manager.find(Company.class,id);
        company1.setCompanyName(company.getCompanyName());
        company1.setLocatedCountry(company.getLocatedCountry());
        manager.merge(company1);
    }


    @Override
    public void deleteCompanyById(int id) {

       manager.remove(manager.find(Company.class,id));

    }
}
