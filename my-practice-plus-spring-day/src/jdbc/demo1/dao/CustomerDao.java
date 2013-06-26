package jdbc.demo1.dao;

import java.util.List;

import jdbc.demo1.bo.Customer;

public interface CustomerDao
{
	public void save(Customer c);

	public void delete(String name);

	public void modify(Customer c);

	public Customer findByName(String name);

	public List<Customer> findAll();
}
