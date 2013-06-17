package cn.javass.dao.hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.javass.commons.pagination.PageUtil;
import cn.javass.dao.IBaseDao;

public class BaseHibernageDao<M extends Serializable, pk extends Serializable> extends HibernateDaoSupport implements IBaseDao<M, pk>
{
	private Class<M> entityClass;
	private String HQL_LIST_ALL;
	private String HQL_COUNT_ALL;

	@SuppressWarnings("unchecked")
	public void init()
	{
		entityClass = (Class<M>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		String entityName = getSessionFactory().getClassMetadata(entityClass).getEntityName();
		HQL_LIST_ALL = "from " + entityName;
		HQL_COUNT_ALL = "select count(*) from " + entityName;
	}

	protected String getListAllHql()
	{
		return HQL_LIST_ALL;
	}

	protected String getCountAllHql()
	{
		return HQL_COUNT_ALL;
	}

	@Override
	public void save(M m)
	{
		getHibernateTemplate().save(m);
	}

	@Override
	public void saveOrUpdate(M m)
	{
		getHibernateTemplate().saveOrUpdate(m);
	}

	@Override
	public void update(M m)
	{
		getHibernateTemplate().update(m);
	}

	@Override
	public void merge(M m)
	{
		getHibernateTemplate().merge(m);
	}

	@Override
	public void delete(pk id)
	{
		getHibernateTemplate().delete(this.get(id));
	}

	@Override
	public void deleteObject(M m)
	{
		getHibernateTemplate().delete(m);
	}

	@Override
	public M get(pk id)
	{
		return getHibernateTemplate().get(this.entityClass, id);
	}

	@Override
	public int countAll()
	{
		Number number = unique(HQL_COUNT_ALL);
		return number.intValue();
	}

	@Override
	public List<M> listAll()
	{
		return list(getListAllHql());
	}

	@Override
	public List<M> listAll(int pn, int pageSize)
	{
		return list(getListAllHql(), pn, pageSize);
	}

	protected <T> List<T> list(final String hQL, final Object... params)
	{
		return list(hQL, -1, -1, params);
	}

	@SuppressWarnings("unchecked")
	protected <T> List<T> list(final String hQL_LIST_ALL, final int pn, final int pageSize, final Object... params)
	{
		return getHibernateTemplate().executeFind(new HibernateCallback<List<T>>()
		{

			@Override
			public List<T> doInHibernate(Session session) throws HibernateException, SQLException
			{
				Query query = session.createQuery(hQL_LIST_ALL);
				if (params != null)
				{
					for (int i = 0; i <= params.length; i++)
					{
						query.setParameter(i, params[i]);
					}
				}

				if (pn > -1 && pageSize > -1)
				{
					query.setMaxResults(pageSize);
					int start = PageUtil.getStart(pn, pageSize);
					if (start != 0)
					{
						query.setFirstResult(start);
					}
				}
				return query.list();
			}
		});
	}

	@SuppressWarnings("unchecked")
	protected <T> T unique(final String hQL_COUNT_ALL, final Object... params)
	{
		return getHibernateTemplate().execute(new HibernateCallback<T>()
		{

			@Override
			public T doInHibernate(Session session) throws HibernateException, SQLException
			{
				Query query = session.createQuery(hQL_COUNT_ALL);
				if (params != null)
				{
					for (int i = 0; i < params.length; i++)
					{
						query.setParameter(i, params[i]);
					}
				}
				return (T) query.setMaxResults(1).uniqueResult();
			}
		});
	}
}
