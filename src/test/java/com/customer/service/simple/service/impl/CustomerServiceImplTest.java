package com.customer.service.simple.service.impl;

import com.customer.service.simple.exceptions.EntityNotFoundException;
import com.customer.service.simple.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceImplTest {
    @Autowired
    private CustomerService service;

    @Test(expected = EntityNotFoundException.class)
    public void getByEmailShouldReturnEntityNotFoundException() {
        service.getByEmail("ivan@gmail.com");
    }
}