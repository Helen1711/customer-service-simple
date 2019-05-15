package com.customer.service.simple.repository;

import com.customer.service.simple.model.Address;
import com.customer.service.simple.model.Customer;
import com.customer.service.simple.model.Role;
import com.customer.service.simple.model.RoleType;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository repository;
    private Customer customer;
    @Autowired
    private EntityManager em;

    @Before
    public void setUp() {
        customer = new Customer("Olia", "Nelasa", "olia@gmail.com");
        Address address = new Address("Lviv", "Rivna", "41", "7");
        Role role = new Role(RoleType.OPERATOR);
        customer.addAddress(address);
        customer.addRole(role);
        repository.save(customer);
    }

    @After
    public void tearDown() {
        repository.deleteAll();
    }

    @Test
    public void testFindAllByCity() {
        int actual = repository.findAllByAddressCity("Lviv").size();
        Assert.assertEquals(1, actual);
    }

    @Test
    public void testFindCustomerByEmail() {
        Optional<Customer> actualCustomer = repository.findByEmailFetchRolesAndAddress("olia@gmail.com");
        Optional<Customer> expectedCustomer = repository.findById(1);
        Assert.assertEquals(expectedCustomer, actualCustomer);
    }

    @Test
    public void testAddRoleToAllCustomers() {
        repository.addRoleToAllCustomers(RoleType.CUSTOMER);
        Customer customer = repository.findByEmailFetchRolesAndAddress("olia@gmail.com").get();
        int size = customer.getRoles().size();
        Assert.assertEquals(1, size);
    }
}