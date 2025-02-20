package com.springproject.org.dao;

import java.util.List;

import com.springproject.org.entity.Customer;

public interface CustomerDAO {
	
	public List<Customer> getCustomers();
	
	public void saveCustomer(Customer customer);
	
	public Customer getCustomer(int customerId);
	
	public void deleteCustomer(int customerId);

}
