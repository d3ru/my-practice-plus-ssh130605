package com.pan.evms.dal.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import com.pan.evms.dal.interfaces.IOperationFlowDal;
import com.pan.evms.domain.OperationFlow;

public class OperationFlowDalImpl implements IOperationFlowDal
{
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

	private JdbcTemplate jTemplate;

	public void setjTemplate(JdbcTemplate jTemplate)
	{
		this.jTemplate = jTemplate;
	}

	@Override
	public void save(OperationFlow of)
	{
		Session session = sessionFactory.openSession();
		session.save(of);
	}

	@Override
	public List<OperationFlow> getByBranchId(String branchid)
	{
		String sql = "select * from operationflow where branchid=?";
		Object[] params = { branchid };
		return jTemplate.query(sql, params, new RowMapperResultSetExtractor<OperationFlow>(new RowMapper<OperationFlow>()
		{
			@Override
			public OperationFlow mapRow(ResultSet arg0, int arg1) throws SQLException
			{
				OperationFlow of = new OperationFlow();
				of.setBranchid(arg0.getString("branchid"));
				of.setId(arg0.getLong("id"));
				of.setStaffid(arg0.getString("staffid"));
				of.setCounterid(arg0.getString("counterid"));
				return of;
			}
		}));

	}

	@Override
	public void update(OperationFlow of)
	{

	}

	@Override
	public OperationFlow get(String id)
	{
		sessionFactory.openSession();
		return null;
	}

}
