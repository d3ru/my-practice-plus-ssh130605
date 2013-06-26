package jdbc.demo2.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.demo2.bo.Stock;
import jdbc.demo2.dao.StockDao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class StockDAOJdbcImpl implements StockDao
{
	private JdbcTemplate jTemplate;

	public void setjTemplate(JdbcTemplate jTemplate)
	{
		this.jTemplate = jTemplate;
	}

	@Override
	public void save(Stock s)
	{
		jTemplate.update("insert into stock(name,qty) values(?,?)", new Object[] { s.getName(), s.getQty() });
	}

	@Override
	public Stock findByName(String name)
	{
		return (Stock) jTemplate.queryForObject("select * from stock where name=?", new Object[] { name }, new RowMapper()
		{

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				Stock s = new Stock();
				s.setId(rs.getLong("id"));
				s.setName(rs.getString("name"));
				s.setQty(rs.getDouble("qty"));
				return s;
			}

		});
	}

	@Override
	public void modify(Stock s)
	{
		jTemplate.update("update stock set name=?, qty=? where id=?", new Object[] { s.getName(), s.getQty(), s.getId() });
	}

}
