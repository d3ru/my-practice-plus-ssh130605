package hibernate.demo1.dao.impl;

import hibernate.demo1.bo.Stock;
import hibernate.demo1.dao.StockDao;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class StockDAOHibernateImpl extends HibernateDaoSupport implements StockDao
{

	@Override
	public void save(Stock s)
	{
		getHibernateTemplate().save(s);
	}

	@Override
	public Stock findByName(final String name)
	{
		return (Stock) getHibernateTemplate().execute(new HibernateCallback()
		{

			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException
			{
				String hql = "from Stock s where s.name=:name";
				Query query = session.createQuery(hql);
				query.setString("name", name);
				return query.uniqueResult();
			}
		});
	}

	@Override
	public void modify(Stock s)
	{
		getHibernateTemplate().update(s);
	}

}
