package jdbc.demo1.dao.impl;

import java.util.List;

import jdbc.demo1.bo.Customer;
import jdbc.demo1.dao.CustomerDao;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class CustomerDaoJDBCImpl2 extends JdbcDaoSupport implements CustomerDao
{

	@Override
	public void save(Customer c)
	{
		String sql = "insert into customer (name,age) values (?,?)";
		Object[] args = { c.getName(), c.getAge() };
		getJdbcTemplate().update(sql, args);
	}

	@Override
	public void delete(String name)
	{
		String sql = "delete from customer where name=?";
		Object[] args = { name };
		getJdbcTemplate().update(sql, args);
	}

	@Override
	public void modify(Customer c)
	{
		String sql = "update customer set name=? , age=? where id=?";
		Object[] params = { c.getName(), c.getAge(), c.getId() };
		getJdbcTemplate().update(sql, params);
	}

	@Override
	public Customer findByName(String name)
	{
		String sql = "select * from customer where name=?";
		Object[] params = { name };
		return (Customer) getJdbcTemplate().queryForObject(sql, params, new CustomerRowMapper());
	}

	@Override
	public List<Customer> findAll()
	{
		String sql = "select * from customer";
		return getJdbcTemplate().query(sql, new CustomerRowMapper());
	}

}
