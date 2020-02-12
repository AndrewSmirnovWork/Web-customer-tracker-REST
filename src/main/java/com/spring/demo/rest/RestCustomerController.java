package com.spring.demo.rest;

import com.spring.demo.Entity.Customer;
import com.spring.demo.exeption.CustomerNotFoundException;
import com.spring.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestCustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> getListCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping("/customer/{customerId}")
    public Customer getCustomer(@PathVariable int customerId) {

        Customer customer = customerService.getCustomer(customerId);
        if(customer == null)
            throw new CustomerNotFoundException("Customer is not found - "+ customerId);
        return customer;
    }

    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer customer) {

        customer.setId(0);
        customerService.saveCustomer(customer);
        return customer;
    }
}
