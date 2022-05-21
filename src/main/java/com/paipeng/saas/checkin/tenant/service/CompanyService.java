package com.paipeng.saas.checkin.tenant.service;


import com.paipeng.saas.checkin.tenant.entity.Company;
import com.paipeng.saas.checkin.tenant.repository.CompanyRepository;
import com.paipeng.saas.checkin.util.exception.SC_NOT_FOUND;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Service
public class CompanyService extends BaseService{

    @Autowired
    private CompanyRepository companyRepository;

    public Company query(long companyId) {
        return companyRepository.findById(companyId).orElse(null);
    }
    public Company save(Company company) {
        company = companyRepository.saveAndFlush(company);
        return company;
    }

    public Company update(Company company) {
        Company foundCompany = query(company.getId());
        if (foundCompany == null) {
            throw new SC_NOT_FOUND();
        }
        company = companyRepository.saveAndFlush(company);
        return company;
    }

    public void delete(long companyId) {
        Company foundCompany = query(companyId);
        if (foundCompany == null) {
            throw new SC_NOT_FOUND();
        }
        companyRepository.delete(foundCompany);
    }
}
