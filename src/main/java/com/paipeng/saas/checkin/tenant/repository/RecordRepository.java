package com.paipeng.saas.checkin.tenant.repository;

import com.paipeng.saas.checkin.tenant.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
}
