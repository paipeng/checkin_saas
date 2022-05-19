package com.paipeng.saas.checkin.master.service;

import org.springframework.data.repository.query.Param;

import com.paipeng.saas.checkin.master.model.MasterTenant;

public interface MasterTenantService {
    
    MasterTenant findByTenantId(@Param("tenantId") String tenantId);
}
