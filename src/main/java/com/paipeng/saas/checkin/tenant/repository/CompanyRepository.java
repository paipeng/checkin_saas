package com.paipeng.saas.checkin.tenant.repository;

import com.paipeng.saas.checkin.tenant.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
