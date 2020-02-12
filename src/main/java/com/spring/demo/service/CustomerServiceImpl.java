package com.spring.demo.service;

import com.spring.demo.Entity.Customer;
import com.spring.demo.dao.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDAO customerDAO;

    @Transactional
    @Override
    public List<Customer> getCustomers() {
        return customerDAO.getCustomers();
    }

    @Transactional
    @Override
    public void saveCustomer(Customer theCustomer) {
        customerDAO.saveCustomer(theCustomer);
    }

    @Transactional
    @Override
    public Customer getCustomer(int theId) {
        return customerDAO.getCustomer(theId);
    }

    @Transactional
    @Override
    public void deleteCustomer(int theId) {
        customerDAO.deleteCustomer(theId);
    }

    @Transactional
    @Override
    public List<Customer> searchCustomers(String theSearchName) {
        return customerDAO.searchCustomers(theSearchName);
    }

}
