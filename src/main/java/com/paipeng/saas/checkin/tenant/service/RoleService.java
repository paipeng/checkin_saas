package com.paipeng.saas.checkin.tenant.service;

import com.paipeng.saas.checkin.tenant.entity.Role;

public interface RoleService {

    Role findByRole(String roleName);
}
