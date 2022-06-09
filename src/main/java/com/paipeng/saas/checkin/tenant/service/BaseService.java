package com.paipeng.saas.checkin.tenant.service;

import com.paipeng.saas.checkin.security.AppAuthenticationToken;
import com.paipeng.saas.checkin.tenant.entity.Task;
import com.paipeng.saas.checkin.tenant.entity.User;
import liquibase.pro.packaged.T;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

public abstract class BaseService implements BaseServiceInterface{
    protected static Logger logger;

    public BaseService() {
        logger = LogManager.getLogger(this.getClass().getSimpleName());
    }

    protected User getUserFromSecurity() {
        AppAuthenticationToken auth = (AppAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            return  (User) auth.getDetails();
        } else {
            return null;
        }
    }


}
