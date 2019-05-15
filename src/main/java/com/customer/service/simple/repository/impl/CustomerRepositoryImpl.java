package com.customer.service.simple.repository.impl;

import com.customer.service.simple.model.Customer;
import com.customer.service.simple.model.Role;
import com.customer.service.simple.model.RoleType;
import com.customer.service.simple.repository.CustomerRepositoryCustom;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addRoleToAllCustomers(RoleType roleType) {
        String query = "select c from Customer c left join fetch c.roles";
        List<Customer> customers = entityManager.createQuery(query, Customer.class)
                .getResultList();
        customers.stream()
                .filter(customer -> hasNoRole(customer, roleType))
                .peek(customer -> customer.addRole(Role.valueOf(roleType)))
                .forEach(c -> entityManager.persist(c));
    }

    private boolean hasNoRole(Customer customer, RoleType roleType) {
        return customer.getRoles()
                .stream()
                .map(Role::getRoleType)
                .noneMatch(type -> type.equals(roleType));
    }
}
