package jdbc.demo1.service.impl;

import java.util.List;

import jdbc.demo1.bo.Customer;
import jdbc.demo1.dao.CustomerDao;
import jdbc.demo1.service.CustomerService;
import jdbc.demo1.service.CustomerServiceAppException;

import org.springframework.dao.EmptyResultDataAccessException;

public class CustomerServiceSpringImpl implements CustomerService
{

	private CustomerDao dao;

	public void setDao(CustomerDao dao)
	{
		this.dao = dao;
	}

	@Override
	public void register(Customer c)
	{
		dao.save(c);
	}

	@Override
	public void unRegister(String name)
	{
		dao.delete(name);

	}

	@Override
	public void modify(Customer c)
	{
		dao.modify(c);
	}

	@Override
	public Customer findCustomerByName(String name) throws CustomerServiceAppException
	{
		Customer customer = null;
		try
		{
			customer = dao.findByName(name);
		}
		catch (EmptyResultDataAccessException e)
		{
			throw new CustomerServiceAppException(e.getMessage());
		}
		return customer;
	}

	@Override
	public List<Customer> findAll()
	{
		return dao.findAll();
	}

}
