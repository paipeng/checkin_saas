package com.paipeng.saas.checkin.tenant.service;


import com.paipeng.saas.checkin.tenant.entity.Company;
import com.paipeng.saas.checkin.tenant.repository.CompanyRepository;
import com.paipeng.saas.checkin.util.exception.SC_NOT_FOUND;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService extends BaseService{

    @Autowired
    private CompanyRepository companyRepository;

    public Company query(long companyId) {
        return companyRepository.findById(companyId).orElse(null);
    }

    public List<Company> query() {
        return companyRepository.findAll();
    }

    public Company save(Company company) {
        company = companyRepository.saveAndFlush(company);
        return company;
    }

    public Company update(long companyId, Company company) {
        Company foundCompany = query(companyId);
        if (foundCompany == null) {
            throw new SC_NOT_FOUND();
        }
        foundCompany.setName(company.getName());
        foundCompany.setLicenseCount(company.getLicenseCount());
        foundCompany.setExpire(company.getExpire());
        company = companyRepository.saveAndFlush(foundCompany);
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
