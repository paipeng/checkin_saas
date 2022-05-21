package com.paipeng.saas.checkin.tenant.repository;


import com.paipeng.saas.checkin.tenant.entity.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeRepository extends JpaRepository<Code, Long> {
}

