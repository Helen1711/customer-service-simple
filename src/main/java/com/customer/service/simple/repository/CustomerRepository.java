package com.customer.service.simple.repository;

import com.customer.service.simple.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface CustomerRepository extends JpaRepository<Customer, Integer>, CustomerRepositoryCustom {
    List<Customer> findAllByAddressCity(String city);

    @Query("select  c from Customer c left join fetch c.address left join fetch c.roles where c.email = :email")
    Optional<Customer> findByEmailFetchRolesAndAddress(@Param("email") String email);
}
