package com.paipeng.saas.checkin.tenant.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paipeng.saas.checkin.tenant.model.Role;
import com.paipeng.saas.checkin.tenant.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

    private static final Logger LOG = LoggerFactory
            .getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findByRole(String roleName) {
        Role role = roleRepository.findByRole(roleName);
        LOG.info("Role:" + role.getRole() + " found");
        return role;
    }
}
