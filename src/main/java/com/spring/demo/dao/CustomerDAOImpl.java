package com.spring.demo.dao;

import com.spring.demo.Entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class CustomerDAOImpl implements CustomerDAO {

    // need to inject the session factory
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Customer> getCustomers() {

        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // create a query..sort by last name
        Query<Customer> theQuery =
                currentSession.createQuery("from Customer order by lastName", Customer.class);

        // execute query and get result list
        List<Customer> customers = theQuery.getResultList();

        // return the results
        return customers;
    }

    @Override
    public void saveCustomer(Customer theCustomer) {

        //get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        //save or update customer
        currentSession.saveOrUpdate(theCustomer);

    }

    @Override
    public Customer getCustomer(int theId) {

        //get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        //retrieve customer with theId
        Customer theCustomer = currentSession.get(Customer.class, theId);

        //send customer
        return theCustomer;
    }

    @Override
    public void deleteCustomer(int theId) {
        Session currentSession = sessionFactory.getCurrentSession();

        Customer theCustomer = currentSession.get(Customer.class, theId);

        currentSession.delete(theCustomer);

    }

    @Override
    public List<Customer> searchCustomers(String theSearchName) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query query = null;

        //search for firstName or lastName(case insensitive)
        if (theSearchName != null && theSearchName.trim().length() > 0) {
            query = currentSession.createQuery("from Customer where lower(firstName) like :theName " +
                    "or lower(lastName) like :theName", Customer.class);
            query.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
        } else {
            //theSearchName is empty...so get all customers
            query = currentSession.createQuery("from Customer", Customer.class);
        }
        //execute query and get result list
        List<Customer> customers = query.getResultList();
        return customers;
    }
}






