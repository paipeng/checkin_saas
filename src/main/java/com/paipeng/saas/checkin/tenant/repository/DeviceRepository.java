package com.paipeng.saas.checkin.tenant.repository;

import com.paipeng.saas.checkin.tenant.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
}