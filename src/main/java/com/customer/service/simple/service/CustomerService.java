package com.customer.service.simple.service;

import com.customer.service.simple.model.Customer;
import com.customer.service.simple.model.RoleType;

import java.util.List;

public interface CustomerService {
    List<Customer> findByCity(String city);

    Customer getByEmail(String email);

    void addRoleToAllCustomers(RoleType roleType);
}
