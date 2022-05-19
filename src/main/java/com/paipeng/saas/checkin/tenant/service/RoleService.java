package com.paipeng.saas.checkin.tenant.service;

import com.paipeng.saas.checkin.tenant.model.Role;

public interface RoleService {

    Role findByRole(String roleName);
}
