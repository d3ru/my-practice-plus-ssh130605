package jdbc.demo1.service;

import java.util.List;

import jdbc.demo1.bo.Customer;

public interface CustomerService
{
	void register(Customer c);

	void unRegister(String name);

	void modify(Customer c);

	Customer findCustomerByName(String name) throws CustomerServiceAppException;

	List<Customer> findAll();
}
