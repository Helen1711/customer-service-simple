package com.customer.service.simple.service.impl;

import com.customer.service.simple.exceptions.EntityNotFoundException;
import com.customer.service.simple.model.Customer;
import com.customer.service.simple.model.RoleType;
import com.customer.service.simple.repository.CustomerRepository;
import com.customer.service.simple.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<Customer> findByCity(String city) {
        return repository.findAllByAddressCity(city);
    }

    @Transactional(readOnly = true)
    @Override
    public Customer getByEmail(String email) {
        return repository.findByEmailFetchRolesAndAddress(email)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Can't find customer by email ", email)));
    }

    @Override
    public void addRoleToAllCustomers(RoleType roleType) {
        repository.addRoleToAllCustomers(roleType);
    }
}
