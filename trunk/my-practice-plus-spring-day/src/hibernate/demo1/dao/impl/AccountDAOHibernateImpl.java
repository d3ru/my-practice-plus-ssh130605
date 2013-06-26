package hibernate.demo1.dao.impl;

import hibernate.demo1.bo.Account;
import hibernate.demo1.dao.AccountDao;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class AccountDAOHibernateImpl extends HibernateDaoSupport implements AccountDao
{

	@Override
	public void save(Account a)
	{
		getHibernateTemplate().save(a);
	}

	@Override
	public Account findByName(final String name)
	{
		return (Account) getHibernateTemplate().execute(new HibernateCallback()
		{

			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException
			{
				String hql = "from Account a where a.name=:name";
				Query query = session.createQuery(hql);
				query.setString("name", name);
				return query.uniqueResult();
			}
		});
	}

	@Override
	public void modify(Account a)
	{
		getHibernateTemplate().update(a);
	}

}
