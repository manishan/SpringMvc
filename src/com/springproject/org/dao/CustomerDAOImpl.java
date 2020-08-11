package com.springproject.org.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springproject.org.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
			
	@Override
	public List<Customer> getCustomers() {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
				
		// create a query
		Query<Customer> theQuery = 
				currentSession.createQuery("from Customer order by lastName", Customer.class);
		
		// execute query and get result list
		List<Customer> customers = theQuery.getResultList();
		
		//System.out.println("Customer :"+ customers);
				
		// return the results		
		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) {
		//get session
			Session session = sessionFactory.getCurrentSession();
		//execute save command
			session.save(customer);
		//
		
	}

	@Override
	public Customer getCustomer(int customerId) {
		// Get session
			Session session = sessionFactory.getCurrentSession();
		//get customer object for given id
			Customer customer = session.get(Customer.class, customerId);
		
		//return saved object
		return customer;
	}

	@Override
	public void deleteCustomer(int customerId) {
		// Get session
		Session currentSession = sessionFactory.getCurrentSession();
		//get customer object for given id
		// create a query
		Query<Customer> theQuery = 
				currentSession.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", customerId);
		//return saved object
		theQuery.executeUpdate();	
		
	}

}
