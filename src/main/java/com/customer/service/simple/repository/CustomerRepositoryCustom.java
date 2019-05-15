package com.customer.service.simple.repository;

import com.customer.service.simple.model.RoleType;

public interface CustomerRepositoryCustom {
    void addRoleToAllCustomers(RoleType roleType);
}
