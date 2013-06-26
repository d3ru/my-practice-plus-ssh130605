package jdbc.demo2.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.demo2.bo.Account;
import jdbc.demo2.dao.AccountDao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class AccountDAOJdbcImpl implements AccountDao
{
	private JdbcTemplate jTemplate;

	public void setjTemplate(JdbcTemplate jTemplate)
	{
		this.jTemplate = jTemplate;
	}

	@Override
	public void save(Account a)
	{
		String sql = "insert into account(name,balance) values(?,?)";
		Object[] params = { a.getName(), a.getBalance() };
		jTemplate.update(sql, params);
	}

	@Override
	public Account findByName(String name)
	{
		String sql = "select * from account where name=?";
		Object[] params = { name };
		return (Account) jTemplate.queryForObject(sql, params, new RowMapper()
		{
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				Account a = new Account();
				a.setBalance(rs.getDouble("balance"));
				a.setName(rs.getString("name"));
				a.setId(rs.getLong("id"));
				return a;
			}
		});
	}

	@Override
	public void modify(Account a)
	{
		String sql = "update account set name=? , balance=? where id=?";
		Object[] params = { a.getName(), a.getBalance(), a.getId() };
		jTemplate.update(sql, params);
	}

}
