package com.springproject.org.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springproject.org.entity.Customer;
import com.springproject.org.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("/list")
	public String listCustomers(Model theModel) {
		List<Customer> theCustomers = customerService.getCustomers();
		
		
		//add customer to model
		theModel.addAttribute("customers",theCustomers);
		
		return "list-customers";
	}
	
	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		Customer theCustomer = new Customer();
		
		theModel.addAttribute("customer", theCustomer);
		
		return "customer-form";
	}
	
	@RequestMapping("/saveCustomer")
	public String saveCutomer(@ModelAttribute("customer") Customer theCustomer) {
		customerService.saveCustomer(theCustomer);
		
		return "redirect:/customer/list";
	}
	
	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int customerId , Model theModel) {
		Customer customer = customerService.getCustomer(customerId);
		
		theModel.addAttribute("customer",customer);
		
		return "customer-form";
	}
	
	@RequestMapping("/showFormForDelete")
	public String showFormForDelete(@RequestParam("customerId") int customerId) {
		customerService.deleteCustomer(customerId);
		
		
		return "redirect:/customer/list";
	}
}
