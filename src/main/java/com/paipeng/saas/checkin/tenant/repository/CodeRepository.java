package com.paipeng.saas.checkin.tenant.repository;


import com.paipeng.saas.checkin.tenant.entity.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CodeRepository extends JpaRepository<Code, Long> {
    Code findBySerialNumber(String serialNumber);

    List<Code> findAllByTaskId(Long taskId);
}

