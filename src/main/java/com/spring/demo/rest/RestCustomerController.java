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

    //save or update
    //Hibernate method saveOrUpdate checks customerId, if it equals 0 then hibernate create new Customer
    //either way he take customerId and update instance
    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer customer) {
        //customerId = hibernate create new Customer
        customer.setId(0);
        customerService.saveCustomer(customer);
        return customer;
    }

    @PutMapping("/customers")
    public Customer updateCustomer(@RequestBody Customer customer) {

        customerService.saveCustomer(customer);
        return customer;
    }

    @DeleteMapping("/customers/{customerId}")
    public String deleteCustomer(@PathVariable int customerId) {

        Customer tempCustomer = customerService.getCustomer(customerId);
        if(tempCustomer == null) throw new CustomerNotFoundException("Customer not found -" + customerId);

        customerService.deleteCustomer(customerId);
        return "Deleted customer customerId - " + customerId;
    }
}
