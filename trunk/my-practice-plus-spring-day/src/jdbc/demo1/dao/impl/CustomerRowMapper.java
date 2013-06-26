package jdbc.demo1.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.demo1.bo.Customer;

import org.springframework.jdbc.core.RowMapper;

public class CustomerRowMapper implements RowMapper
{

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException
	{
		Customer customer = new Customer();
		customer.setId(rs.getLong("id"));
		customer.setAge(rs.getInt("age"));
		customer.setName(rs.getString("name"));
		return customer;
	}

}
